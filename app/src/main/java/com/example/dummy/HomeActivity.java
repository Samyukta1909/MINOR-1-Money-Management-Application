package com.example.dummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button button2,button3;
    String usernameSF;
    SharedPreferences shrd;
    ExpenseTrackerActivity shhd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);



//        String username = getIntent().getStringExtra("USERNAME");
//        System.out.println("Username is: "+username);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(getApplicationContext(),ExpenseTrackerActivity.class);
                startActivity(intent1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(HomeActivity.this,SplitwiseActivity.class);
                startActivity(intent2);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
               logoutUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        logout();
        Intent intent2=new Intent(HomeActivity.this,SignUp.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
        Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();
    }


        public void logout(){
            SharedPreferences prefs = getSharedPreferences("UserDetail", MODE_PRIVATE);
            usernameSF = prefs.getString("USERNAME",null);
            shrd = getApplicationContext().getSharedPreferences(usernameSF, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();
        }

}