package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class SplitwiseActivity extends AppCompatActivity {

    private ListView detlistview;
    static List printBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitwise);

        initWidgets();
        setdetAdapter();
    }

    private void setdetAdapter() {

        DetailsAdapter detailsAdapter = new DetailsAdapter(getApplicationContext(), Detail.detailsArrayList);
        detlistview.setAdapter(detailsAdapter);
    }

    private void initWidgets() {
        detlistview = findViewById(R.id.group_listview);
    }


    public void newDet(View view) {

        Intent newDetIntent = new Intent(this, DetailActivity.class);
        startActivity(newDetIntent);
    }



    public void FINDPATH(View view) {

        Intent splitIntent = new Intent(this, SplitwiseCal.class);
        startActivity(splitIntent);
    }
}