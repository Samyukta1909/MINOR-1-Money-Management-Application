package com.example.dummy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExpenseTrackerActivity extends AppCompatActivity {

    Button save,total;
    TextView textView1;
    TextView textView2;
    EditText expd;
    EditText amt;
    SharedPreferences shrd;
    ArrayList<ModelClass> arrayList;
    String usernameSF;
    DBHelper DB;

    private AlertDialog.Builder dialogbuilder;
    private AlertDialog dialog;
    private TextView sum;
    private Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);


        save = findViewById(R.id.button1);
        total = findViewById(R.id.button2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        expd = findViewById(R.id.editText1);
        amt = findViewById(R.id.editText2);
        DB = new DBHelper(this);

        SharedPreferences prefs = getSharedPreferences("UserDetail", MODE_PRIVATE);
        usernameSF = prefs.getString("USERNAME",null);


        loadData();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean amtRegCheck = DB.amountRegCheck(amt.getText().toString());
                boolean expensenameRegCheck = DB.expenseNameRegCheck(expd.getText().toString());
                if(amtRegCheck==true && expensenameRegCheck==true){
                    SaveData(expd.getText().toString(),amt.getText().toString());
                }else {
                    Toast.makeText(ExpenseTrackerActivity.this,"Invalid Entry",Toast.LENGTH_SHORT).show();
                }

                expd.setText("");
                amt.setText("");
            }
        });

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer total=0;

                shrd = getApplicationContext().getSharedPreferences(usernameSF, MODE_PRIVATE);

                Gson gson = new Gson();
                String json = shrd.getString("expense_data", null);

                Type type = new TypeToken<ArrayList<ModelClass>>() {
                }.getType();

                arrayList = gson.fromJson(json, type);
                System.out.println("ArrayList: "+arrayList);
                if(arrayList != null){
                    for (int i = 0; i < arrayList.size(); i++) {
                        int temp = arrayList.get(i).amount;
                        total = total + temp;
                    }
                }
                    createNewSumDialog(total.toString());
                }

        });
    }

    public void createNewSumDialog(String total) {
        dialogbuilder = new AlertDialog.Builder(this);
        final View SumPopup = getLayoutInflater().inflate(R.layout.total_popup, null);

        sum = (TextView) SumPopup.findViewById(R.id.display);
        ok = (Button) SumPopup.findViewById(R.id.close);

        dialogbuilder.setView(SumPopup);
        dialog = dialogbuilder.create();
        dialog.show();

        sum.setText("The total amount spend is "+total);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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
        textView1.setText("List of Expense\n");
        textView2.setText("List of Amount\n");
        editor.apply();
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
            textView1.setText("List of Expense");
            textView2.setText("List of Amount");
        }else{
            for (int i=0;i<arrayList.size();i++){
                textView1.setText(textView1.getText().toString()+"\n"+arrayList.get(i).exp+"\n");
                textView2.setText(textView2.getText().toString()+"\n"+arrayList.get(i).amount+"\n");

            }
        }
    }

}