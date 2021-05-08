package com.csci3130project14.medapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    FirebaseAuth mCurrentUserAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference userMedListReference;

    //The delete confirmation popup will have a text to delete target medication
    TextView confirmationMessage;
    Button confirmYesButton;
    Button confirmNoButton;
    ArrayList<Medication> mUserMedicationCopy;

    //constants to represent the data for target medication
    private static final String MED_NAME = "Name of medication";
    private static final String MED_DOSAGE = "Medication Dosage";
    private static final String MED_TIME = "Time of medication";
    private static final String MED_DAY = "Day of medication";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        //same as the add activity (can get some boilerplate code for database)

        //get the view objects
        confirmationMessage = (TextView) findViewById(R.id.confirmation_textview);
        confirmYesButton = (Button) findViewById(R.id.confirm_yesDelete_button);
        confirmNoButton = (Button) findViewById(R.id.confirm_noDelete_button);

        Intent medItemIntent = getIntent();
        final String nameToDelete = medItemIntent.getStringExtra(MED_NAME);
        final String dosageToDelete = medItemIntent.getStringExtra(MED_DOSAGE);
        final String timeToDelete = medItemIntent.getStringExtra(MED_TIME);
        final String dayToDelete = medItemIntent.getStringExtra(MED_DAY);

        confirmationMessage.setText("Are you sure you want to delete "+ nameToDelete + "?");


        //get the current user from Firebase
        mCurrentUserAuth = FirebaseAuth.getInstance();
        mCurrentUser = mCurrentUserAuth.getCurrentUser();

        //get a copy of the list of medication
        userMedListReference = FirebaseDatabase.getInstance().getReference("medicationList");
        final DatabaseReference userCurrentMeds = userMedListReference.child(mCurrentUser.getUid());


        confirmYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DeleteActivity.this, nameToDelete
                        + " shall be deleted.", Toast.LENGTH_SHORT).show();

                /*
                * match the values from intent with the ones in database. If all of the values match
                * then get that item's unique id
                *
                */
                final String[] medKey = new String[1];
                userCurrentMeds.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot meds : dataSnapshot.getChildren()){

                            Medication m = meds.getValue(Medication.class);

                            //match every single value
                            boolean nameFlag = false;
                            assert m != null;
                            if(m.getMedcationName().equals(nameToDelete)){
                                nameFlag = true;
                            }

                            boolean dosageFlag = false;
                            if(m.getMedicationDosage().equals((dosageToDelete))){
                                dosageFlag = true;
                            }

                            boolean timeFlag = false;
                            if(m.getMedicationTimeOfDay().equals((timeToDelete))){
                                timeFlag = true;
                            }

                            boolean dayFlag = false;
                            if(m.getMedicationDayOfWeek().equals((dayToDelete))){
                                dayFlag = true;
                            }

                            //final verification
                            if(nameFlag == true && dosageFlag == true && dayFlag == true
                                    && timeFlag == true){

                                medKey[0] = meds.getKey();
                                //delete
                                meds.getRef().setValue(null);
                                Intent clickBackView = new Intent(DeleteActivity.this, viewActivity.class);
                                finish();
                                startActivity(clickBackView);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        assert nameToDelete != null;
                        Log.d("Delete error: " , nameToDelete);
                    }
                });
                /*
                * Finally, for the delete thing to actually work, remove that item with said  id
                * */


            }
        });

        confirmNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DeleteActivity.this, nameToDelete
                        + " NOOO!!", Toast.LENGTH_SHORT).show();
                Intent clickBackView = new Intent(DeleteActivity.this, viewActivity.class);
                finish();
                startActivity(clickBackView);
            }
        });

    }

    public boolean deleteMedicationFromList(Medication m){
        if(mUserMedicationCopy.isEmpty()){
            return false;
        }
        return mUserMedicationCopy.remove(m);

    }
}
