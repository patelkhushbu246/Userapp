package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText fname_edt, lname_edt, email_edt, uname_edt, pass_edt, cpass_edt;
    RadioGroup rbg;
    RadioButton malerb, femalerb;
    Spinner utype_spinner;
    TextView hobbies_tv;
    CheckBox movies_check, music_check, travelling_check;
    Button signupbtn;
    DBHelper db;

    CircleImageView imageView;
    //PERMISSION CODE
    private static final  int CAMARA_REQUEST_CODE=100;
    private static final  int STORAGE_REQUEST_CODE=101;
    //IMAGE CODE
   private static final  int IMAGE_PICK_CAMARA_CODE=101;
    private static final  int IMAGE_PICK_GALLARY_CODE=101;

    String[] camarapermission;
    String[] storagepermission;
    private Uri imguri;


    String[] usertype = {"Admin", "User"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DBHelper(this);
        fname_edt = (EditText) findViewById(R.id.et_fn);
        lname_edt = (EditText) findViewById(R.id.et_ln);
        email_edt = (EditText) findViewById(R.id.et_email);
        uname_edt = (EditText) findViewById(R.id.et_un);
        pass_edt = (EditText) findViewById(R.id.et_pass);
        cpass_edt = (EditText) findViewById(R.id.et_cpass);
        rbg = (RadioGroup) findViewById(R.id.rg_gender);
        utype_spinner = (Spinner) findViewById(R.id.sp_ut);
        hobbies_tv = (TextView) findViewById(R.id.tv_hobbies);
        movies_check = (CheckBox) findViewById(R.id.cb_movie);
        music_check = (CheckBox) findViewById(R.id.cb_music);
        travelling_check = (CheckBox) findViewById(R.id.cb_travel);
        signupbtn = (Button) findViewById(R.id.btn_signup_registration);

        malerb = (RadioButton) findViewById(R.id.rb_male);
        femalerb = (RadioButton) findViewById(R.id.rb_female);

        camarapermission=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagepermission=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, usertype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        utype_spinner.setAdapter(adapter);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   String fname = fname_edt.getText().toString().trim();
                String lname = lname_edt.getText().toString().trim();
                String email = email_edt.getText().toString().trim();
                String uname = uname_edt.getText().toString().trim();
                String pwd = pass_edt.getText().toString().trim();
                String cpwd = cpass_edt.getText().toString().trim();
                RadioButton checkid = findViewById(rbg.getCheckedRadioButtonId());
                String gender = checkid.getText().toString().trim();
                String usertype = utype_spinner.getItemAtPosition(utype_spinner.getSelectedItemPosition()).toString().trim();
                //StringBuffer sb=new StringBuffer();
                String hobby1 = "movie";
                String hobby2 = "music";
                String hobby3 = "travelling";
                if (movies_check.isChecked()) {
                    hobby1 = movies_check.getText().toString();
                }
                if (music_check.isChecked()) {
                    hobby2 = music_check.getText().toString();
                }
                if (travelling_check.isChecked()) {
                    hobby3 = travelling_check.getText().toString();
                }

                if (pwd.equals(cpwd)) {
                    db.adduser(fname, lname, email, uname, pwd, gender, usertype, hobby1, hobby2, hobby3);
                    Toast.makeText(RegistrationActivity.this, "You have signup", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }*/
                insertdata();

            }
        });
        imageView=findViewById(R.id.pro_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagepickDialoag();
            }
        });


    }

    private void insertdata() {
        String fname = fname_edt.getText().toString().trim();
        String lname = lname_edt.getText().toString().trim();
        String email = email_edt.getText().toString().trim();
        String uname = uname_edt.getText().toString().trim();
        String pwd = pass_edt.getText().toString().trim();
        String cpwd = cpass_edt.getText().toString().trim();
        RadioButton checkid = findViewById(rbg.getCheckedRadioButtonId());
        String gender = checkid.getText().toString().trim();
        String usertype = utype_spinner.getItemAtPosition(utype_spinner.getSelectedItemPosition()).toString().trim();
        //StringBuffer sb=new StringBuffer();
        String hobby1 = " ";
        String hobby2 = " ";
        String hobby3 = " ";
        if (movies_check.isChecked()) {
            hobby1 = movies_check.getText().toString();
        }
        if (music_check.isChecked()) {
            hobby2 = music_check.getText().toString();
        }
        if (travelling_check.isChecked()) {
            hobby3 = travelling_check.getText().toString();
        }

        if (pwd.equals(cpwd)) {
           long id= db.adduser(""+fname,""+ lname,""+ email,""+ uname,""+ pwd,""+ gender,"" +usertype,""+ hobby1,""+ hobby2,""+ hobby3,""+imguri);
            Toast.makeText(RegistrationActivity.this, "You have signup"+id, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(RegistrationActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
        }
    }

    private void imagepickDialoag() {
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,IMAGE_PICK_GALLARY_CODE);
    }

    private void pickFromGallery() {
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,IMAGE_PICK_GALLARY_CODE);
    }

    private void pickFromCamara() {
        ContentValues cv=new ContentValues();
        cv.put(MediaStore.Images.Media.TITLE,"Image title");
        cv.put(MediaStore.Images.Media.DESCRIPTION,"Image description");
        imguri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,cv);
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT,imguri);
        startActivityForResult(i,IMAGE_PICK_CAMARA_CODE);
    }

    private boolean checkstoragepermission(){
        boolean res= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return  res;
    }

    private void requeststoragepermission(){
        ActivityCompat.requestPermissions(this,storagepermission,STORAGE_REQUEST_CODE);
    }

    private boolean checkcamarapermission(){
        boolean res= ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean res1= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return res && res1;
    }

    private void requestcamarapermission(){
        ActivityCompat.requestPermissions(this,camarapermission,CAMARA_REQUEST_CODE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMARA_REQUEST_CODE:{
                if (grantResults.length>0){
                    boolean camaraAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if (camaraAccepted && storageAccepted){
                        pickFromCamara();
                    }else {
                        Toast.makeText(this, "Camara & Storage are required", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            break;
            case STORAGE_REQUEST_CODE:{
                if (grantResults.length>0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Storage is required", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK){
            if (requestCode==IMAGE_PICK_GALLARY_CODE){
                CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1,1).start(this);

            }else if(requestCode==IMAGE_PICK_CAMARA_CODE){
                CropImage.activity(imguri).setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1,1).start(this);

            }else if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resulturi = result.getUri();
                    imguri = resulturi;
                    imageView.setImageURI(resulturi);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error=result.getError();
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}