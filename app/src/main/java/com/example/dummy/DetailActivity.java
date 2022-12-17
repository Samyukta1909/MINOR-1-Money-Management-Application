package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    private EditText newperson_firstname, newperson_contactnumber, newperson_amount;
    RadioGroup modegroup;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initWidgets();
    }

    private void initWidgets() {
        newperson_firstname = findViewById(R.id.newperson_firstname);
        newperson_contactnumber = findViewById(R.id.newperson_contactnumber);
        newperson_amount = findViewById(R.id.newperson_amount);
        modegroup= findViewById(R.id.modegroup);
    }

    public static HashMap<String, Integer> name_amount= new HashMap<String, Integer>();

    public void saveDetail(View view) {


        int amount ;

        int selectedMode= modegroup.getCheckedRadioButtonId();
        radioButton=findViewById(selectedMode);

        String name = String.valueOf(newperson_firstname.getText());
        String contact = String.valueOf(newperson_contactnumber.getText());
        String button= String.valueOf(radioButton.getText());

        if (button.equalsIgnoreCase("pay"))
        {
            amount =(Integer.parseInt(newperson_amount.getText().toString()))*-1;
        }
        else {
            amount = Integer.parseInt(newperson_amount.getText().toString());
        }
        int id = Detail.detailsArrayList.size();
        Detail newDet = new Detail(id, name, amount, contact, button);
        Detail.detailsArrayList.add(newDet);

        name_amount.put(name, amount);


        Log.i("hash", String.valueOf(name_amount));

        finish();


    }
}