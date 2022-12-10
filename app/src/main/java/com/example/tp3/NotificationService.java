package com.example.tp3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.security.acl.NotOwnerException;

public class NotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        if(message.getNotification() != null) {
            String title = message.getNotification().getTitle();
            String body = message.getNotification().getBody();

            showNotification(title, body);
        }
    }

    private void showNotification(String title, String body) {
        // FCM Notification Channel setup
        String CHANNEL_ID = "FCM_CHANNEL2";
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel 2", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Canal 2");
        notifManager.createNotificationChannel(channel);

        // Notification setup
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_baseline_add_24)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        notifManager.notify(1, builder.build());
    }
}
