package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NotificationMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_message);


        Button msgbtn=findViewById(R.id.msg_btn);
        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager notificationManager=(NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                Intent i1=new Intent(NotificationMessageActivity.this,MessageActivity.class);
                i1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i1.putExtra("no",false);
                PendingIntent pendingIntent=PendingIntent.getActivity(NotificationMessageActivity.this,0,i1,PendingIntent.FLAG_ONE_SHOT
                );
                Intent i2=new Intent(NotificationMessageActivity.this,MessageActivity.class);
                i2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i2.putExtra("yes",true);
                PendingIntent pendingIntent1=PendingIntent.getActivity(NotificationMessageActivity.this,0,i2,PendingIntent.FLAG_ONE_SHOT
                );
                Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder builder=new NotificationCompat.Builder(NotificationMessageActivity.this,getString(R.string.app_name));
                 builder.setContentTitle("Request");
                 builder.setContentText("Are you sure you want to accept");
                 builder.setSmallIcon(R.drawable.ic_message);
                 builder.setSound(uri);
                 builder.setAutoCancel(true);
                 builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                 builder.addAction(R.drawable.ic_launcher_foreground,"yes",pendingIntent);
                builder.addAction(R.drawable.ic_launcher_foreground,"no",pendingIntent1);
                notificationManager.notify(1,builder.build());


            }
        });


    }
}