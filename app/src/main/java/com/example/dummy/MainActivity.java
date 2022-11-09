package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean result = isUserLoggedIn();
        if (result == true){
            Intent intent = new Intent(getApplicationContext(),SignUp.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
        }


//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                // Actions to do after 5 seconds
//                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        }, 5000);

    }

    public boolean isUserLoggedIn(){
        SharedPreferences prefs = getSharedPreferences("UserDetail", MODE_PRIVATE);
        String username = prefs.getString("USERNAME",null);

        Boolean result = username == null ? true:false;
        return result;
    }
}