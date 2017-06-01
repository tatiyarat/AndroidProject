package com.techin1.androidproject.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.techin1.androidproject.MainActivity;
import com.techin1.androidproject.R;
import com.techin1.androidproject.activity.StatusActivity;
import com.techin1.androidproject.fragment.StatusFragment;

/**
 * Created by TECHIN1 on 23/11/2559.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("message"));
    }

    private void showNotification(String message) {

        Intent i = new Intent(this,StatusActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("FCM Test")
                .setWhen(System.currentTimeMillis())
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_communication_email)
                .setContentIntent(pendingIntent);

        Uri sound = RingtoneManager.getDefaultUri(Notification.DEFAULT_VIBRATE);
        builder.setSound(sound);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.ic_content_drafts);
        builder.setLargeIcon(picture);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(1,builder.build());

    }
}
