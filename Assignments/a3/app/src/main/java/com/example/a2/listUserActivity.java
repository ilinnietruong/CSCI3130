package com.example.a2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class listUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        final ListView userView = (ListView)findViewById(R.id.listUser);
        final List<String> userList = new ArrayList<>();
        final FirebaseFirestore dataInfo = FirebaseFirestore.getInstance();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(listUserActivity.this, android.R.layout.simple_list_item_2,android.R.id.text1, userList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView textChanged = (TextView) view.findViewById(android.R.id.text1);


                textChanged.setTextColor(Color.MAGENTA);
                return view;
            }
        };


       dataInfo.collection("userInfo").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentSnapshot doc : queryDocumentSnapshots) {

                    userList.add(doc.getString("Username"));
                    userList.add(doc.getString("E-mail"));
                }

                arrayAdapter.notifyDataSetChanged();
                userView.setAdapter(arrayAdapter);
            }
        });
    }
}
