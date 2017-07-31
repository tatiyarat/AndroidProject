package com.techin1.androidproject;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.techin1.androidproject.Service.MyService;
import com.techin1.androidproject.activity.ForgotPasswordActivity;
import com.techin1.androidproject.activity.MenuGroupActivity;
import com.techin1.androidproject.dao.GetDateDao;
import com.techin1.androidproject.dao.Login;
import com.techin1.androidproject.manager.HTTPManager;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public SharedPreferences sharedPreferences;

    EditText etuser, etpass;
    Button butlogin,ForgotPassword;
    String iduser, passuser;
    private Session session;
    String token = FirebaseInstanceId.getInstance().getToken();

    private  int i = 0;
    private PendingIntent pIntent;
    private AlarmManager alarm;
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(this);
        if (session.loggedin()) {
            sharedPreferences = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
            int idu = sharedPreferences.getInt("iduser", 0);
            Log.e("xxx",""+idu);
            // เวลาแจ้งเตือน
            setTime(idu);
            startActivity(new Intent(LoginActivity.this, MenuGroupActivity.class));
            finish();
        }

        init();
    }

    private void init() {

        etuser = (EditText) findViewById(R.id.etuser);
        etpass = (EditText) findViewById(R.id.etpass);

        ForgotPassword = (Button) findViewById(R.id.ForgotPassword);

        butlogin = (Button) findViewById(R.id.butlogin);

        butlogin.setOnClickListener(this);
        ForgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == butlogin) {
            iduser = etuser.getText().toString();
            passuser = etpass.getText().toString();
            if (iduser.isEmpty() || passuser.isEmpty()) {
                Toast.makeText(LoginActivity.this, "กรุณาใส่ username || password"
                        , Toast.LENGTH_LONG)
                        .show();
            } else {

                getResponseLogin(iduser, passuser, token);

            }
        }else   if (v == ForgotPassword){
            Intent intent = new Intent(LoginActivity.this,
                    ForgotPasswordActivity.class);
            startActivity(intent);
        }
    }

    private void getResponseLogin(final String user, final String pass, final String token) {

        final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "",
                "กรุณารอสักครู่...", true);
        dialog.show();

        Call<Login> call = HTTPManager.getInstances().getService().getUser(user, pass, token);
        call.enqueue(new Callback<Login>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login dao = response.body();
                if (response.isSuccessful()) {
                    if (dao.getSuccess() == 1) {

                        session.setLoggedin(true);

                        Toast.makeText(LoginActivity.this, "" + dao.getName()
                                , Toast.LENGTH_LONG)
                                .show();

                        Intent intent = new Intent(LoginActivity.this,
                                MenuGroupActivity.class);
                        int id = dao.getId();

                        SharedPreferences sp = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("iduser", id);
                        editor.commit();

                        startActivity(intent);
                        finish();

                        // เวลาแจ้งเตือน
                        setTime(id);

                        dialog.dismiss();

                    } else {
                        Toast.makeText(LoginActivity.this, "username password ไม่ถูกต้อง"
                                , Toast.LENGTH_LONG)
                                .show();
                        dialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.toString()
                        , Toast.LENGTH_LONG)
                        .show();

                Log.e("error: ",t.toString());
//                Log.e("error: ",user);
//                Log.e("error: ",pass);
//                Log.e("error: ",token);
                dialog.dismiss();


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTime(int id) {
        //  final Calendar cal = Calendar.getInstance();
        //  final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Call<GetDateDao> call = HTTPManager.getInstances().getService().getdate(id);
        call.enqueue(new Callback<GetDateDao>() {
            @Override
            public void onResponse(Call<GetDateDao> call, Response<GetDateDao> response) {
                GetDateDao dao = response.body();
                if (response.isSuccessful()) {

                    if (dao.getSuccess() == 0) {
                        Log.e("date", "0");
                    }else {

                        if (dao.getData().size() != 0) {
                            for (int i = 0; i < dao.getData().size(); i++) {
                                year = dao.getData().get(i).getYear();
                                month = dao.getData().get(i).getMonth();
                                day = dao.getData().get(i).getDay();

                                hour = dao.getData().get(i).getHh();
                                minute = dao.getData().get(i).getMm();

                                setTimeDate(year, month, day, hour, minute);
                            }
                        }else{
                            Log.e("size ", "0");
                        }

                    }


                } else {
                    try {
                        Toast.makeText(LoginActivity.this,
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
                Toast.makeText(LoginActivity.this, t.toString()
                        , Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    private void setTimeDate(int year, int month, int day, int hour, int minute) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DATE,day);
        cal.set(Calendar.HOUR,hour);
        cal.set(Calendar.MINUTE,minute);

        cal.set(year, month - 1, day, hour, minute, 00);
        Log.e("date", i+" " + sdf.format(cal.getTime()));

        Intent intent = new Intent(LoginActivity.this, MyService.class);
        pIntent = PendingIntent.getService(LoginActivity.this, i, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pIntent);

        i++;
    }
}
