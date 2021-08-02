package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateActivity extends AppCompatActivity {

    EditText fntxt, lntxt, emailtxt, untxt, passtxt,cpasstxt;
    TextView txtid, gentxt, utypetxt, hobby1txt, hobby2txt, hobby3txt;
    FloatingActionButton floatingActionButton, deletefloating;


    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        fntxt = (EditText) findViewById(R.id.fn_tv_details);
        lntxt = (EditText) findViewById(R.id.ln_tv_details);
        emailtxt = (EditText) findViewById(R.id.email_tv_details);
        untxt = (EditText) findViewById(R.id.un_tv_details);
        passtxt = (EditText) findViewById(R.id.pwd_tv_details);
        cpasstxt = (EditText) findViewById(R.id.cpwd_tv_details);
        gentxt = (TextView) findViewById(R.id.gen_tv_details);
        utypetxt = (TextView) findViewById(R.id.utype_tv_details);
        hobby1txt = (TextView) findViewById(R.id.hobbie1_tv_details);
        hobby2txt = (TextView) findViewById(R.id.hobbie2_tv_details);
        hobby3txt = (TextView) findViewById(R.id.hobbie3_tv_details);
        txtid=(TextView)findViewById(R.id.regid);


        db=new DBHelper(UpdateActivity.this);
        Intent i=getIntent();
        int strid=i.getIntExtra("id",0);
        String fname=i.getStringExtra("Fname");
        String lname=i.getStringExtra("Lname");
        String email=i.getStringExtra("Email");
        String uname=i.getStringExtra("Uname");
        String pass=i.getStringExtra("Password");
        String gen=i.getStringExtra("Gender");
        String utype=i.getStringExtra("Usertype");
        String hobby1=i.getStringExtra("Hobby1");
        String hobby2=i.getStringExtra("Hobby2");
        String hobby3=i.getStringExtra("Hobby3");
        txtid.setText(""+strid);
        fntxt.setText(fname);
        lntxt.setText(lname);
        emailtxt.setText(email);
        untxt.setText(uname);
        passtxt.setText(pass);
        gentxt.setText(gen);
        utypetxt.setText(utype);
        hobby1txt.setText(hobby1);
        hobby2txt.setText(hobby2);
        hobby3txt.setText(hobby3);


        floatingActionButton=findViewById(R.id.update_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=txtid.getText().toString();
                String fname=fntxt.getText().toString();
                String lname=lntxt.getText().toString();
                String email=emailtxt.getText().toString();
                String uname=untxt.getText().toString();
                String pass=passtxt.getText().toString();
                String cpwd=cpasstxt.getText().toString();
                boolean res=db.update(id,fname,lname,email,uname,pass);
                if (res == true) {
                    if (pass.equals(cpwd)) {

                        Toast.makeText(UpdateActivity.this, "Update Successfully...", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(UpdateActivity.this, "Password id not match", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(UpdateActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        deletefloating=findViewById(R.id.delete_btn);
        deletefloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=untxt.getText().toString();
                boolean res=db.delete(uname);
                if (res==true) {

                    Toast.makeText(UpdateActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateActivity.this, DisplayActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(UpdateActivity.this, "Admin Can Delete", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}