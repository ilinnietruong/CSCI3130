package com.csci3130project14.medapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    FirebaseDatabase medFireDatabase;
    DatabaseReference medDB;
    FirebaseAuth loginAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button login = (Button) findViewById(R.id.getInButton); //login button from login
        final Button register = (Button) findViewById(R.id.RegButton); //register button in login page


        //get the user's email and password
        final EditText userEmail = (EditText) findViewById(R.id.emailButton2);
        final EditText userPassword = (EditText) findViewById(R.id.password);

        //obtain the user's credentials
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String loginEmail = userEmail.getText().toString();
                final String loginPassword = userPassword.getText().toString();




                //Code snippet taken from: https://firebase.google.com/docs/auth/android/start
                loginAuth = FirebaseAuth.getInstance();

                //"authenticate" the user with Firebase
                loginAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(
                        Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent openView = new Intent(Login.this,viewActivity.class);
                                    finish();
                                    startActivity(openView);
                                } else {

                                    Toast.makeText(Login.this, "Login Failed. Please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }

        });

        //if user does not have an account, then the user can proceed to create a new account
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchPage = new Intent(Login.this, Register.class);
                startActivity(switchPage);
            }
        });
    }
}