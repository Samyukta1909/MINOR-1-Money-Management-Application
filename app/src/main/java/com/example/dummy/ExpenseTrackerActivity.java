package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExpenseTrackerActivity extends AppCompatActivity {

    Button button ;
    TextView textView1;
    TextView textView2;
    EditText expd;
    EditText amt;
    SharedPreferences shrd;
    ArrayList<ModelClass> arrayList;
    String usernameSF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);


        button = findViewById(R.id.button);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        expd = findViewById(R.id.editText1);
        amt = findViewById(R.id.editText2);


        SharedPreferences prefs = getSharedPreferences("UserDetail", MODE_PRIVATE);
        usernameSF = prefs.getString("USERNAME",null);
        System.out.println("Username from ExpenseTracker is: "+usernameSF);
        System.out.println("Type from ExpenseTracker is: "+usernameSF.getClass());

        loadData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveData(expd.getText().toString(),amt.getText().toString());

            }
        });

    }

    private void SaveData(String exp, String amount) {

        shrd = getApplicationContext().getSharedPreferences(usernameSF, MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();

        Gson gson = new Gson();
        arrayList.add(new ModelClass(exp, Integer.parseInt(amount)));
        String json= gson.toJson(arrayList);
        editor.putString("expense_data", json);
        editor.apply();
        textView1.setText("list of expense\n");
        textView2.setText("list of amount\n");
        loadData();

    }

    private void loadData() {

        shrd = getApplicationContext().getSharedPreferences(usernameSF, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = shrd.getString("expense_data",null);

        Type type = new TypeToken<ArrayList<ModelClass>>(){
        }.getType();

        arrayList= gson.fromJson(json,type);

        if(arrayList==null){
            arrayList= new ArrayList<>();
            textView1.setText(""+0);
            textView2.setText(""+0);
        }else{
            for (int i=0;i<arrayList.size();i++){
                textView1.setText(textView1.getText().toString()+"\n"+arrayList.get(i).exp+"\n");
                textView2.setText(textView2.getText().toString()+"\n"+arrayList.get(i).amount+"\n");
            }
        }
    }
}