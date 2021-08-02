package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText uname_edt,pass_edt;
    Button loginbtn, signupbtn;
    DBHelper db;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        uname_edt = (EditText) findViewById(R.id.et_un_mainctivity);
        pass_edt = (EditText) findViewById(R.id.et_pw_mainactivity);
        loginbtn = (Button) findViewById(R.id.btn_login_mainactivity);
        signupbtn = (Button) findViewById(R.id.btn_signup_mainactivity);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=uname_edt.getText().toString();
                String pass=pass_edt.getText().toString();
                boolean res=db.checklogin(user,pass);
                if (res == true){
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
                 pd=new ProgressDialog(MainActivity.this);
                pd.show();;
                pd.setContentView(R.layout.progress_dialog);
                pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pd.dismiss();
    }
}