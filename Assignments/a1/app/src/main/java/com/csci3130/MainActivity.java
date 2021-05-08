package com.csci3130;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = (Button) findViewById(R.id.button);
        enterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText anyWord = (EditText) findViewById(R.id.input);
                TextView resultTextView = (TextView) findViewById(R.id.textView);

                String outputWord = anyWord.getText().toString();
                resultTextView.setText(outputWord + "");


            }
        });
    }
}
