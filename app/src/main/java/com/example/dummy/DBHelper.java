package com.example.dummy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";
    public DBHelper(Context context) {
        super(context, "login.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String hashedPass = hashCheck(password);


        values.put("username",username);
        values.put("password",hashedPass);

        long result = db.insert("users",null,values);
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean checkUserName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?",new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db  = this.getWritableDatabase();

        String hashedPass = hashCheck(password);

        Cursor cursor = db.rawQuery("select * from users where username=? and password=?",new String[] {username,hashedPass});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public String hashCheck(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            String stringToHash = password;
            messageDigest.update(password.getBytes());
            // String stringHash = new String(messageDigest.digest());
            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }

    public boolean usernameRegCheck(String username){
        return Pattern.matches("[a-zA-Z0-9]{1,20}", username);
    }

    public boolean passwordRegCheck(String password){
        return Pattern.matches("[a-zA-Z0-9]{8,20}", password);
    }
    public boolean amountRegCheck(String amount){
        return Pattern.matches("[0-9]{1,7}", amount);
    }
    public boolean expenseNameRegCheck(String expenseName){
        return Pattern.matches("[a-zA-Z0-9]{1,20}", expenseName);
    }

}
