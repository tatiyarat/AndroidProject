package com.techin1.androidproject.Service;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;
import com.techin1.androidproject.LoginActivity;
import com.techin1.androidproject.MainActivity;
import com.techin1.androidproject.R;
import com.techin1.androidproject.activity.StatusActivity;
import com.techin1.androidproject.dao.GetDateDao;
import com.techin1.androidproject.fragment.StatusFragment;
import com.techin1.androidproject.manager.HTTPManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TECHIN1 on 23/11/2559.
 */
@TargetApi(Build.VERSION_CODES.N)
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    public SharedPreferences sharedPreferences;
    private static int id = 0;

    private PendingIntent pIntent;
    private AlarmManager alarm;
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

//        Log.e("data",remoteMessage.getData().get("type"));
        if (remoteMessage.getData() == null){
            Log.e("type","ว่าง");
        }else if (remoteMessage.getData().get("type").equals("0") == true){
            int uid = Integer.parseInt(remoteMessage.getData().get("uid"));
            String timeremind = remoteMessage.getData().get("timeremind");
            setTime(uid,timeremind);
        }else if (remoteMessage.getData().get("type").equals("1") == true){
            showNotification(remoteMessage.getData().get("gid"));
        }

        //Log.e("message",""+remoteMessage.getData().get("type"));

    }

    private void showNotification(String gid) {

        SharedPreferences sp = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("idgroup", Integer.parseInt(gid));
        editor.commit();

        Intent i = new Intent(this,StatusActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("ข้อความใหม่"+gid)
                .setWhen(System.currentTimeMillis())
                .setContentText(gid)
                .setTicker("้มีข้อความใหม่")
                .setSmallIcon(R.drawable.ic_communication_email)
                .setContentIntent(pendingIntent);

        Uri sound = RingtoneManager.getDefaultUri(Notification.DEFAULT_VIBRATE);
        builder.setSound(sound);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.ic_content_drafts);
        builder.setLargeIcon(picture);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        ++id;
        manager.notify(id,builder.build());

    }

    private void setTime(int id, final String timeremind) {

        Call<GetDateDao> call = HTTPManager.getInstances().getService().getdate(id);
        call.enqueue(new Callback<GetDateDao>() {
            @Override
            public void onResponse(Call<GetDateDao> call, Response<GetDateDao> response) {
                GetDateDao dao = response.body();
                if (response.isSuccessful()) {

                    for (int i = 0; i < dao.getData().size(); i++) {
                        year = dao.getData().get(i).getYear();
                        month = dao.getData().get(i).getMonth();
                        day = dao.getData().get(i).getDay();

                        hour = dao.getData().get(i).getHh();
                        minute = dao.getData().get(i).getMm();

                        setTimeDate(year, month, day, hour, minute, timeremind);
                    }



                } else {
                    try {
                        Toast.makeText(getApplication(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetDateDao> call, Throwable t) {
                Toast.makeText(getApplication(), t.toString()
                        , Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    private void setTimeDate(int year, int month, int day, int hour, int minute, String timeremind) {
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        cal.set(year, month - 1, day, hour, minute);
        Log.e("date", "" + sdf.format(cal.getTime()));

        Intent intent = new Intent(getApplication(), MyService.class);
        intent.putExtra("date", timeremind);
        pIntent = PendingIntent.getService(getApplication(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pIntent);
    }

}
