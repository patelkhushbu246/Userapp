package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="userdb";
    private static final String TABLE_REG="userinfo";
    private static final String KEY_ID="ID";
    private static final String KEY_FNAME="Fname";
    private static final String KEY_LNAME="Lname";
    private static final String KEY_EMAIL="Email";
    private static final String KEY_UNAME="Uname";
    private static final String KEY_PASS="Password";
    private static final String KEY_GENDER="Gender";
    private static final String KEY_USERTYPE="Usertype";
    private static final String KEY_HOBBY1="Hobby1";
    private static final String KEY_HOBBY2="Hobby2";
    private static final String KEY_HOBBY3="Hobby3";
    private static final String KEY_PROPIC= "Propic";



    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_REG+"("+KEY_ID+" INTEGER PRIMARY KEY ,"+KEY_FNAME+" TEXT,"+KEY_LNAME+" TEXT,"+KEY_EMAIL+
                " TEXT,"+KEY_UNAME+" TEXT,"+KEY_PASS+" TEXT,"+KEY_GENDER+" TEXT,"+KEY_USERTYPE+" TEXT,"+KEY_HOBBY1+" TEXT,"+KEY_HOBBY2+
                " TEXT,"+KEY_HOBBY3+" TEXT,"+KEY_PROPIC+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+TABLE_REG+"");
        onCreate(db);

    }
    public long adduser(String fname,String lname,String email,String uname, String pass,String gen,String utype,String hobby1,String hobby2,String hobby3,String img){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put("Image",img);
        cv.put("Fname",fname);
        cv.put("Lname",lname);
        cv.put("Email",email);
        cv.put("Uname",uname);
        cv.put("Password",pass);
        cv.put("Gender",gen);
        cv.put("Usertype",utype);
        cv.put("Hobby1", hobby1);
        cv.put("Hobby2", hobby2);
        cv.put("Hobby3",hobby3);
        cv.put("Propic",img);
        long res=db.insert(TABLE_REG,null,cv);
        db.close();
        return res;
    }
    public boolean checklogin(String username, String password){
        String[] column={KEY_ID};
        SQLiteDatabase db=this.getReadableDatabase();
        String selection=KEY_UNAME+"=?"+"AND "+KEY_PASS+"=?";
        String[] selectionArgs={username,password};
        Cursor c=db.query("userinfo",column,selection,selectionArgs,null,null,null);
        int res=c.getCount();
        db.close();
        if(res>0) {
            return true;
        }
        else {
            return false;
        }
    }
    public ArrayList<User> retrivedata(){
        SQLiteDatabase db=this.getReadableDatabase();
        String strqry="select * from "+TABLE_REG;
        Cursor c=db.rawQuery(strqry,null);
        ArrayList<User> arrayList=new ArrayList<>();
        if (c.moveToFirst()){
            do {
                User u=new User();
                u.setId(c.getInt(c.getColumnIndex("ID")));
                u.setFname(c.getString(c.getColumnIndex("Fname")));
                u.setLname(c.getString(c.getColumnIndex("Lname")));
                u.setGender(c.getString(c.getColumnIndex("Gender")));
                u.setHobby1(c.getString(c.getColumnIndex("Hobby1")));
                u.setHobby2(c.getString(c.getColumnIndex("Hobby2")));
                u.setHobby3(c.getString(c.getColumnIndex("Hobby3")));
                arrayList.add(u);
            }while (c.moveToNext());

        }
        db.close();
        return arrayList;

    }
    public ArrayList<User> selectbyId(){
        SQLiteDatabase db=this.getReadableDatabase();
        //String str="select * from userinfo where ID="+"=?";
        Cursor c=db.query(TABLE_REG,null,null,null,null,null,null);
        ArrayList<User> arr=new ArrayList<>();
        while (c.moveToNext()){
            User u = new User();
            u.setId(c.getInt(0));
            u.setFname(c.getString(1));
            u.setLname(c.getString(2));
            u.setEmail(c.getString(3));
            u.setUname(c.getString(4));
            u.setPass(c.getString(5));
            u.setGender(c.getString(6));
            u.setUtype(c.getString(7));
            u.setHobby1(c.getString(8));
            u.setHobby2(c.getString(9));
            u.setHobby3(c.getString(10));
            u.setImg(c.getString(11));
            arr.add(u);

        }

        db.close();
        return arr;
    }
    public boolean update(String id,String fname,String lname,String email,String uname, String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("ID",id);
        cv.put("Fname",fname);
        cv.put("Lname",lname);
        cv.put("Email",email);
        cv.put("Uname",uname);
        cv.put("Password",pass);
        Cursor c=db.rawQuery("select * from userinfo where ID = ?",new String[]{id});
        if (c.getCount()>0) {
            long no = db.update(TABLE_REG, cv, "ID = ?", new String[]{id});
            if (no==-1)
                return false;
            else
                return true;
        }else {
            return false;
        }

    }

    public boolean delete(String uname){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from userinfo where Uname=?",new String[]{(uname)});
        if (c.getCount()>0) {
            long no = db.delete(TABLE_REG, "Uname = ?", new String[]{(uname)});
            if (no==-1)
                return false;
            else
                return true;
        }else {
            return false;
        }

    }

}
