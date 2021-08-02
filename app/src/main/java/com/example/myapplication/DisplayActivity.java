package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<User> arr;
    //create ListAdapter class reference
    ListAdapter listAdapter;

    MenuItem menuItem;
    TextView budgecount;
    int pendingnotifi=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lv=(ListView)findViewById(R.id.lv_item);
        DBHelper db=new DBHelper(getApplicationContext());
        arr=new ArrayList<>();
        arr=db.retrivedata();
        listAdapter=new ListAdapter(this,arr);
        lv.setAdapter(listAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arr=db.selectbyId();
                User u = arr.get(position);
                Intent i=new Intent(DisplayActivity.this,UpdateActivity.class);
                String fname = u.getFname();
                String lname=u.getLname();
                String email=u.getEmail();
                String uname=u.getUname();
                String pass=u.getPass();
                String gender=u.getGender();
                String utype=u.getUtype();
                String hobby1=u.getHobby1();
                String hobby2=u.getHobby2();
                String hobby3=u.getHobby3();

                i.putExtra("ID",id);
                i.putExtra("Fname",fname);
                i.putExtra("Lname",lname);
                i.putExtra("Email",email);
                i.putExtra("Uname",uname);
                i.putExtra("Password",pass);
                i.putExtra("Gender",gender);
                i.putExtra("Usertype",utype);
                i.putExtra("Hobby1",hobby1);
                i.putExtra("Hobby2",hobby2);
                i.putExtra("Hobby3",hobby3);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        menuItem=menu.findItem(R.id.action_notification);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i=new Intent(DisplayActivity.this,NotificationMessageActivity.class);
                startActivity(i);
                return true;
            }


        });

      /*  if (pendingnotifi==0) {
            menuItem.setActionView(null);
        }else {
            menuItem.setActionView(R.layout.notification);
            View view=menuItem.getActionView();
            budgecount=view.findViewById(R.id.budge_count);
            budgecount.setText(String.valueOf(pendingnotifi));
        }*/
        return super.onCreateOptionsMenu(menu);
    }
}