package com.example.a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;

public class welcomeActivity extends AppCompatActivity {
    public final static String welcomeUsr = "welcomeUsrString";
    FirebaseFirestore userDB;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView userPop = (TextView) findViewById(R.id.userText);
        counter = 0;
        Intent intent = getIntent();
        String preferredUserName = intent.getStringExtra(welcomeActivity.welcomeUsr);
        userPop.setText(preferredUserName);


        ConstraintLayout clickLayout = (ConstraintLayout) findViewById(R.id.welcomeLayout);

        clickLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                counter++;
                if(counter < 8) {
                    Toast clickAnnouncement = Toast.makeText(welcomeActivity.this, "click " + counter, Toast.LENGTH_SHORT);
                    clickAnnouncement.setGravity(Gravity.CENTER, 0, 0);
                    clickAnnouncement.show();
                }
                else{
                    Intent listUserActivity = new Intent(welcomeActivity.this, listUserActivity.class);
                    startActivity(listUserActivity);
                    Toast.makeText(welcomeActivity.this, "Done " , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



