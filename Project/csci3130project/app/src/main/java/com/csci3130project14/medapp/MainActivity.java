package com.csci3130project14.medapp;
//database login : kcassidycsci@gmail.com password Skorbar15
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.loginButton);
        Button register = (Button) findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistration();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }


    /**
     * Take the user to the registration page
     */
    private void openRegistration () {
        Intent switchPage = new Intent(MainActivity.this, Register.class);
        startActivity(switchPage);
    }

    /**
     * Take a current user to a login prompt
     */
    private void openLogin () {
        Intent switchPage2 = new Intent(MainActivity.this, Login.class);
        startActivity(switchPage2);
    }

}

