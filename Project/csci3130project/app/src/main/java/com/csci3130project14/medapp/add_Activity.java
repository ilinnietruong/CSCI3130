package com.csci3130project14.medapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class add_Activity extends AppCompatActivity {
    EditText inputMed;
    EditText inputMedDosage;
    EditText inputTimeOfDay;
    EditText inputDayOfWeek;
    Button addMedi;
    Button back2View;

    ArrayList<Medication> newMedsList;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_);

        //get all the view elements
        inputMed = (EditText) findViewById(R.id.medTxt);
        inputMedDosage = (EditText) findViewById(R.id.medDosage_EditText);
        inputTimeOfDay = (EditText) findViewById(R.id.medTimeOfDay_EditText);
        inputDayOfWeek = (EditText) findViewById(R.id.medDayOfWeek_EditText);

        addMedi = (Button) findViewById(R.id.addMed);
        back2View = (Button) findViewById(R.id.backtoView);


        //used to get a copy of the user's current medication and add new ones
        newMedsList = new ArrayList<Medication>();

        final String result = inputMed.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("medicationList");
        DatabaseReference userCurrentMeds = reference.child(currentUser.getUid());

        //get a copy of the current list of medication. New medication will be added to this list
        userCurrentMeds.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot medListItem : dataSnapshot.getChildren()) {
                    String key = medListItem.getKey();
                    Medication medItem = medListItem.getValue(Medication.class);
                    Log.d("currItem", medItem.getMedcationName() +" ");
                    newMedsList.add(medItem);
                    Log.d("Item count", Integer.toString(newMedsList.size()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        onBtnClick(currentUser);

    }

    /**
     * Separate the clicking functionality of the current user's activity
     *
     * @param user The current user that's logged into the app
     */
    public void onBtnClick(final FirebaseUser user) {
        back2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clickBackView = new Intent(add_Activity.this, viewActivity.class);
                finish();
                startActivity(clickBackView);
            }
        });

        //add a new medication
        addMedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = inputMed.getText().toString();
                String resultDosage = inputMedDosage.getText().toString();
                String resultTime = inputTimeOfDay.getText().toString();
                String resultDay = inputDayOfWeek.getText().toString();

                reference = FirebaseDatabase.getInstance().getReference("medicationList");

                //check for empty entries for each medication field
                if (result.isEmpty()) {
                    inputMed.setError("Please input a medicine");

                }
                if (resultDosage.isEmpty()) {
                    inputMed.setError("Please input dosage amount");

                }
                if (resultTime.isEmpty()) {
                    inputMed.setError("Please input a time to take medication");

                }
                if (resultDay.isEmpty()) {
                    inputMed.setError("Please input a day to have medication" );

                }

                else {

                    //check for duplicate entries
                    Medication newMed = new Medication(result, resultDosage, resultTime, resultDay);
                    boolean isInList = false;

                    for(Medication i : newMedsList) {
                        if (newMed.getMedcationName().equals(i.getMedcationName())) {

                            isInList = true;
                            break;
                        }


                    }
                    if(isInList == false) { //only if it's not on the list
                        newMedsList.add(newMed);
                        reference.child(user.getUid()).child(newMed.getMedcationName()).setValue(newMed);
                        Toast.makeText(add_Activity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{ //already in list
                        Toast.makeText(add_Activity.this, "Already in list", Toast.LENGTH_SHORT).show();
                    }




                }
            }
        });
    }
}




