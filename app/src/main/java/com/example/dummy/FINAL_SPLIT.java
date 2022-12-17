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

        Bundle bundle= getIntent().getExtras();
        ArrayList<String> arrayList= bundle.getStringArrayList("Split array");
        ListView listview = findViewById(R.id.splitlist);
        ArrayAdapter<String > it= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(it);
    }
}