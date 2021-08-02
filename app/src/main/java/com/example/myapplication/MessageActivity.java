package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        TextView msgtxt=findViewById(R.id.message_tv);
        NotificationManager notificationManager=(NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        if (getIntent().hasExtra("yes")) {
            msgtxt.setText("you accepted");
        }else if (getIntent().hasExtra("no")){
            msgtxt.setText("you reject request");
        }
    }
}