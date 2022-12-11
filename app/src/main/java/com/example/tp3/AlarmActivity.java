package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity{

    private TimePicker timePicker;
    private EditText alarmNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        findViewById(R.id.button_cancel).setOnClickListener((View v) -> {finish();});
        findViewById(R.id.button_confirm).setOnClickListener(this::confirmAlarm);
        timePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmNameView = (EditText) findViewById(R.id.edittext_second);

        createNotificationChannel();
    }

    private void confirmAlarm(View view) {

        Calendar calendar = Calendar.getInstance();

        long currentTime = System.currentTimeMillis();

        int timerMinute = timePicker.getMinute();
        int timerHour = timePicker.getHour();

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, timerMinute);
        calendar.set(Calendar.HOUR_OF_DAY, timerHour);

        long timerTime = calendar.getTimeInMillis();

        // Invalid time
        if(timerTime < currentTime) {
            Toast errorToast = Toast.makeText(this, getString(R.string.tempsInvalide), Toast.LENGTH_SHORT);
            errorToast.show();
            return;
        }

        // Valid time
        Toast.makeText(this, getString(R.string.alarmeCree), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AlarmActivity.this, TimerBroadcast.class);
        intent.putExtra("alarmName", alarmNameView.getText().toString());
        PendingIntent pIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, timerTime, pIntent);
    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel("timerNotify", "timerNotify", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}