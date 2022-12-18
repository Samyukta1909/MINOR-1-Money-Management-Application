package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FINAL_SPLIT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_split);
//        ArrayList finalBill = SplitwiseCal.FINALprintBill;

        ArrayAdapter adapter = new ArrayAdapter<ArrayList>(this, R.layout.activity_listview,SplitwiseCal.FINALprintBill);
//        ArrayAdapter adapter = new ArrayAdapter<ArrayList>(this, R.layout.activity_listview, (ArrayList[]) finalBill.get(i));

        ListView listView = (ListView) findViewById(R.id.splitlist);


        listView.setAdapter(adapter);



    }
}