package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.confirmPassword);
        signup = findViewById(R.id.SignUpButton);
        signin = findViewById(R.id.LogInButton);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass  = password.getText().toString();
                String repass  = repassword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)){
                    Toast.makeText(SignUp.this, "All fields Required.", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(repass)){
                        Boolean userRegCheck = DB.usernameRegCheck(user);
                        Boolean passRegCheck = DB.passwordRegCheck(pass);

                        if (userRegCheck==true && passRegCheck==true){
                            Boolean checkuser = DB.checkUserName(user);
                            if (checkuser==false){
                                Boolean insert= DB.insertData(user,pass);
                                if(insert==true){
                                    Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(SignUp.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                            }
                    }else {
                            Toast.makeText(SignUp.this, "Invalid Username/Password Format", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignUp.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });
    
    }
}