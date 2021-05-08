package com.example.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText usrname,email;
    private String outputUsrname, outputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Map<String,Object> userInfo = new HashMap<>();

        usrname = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        final TextView success =(TextView)findViewById(R.id.success);
        Button signup = (Button) findViewById((R.id.button));

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final FirebaseFirestore infoDB= FirebaseFirestore.getInstance();
                outputUsrname = usrname.getText().toString();
                 outputEmail = email.getText().toString();

                Validation validator = new Validation(outputUsrname,outputEmail);


                if(validator.isEmpty() == true) {
                    usrname.setError("Username is required.");
                }

                if(validator.validEmail()==false && outputEmail.length()==0){
                    email.setError("Empty. Invalid e-mail.");
                }
                else if (validator.validEmail()==false){
                    email.setError("Invalid e-mail.Ex:johndoe@dal.ca");
                }

                //Add the username and Email into the Firestore
                if(validator.successLogin()==true) {
                    userInfo.put("Username", outputUsrname);
                    userInfo.put("E-mail", outputEmail);

                    infoDB.collection("userInfo")
                            .add(userInfo)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(MainActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                                }
                            });
                    success.setVisibility(View.VISIBLE);
                    success.setText("Welcome " + outputUsrname + "! A welcome email was sent to: " + outputEmail);
                    goToWelcomePage();
                }
            }
        });
    }
//[REFACTOR] go to another activity
    public void goToWelcomePage(){
        usrname = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        outputUsrname = usrname.getText().toString();
        outputEmail = email.getText().toString();
        Validation user = new Validation(outputUsrname,outputEmail);

        user.setUsrname(outputUsrname);

        Intent welcomeActivity = new Intent(MainActivity.this, welcomeActivity.class);
       welcomeActivity.putExtra("welcomeUsrString",user.getUsrname());
        startActivity(welcomeActivity);
    }
}
