package com.techin1.androidproject.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.techin1.androidproject.R;

public class RemindActivity extends AppCompatActivity implements View.OnClickListener {
    Button bu1;
    Button bu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        bu1 = (Button) findViewById(R.id.bu1);
        bu2 = (Button) findViewById(R.id.bu2);

        bu1.setOnClickListener(this);
        bu2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bu1){
            final AlertDialog.Builder alert = new AlertDialog.Builder(RemindActivity.this);

            alert.setTitle("Alert");
            alert.setMessage("Text");
            alert.setIcon(R.mipmap.ic_launcher);

            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alert.create();

            alert.show();
        }
        if (v == bu2){

            NotificationCompat.Builder notification = new NotificationCompat.Builder(RemindActivity.this);

            notification.setSmallIcon(R.mipmap.ic_launcher);
            notification.setTicker("New notification!!");
            notification.setWhen(System.currentTimeMillis());
            notification.setContentTitle("How to android");
            notification.setContentText("This is anew notification :>");

            Uri sound = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);
            notification.setSound(sound);

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            notification.setLargeIcon(picture);

            PendingIntent myPendinIntent;
            Intent myIntent = new Intent();
            Context myContext = getApplicationContext();

            myIntent.setClass(myContext, InMessageActivity.class);
            myIntent.putExtra("ID",1);
            myPendinIntent = PendingIntent.getActivity(myContext, 0, myIntent,0);
            notification.setContentIntent(myPendinIntent);

            Notification n = notification.build();

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(1,n);


        }
    }
}
