package com.example.tp3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class TimerBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String title;

        if(intent.getStringExtra("alarmName") != null)
            title = intent.getStringExtra("alarmName");
        else
            title = context.getString(R.string.timerTermine);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "timerNotify")
                .setSmallIcon(R.drawable.ic_baseline_add_24)
                .setContentTitle(title)
                .setContentText(null)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(80, builder.build());
    }
}
