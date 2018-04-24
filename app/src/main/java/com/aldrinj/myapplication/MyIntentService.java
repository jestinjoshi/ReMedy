package com.aldrinj.myapplication;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;

public class MyIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;


    public MyIntentService() {
        super("My");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Reminder!");
        builder.setContentText("Time to eat your medicines");
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        Intent notify = new Intent(this, MedicineListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notify, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID,notificationCompat);
    }
}
