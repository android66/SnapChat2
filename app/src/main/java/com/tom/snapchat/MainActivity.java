package com.tom.snapchat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeNotification();
    }
    private void makeNotification() {
        Bitmap bmp = BitmapFactory
                .decodeResource(getResources(), R.drawable.pig64);
        Notification.BigPictureStyle big =
                new Notification.BigPictureStyle();
        big.bigPicture(
                BitmapFactory.decodeResource(getResources(), R.drawable.pig64))
                .setSummaryText("bla bla bla");
        Intent intent = new Intent(this, ChatActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(new Intent(this, MainActivity.class));
        stackBuilder.addNextIntent(intent);
//        PendingIntent pendingIntent = PendingIntent.getActivities(
//                this, 0, new Intent[]{backIntent, intent}, PendingIntent. );
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT);
        /*
        PendingIntent pendingIntent =
                PendingIntent.getActivity( this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
                */
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.pig32)
                .setContentTitle("This is Title")
                .setContentText("This is Text")
                .setContentInfo("This is Info")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build();
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
