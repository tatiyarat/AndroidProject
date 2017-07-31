package com.techin1.androidproject.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.techin1.androidproject.R;
import com.techin1.androidproject.activity.StatusActivity;

/**
 * Created by IamDeveloper on 10/24/2016.
 */
public class MyService extends Service {

    public SharedPreferences sharedPreferences;
    private static int id = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        SharedPreferences sp = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt("idgroup", 2);
//        editor.commit();



//        Intent i = new Intent(this, StatusActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        String url = "http://202.28.34.201/CS_Msuclub/csmsuclub/Home/schedule.php";
        Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("แจ้งเตือนกิจกรรม")
                .setWhen(System.currentTimeMillis())
                .setContentText("แจ้งเตือนกิจกรรม")
                .setTicker("้มีข้อความใหม่")
                .setSmallIcon(R.drawable.ic_action_action_account_box)
                .setContentIntent(pendingIntent);

        Uri sound = RingtoneManager.getDefaultUri(Notification.DEFAULT_ALL);
        builder.setSound(sound);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.icon2csmsuclub);
        builder.setLargeIcon(picture);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        ++id;
        manager.notify(id, builder.build());

        return super.onStartCommand(intent, flags, startId);
    }

}
