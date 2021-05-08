package com.csci3130project14.medapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class viewActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference userInfo;
    private DatabaseReference medInfo;
    private String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        userInfo = FirebaseDatabase.getInstance().getReference("medAppUsers");

        medInfo = FirebaseDatabase.getInstance().getReference("medicationList");
        final TextView welcomeMessg = (TextView) findViewById(R.id.welcomeTxt);

        //final ListView medicationListView = (ListView) findViewById(R.id.viewMed);
        final RecyclerView medicationListView = (RecyclerView) findViewById(R.id.viewMed);

        //medicationListView.setHasFixedSize(true);

        medicationListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Drawable mDivider = ContextCompat.getDrawable(this, R.drawable.recyclerview_divider);


        Button goToAdd = (Button) findViewById(R.id.goToAddBut);
        final TextView noMedText = (TextView) findViewById(R.id.no_med_textview);


        final ArrayList<Medication> userMedicationList = new ArrayList<Medication>();

        //Reference: https://hacksmile.com/how-to-create-android-horizontal-scrolling-recyclerview/
        RecyclerView.LayoutManager viewMedsLayoutManager = new LinearLayoutManager(this);
        medicationListView.setLayoutManager(viewMedsLayoutManager);


        final MedicationRecyclerViewAdapter medInfoAdapter = new MedicationRecyclerViewAdapter(
                viewActivity.this, userMedicationList);
        medicationListView.setAdapter(medInfoAdapter);



        // Create a DividerItemDecoration whose orientation is Horizontal
        DividerItemDecoration hItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL);
        // Set the drawable on it
        hItemDecoration.setDrawable(mDivider);



        //greet the existing user
        userInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String firstName = dataSnapshot.child(uid).child("userFirstName").getValue(String.class);
                String lastName = dataSnapshot.child(uid).child("userLastName").getValue(String.class);
                welcomeMessg.setText("Welcome " + firstName + " " + lastName);
                sendNotification(0);
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //get the user's list of medication
        /*Reference:https://stackoverflow.com/questions/47097787/firebase-android-how-get-children-keys-and-values
         */
        //update the medInfo get child info
        DatabaseReference inInfo = medInfo.child(uid);
        inInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot medListItem : dataSnapshot.getChildren()) {
                    Medication medItem = medListItem.getValue(Medication.class);

                    //check if the item is already in the list
                    userMedicationList.add(medItem);
                }

                //see if the list is empty
                if (userMedicationList.size() == 0) {
                    noMedText.setVisibility(View.VISIBLE);
                } else {
                    noMedText.setVisibility(View.GONE);

                }
                //MedicationListAdapter medInfoAdapter = new MedicationListAdapter(viewActivity.this, userMedicationList);
                medInfoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        goToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //needs to be changed to a dialog popping up
                //create a dialog builder
                Intent openAddMed = new Intent(viewActivity.this, add_Activity.class);
                startActivity(openAddMed);
            }
        });
    }
    //MOVE THIS OTHER STUFF TO WHEREVER WE WANNA USE IT (PROBABLY VIEW ACTIVITY)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "medNotif";
            String description = "Notification for MedApp";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("medapp.notif", name, importance);
            channel.setDescription(description);
            NotificationManager notifManager = getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(channel);
        }
    }

    void sendNotification( final int notificationId) {
        final DatabaseReference inInfo = medInfo.child(uid);
        inInfo.addValueEventListener(new ValueEventListener() {
        String medName="";
        String time="";
        String dayOfWeek="";
        int count =0;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot medListItem : dataSnapshot.getChildren()) {

                    if (medListItem != null) {
                        medName = medListItem.child("medcationName").getValue(String.class);
                        time = medListItem.child("medicationTimeOfDay").getValue(String.class);
                        dayOfWeek = medListItem.child("medicationDayOfWeek").getValue(String.class);

                        String boldTitle = "<b>A Friendly Reminder<b>";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(viewActivity.this, "medapp.notif")
                                .setSmallIcon(R.drawable.baseline_local_hospital_black_18dp)
                                .setContentTitle(Html.fromHtml(boldTitle))
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(medName + " need to be taken on every " + dayOfWeek + " at " + time))
                                .setContentText(medName + " need to be taken on every " + dayOfWeek + " at " + time)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(false);
                        NotificationManagerCompat notifManager = NotificationManagerCompat.from(viewActivity.this);
                        count++;
                        notifManager.notify(count, builder.build());
                    }
                    //inInfo.push();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

