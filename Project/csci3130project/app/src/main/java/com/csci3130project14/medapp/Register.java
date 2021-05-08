package com.csci3130project14.medapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.concurrent.atomic.AtomicInteger;


public class Register extends AppCompatActivity {


    //get instance of Firebase realtime database
    FirebaseDatabase medUserDatabase;

    DatabaseReference medDB;
    DatabaseReference medDBUserEntry;

    FirebaseAuth mUserRegistrationAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //obtain the necessaty view objects
        final EditText fNameRegister = (EditText) findViewById(R.id.firstNameRegister_EditText);
        final EditText lNameRegister = (EditText) findViewById(R.id.lastNameRegister_EditText);
        final EditText emailRegister = (EditText) findViewById(R.id.emailRegister_EditText);
        final EditText pWordRegister = (EditText) findViewById(R.id.passwordRegister_EditText);
        final EditText pWord2Register = (EditText) findViewById(R.id.confirmPasswordRegister_EditText);

        final Button signup = (Button) findViewById(R.id.signUpButton);
        final Button back = (Button) findViewById(R.id.goBack_MainPage_Button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fNameS = fNameRegister.getText().toString();
                final String lNameS = lNameRegister.getText().toString();
                final String emS = emailRegister.getText().toString();
                final String pWordS = pWordRegister.getText().toString();
                final String pWord2S = pWord2Register.getText().toString();



                Validator registrationValidator = new Validator(fNameS, lNameS, emS, pWordS);

                //if this method is successful then proceed to enter user data into database
                if(validateUserRegistration(registrationValidator, fNameRegister, lNameRegister,
                        emailRegister,  pWordRegister, pWord2Register)){

                    //authenticate user
                    mUserRegistrationAuth = FirebaseAuth.getInstance();
                    //code snippet taken from: https://stackoverflow.com/questions/37798560/how-do-i-add-username-to-user-when-using-firebase-android
                    mUserRegistrationAuth.createUserWithEmailAndPassword(emS, pWordS).addOnCompleteListener(
                            Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //create the new user
                                //first, create a user object
                                final MedAppUser newMedAppUser = new MedAppUser(fNameS, lNameS, emS, pWordS);

                                medUserDatabase = FirebaseDatabase.getInstance();  //get instance of database
                                medDB = medUserDatabase.getReference("medAppUsers"); //get reference to 'root' node
                                medDBUserEntry = medDB.child(task.getResult().getUser().getUid());
                                medDBUserEntry.setValue(newMedAppUser);
                                Toast.makeText(Register.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                Intent switchPageView = new Intent(Register.this, Login.class);
                                startActivity(switchPageView);
                                finish();
                            }
                            else{
                                Toast.makeText(Register.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }

    private void createNewUser(User u){
        //String newUseID = u.getUid();
    }

    /**
     * Validate the data entered by the user
     *
     * @param registrationValidator the object containing the data entered by the user
     * @return true if all pieces of data are valid
     */
    private boolean validateUserRegistration(Validator registrationValidator,
                                             EditText fName, EditText lName, EditText uEmail,
                                             EditText uPassword, EditText uConfirmPasswd){


        //Determine if the names are valid()
        if(registrationValidator.emptyName(registrationValidator.getFirstName())){
            fName.setError("Empty. Invalid first name.");
        }

        if(registrationValidator.emptyName(registrationValidator.getLastName())){
            lName.setError("Empty. Invalid last name.");
        }

        //Determine whether the e-mail is Valid
        if (!registrationValidator.validEmail()) {
            uEmail.setError("Invalid e-mail.Ex:johndoe@dal.ca");
        }

        //Determine whether the password is valid
        if (!registrationValidator.isValidPassword()) {
            //then check for what's wrong with password

            if (!registrationValidator.passowordLengthCheck()) {
                uPassword.setError("Password must be between 5 and 10 characters long.");
            }
            else {
                uPassword.setError("At least one upper case, one lower case, and one integer.");
            }

        }



        //Determine the password same as the confirm password
        if(!registrationValidator.samePassword(uConfirmPasswd.getText().toString())){
            uConfirmPasswd.setError("The password is not the same.");
        }


        //if any of the above conditions fail, the method shall return false
        return registrationValidator.successfulRegister();
    }


    private void openMain(){
        Intent switchPage = new Intent(Register.this,MainActivity.class);
        startActivity(switchPage);
    }
}
