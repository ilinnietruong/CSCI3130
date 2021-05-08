package com.csci3130project14.medapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Notification extends AppCompatActivity {
    Button btNotification;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        btNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String message = "This is a notification example.";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        Notification.this
                )
                        .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                        .setContentTitle("New Notification")
                        .setContentText(message)
                        .setAutoCancel(true);
                Intent intent = new Intent(Notification.this,
                        Notification.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);

                PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(
                        Context.NOTIFICATION_SERVICE
                );
                notificationManager.notify(0, builder.build());

            }
        });

    }


}
