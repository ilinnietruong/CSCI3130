package com.example.a2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usrname = (EditText) findViewById(R.id.username);
        final EditText email = (EditText) findViewById(R.id.email);
        final TextView success =(TextView)findViewById(R.id.success);
        Button signup = (Button) findViewById((R.id.button));

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String outputUsrname = usrname.getText().toString();
                final String outputEmail = email.getText().toString();

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

                if(validator.successLogin()==true)
                    success.setVisibility(View.VISIBLE);
                    success.setText("Welcome "+outputUsrname+"! A welcome email was sent to: "+outputEmail);
            }
        });
    }
}
