package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button signin;
    DBHelper DB;
    SharedPreferences shrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        signin = findViewById(R.id.FinalLogInButton);
        DB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "All Fields are Required",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean login = DB.checkUsernamePassword(user,pass);
                    if (login==true){
                        Toast.makeText(LoginActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//                        intent.putExtra("USERNAME", user);

                        shrd = getApplicationContext().getSharedPreferences("UserDetail", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shrd.edit();
                        editor.putString("USERNAME",user);
                        editor.apply();

                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}