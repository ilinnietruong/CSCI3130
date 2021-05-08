package com.csci3130project14.medapp;

import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editActivity extends AppCompatActivity {


    //constants to represent the data for target medication
    private static final String MED_NAME = "Name of medication";
    private static final String MED_DOSAGE = "Medication Dosage";
    private static final String MED_TIME = "Time of medication";
    private static final String MED_DAY = "Day of medication";


    FirebaseAuth mCurrentUserAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference userMedListReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit);

        mCurrentUserAuth = FirebaseAuth.getInstance();
        mCurrentUser = mCurrentUserAuth.getCurrentUser();

        userMedListReference = FirebaseDatabase.getInstance().getReference("medicationList");
        final DatabaseReference userCurrentMeds = userMedListReference.child(mCurrentUser.getUid());

        Button save = (Button) findViewById(R.id.saveButton); //to confirm changes
        Button back = (Button) findViewById(R.id.backButton); //to cancel adding medication

        final EditText medEditName = (EditText) findViewById(R.id.medEditName);
        final EditText medEditDosage = (EditText) findViewById(R.id.medEditDosage);
        final EditText medEditTime = (EditText) findViewById(R.id.medEditTime);
        final EditText medEditDay = (EditText) findViewById(R.id.medEditDay);

        //get the values of the medication to be edited. These shall serve as placeholders
        // for the edit fields
        Intent medItemIntent = getIntent();
        final String nameToEdit = medItemIntent.getStringExtra(MED_NAME);
        final String dosageToEdit = medItemIntent.getStringExtra(MED_DOSAGE);
        final String timeToEdit = medItemIntent.getStringExtra(MED_TIME);
        final String dayToEdit = medItemIntent.getStringExtra(MED_DAY);

        medEditName.setText(nameToEdit);
        medEditDosage.setText(dosageToEdit);
        medEditTime.setText(timeToEdit);
        medEditDay.setText(dayToEdit);

        //click back to return to view activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clickBackView = new Intent(editActivity.this, viewActivity.class);
                finish();
                startActivity(clickBackView);
            }
        });

        //save whatever changes have been made and return to the view acivity
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                 * match the values from intent with the ones in database.
                 * If all of the values match, then get that item's reference
                 */

                final String[] medKey = new String[1];
                userCurrentMeds.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot meds : dataSnapshot.getChildren()){

                            Medication m = meds.getValue(Medication.class);

                            //match every single value
                            //this is to ensure that we are editing the correct medication
                            boolean nameFlag = false;
                            assert m != null;
                            if(m.getMedcationName().equals(nameToEdit)){
                                nameFlag = true;
                            }

                            boolean dosageFlag = false;
                            if(m.getMedicationDosage().equals((dosageToEdit))){
                                dosageFlag = true;
                            }

                            boolean timeFlag = false;
                            if(m.getMedicationTimeOfDay().equals((timeToEdit))){
                                timeFlag = true;
                            }

                            boolean dayFlag = false;
                            if(m.getMedicationDayOfWeek().equals((dayToEdit))){
                                dayFlag = true;
                            }

                            //if the four criteria are fulfilled then proceed with edit
                            if(nameFlag == true && dosageFlag == true && dayFlag == true
                                    && timeFlag == true){

                                medKey[0] = meds.getKey();

                                //make changes to the values
                                DatabaseReference targetMed =  meds.getRef();
                                DatabaseReference nameChangeRef = targetMed.child("medcationName");
                                DatabaseReference dayChangeRef = targetMed.child("medicationDayOfWeek");
                                DatabaseReference doseChangeRef = targetMed.child("medicationDosage");
                                DatabaseReference timeChangeRef = targetMed.child("medicationTimeOfDay");

                                //check for empty values
                                String newName = medEditName.getText().toString();
                                String newDose = medEditDosage.getText().toString();
                                String newTime = medEditTime.getText().toString();
                                String newDay = medEditDay.getText().toString();


                                //verify neither of the fields are empty
                                if(newName.isEmpty()){
                                    medEditName.setError("Name can't be empty");
                                }

                                if(newDose.isEmpty()){
                                    medEditDosage.setError("Dosage can't be emoty");
                                }

                                if(newTime.isEmpty()){
                                    medEditTime.setError("Time can;t be empty");
                                }

                                if(newDay.isEmpty()){
                                    medEditDay.setError("Day can't be empty");
                                }

                                //set the changes and be done with it
                                else{
                                    nameChangeRef.setValue(newName);
                                    dayChangeRef.setValue(newDay);
                                    doseChangeRef.setValue(newDose);
                                    timeChangeRef.setValue(newTime);

                                    Toast.makeText(editActivity.this, "Saved " + newName, Toast.LENGTH_SHORT);

                                    Intent clickBackView = new Intent(editActivity.this, viewActivity.class);
                                    finish();
                                    startActivity(clickBackView);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        assert nameToEdit != null;
                        Log.d("Delete error: " , nameToEdit);
                    }
                });

            }
        });
    }


}