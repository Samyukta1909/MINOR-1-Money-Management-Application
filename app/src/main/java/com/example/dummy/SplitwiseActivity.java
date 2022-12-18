package com.example.dummy;

import static com.example.dummy.Detail.detailsArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        DetailsAdapter detailsAdapter = new DetailsAdapter(getApplicationContext(), detailsArrayList);
        detlistview.setAdapter(detailsAdapter);
    }

    private void initWidgets() {
        detlistview = findViewById(R.id.group_listview);
    }

    public void newDet(View view) {

        Intent newDetIntent = new Intent(this, DetailActivity.class);
        startActivityForResult(newDetIntent, 1);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            setdetAdapter();
        }
    }


    public void FINDPATH(View view) {

        Intent splitIntent = new Intent(this, SplitwiseCal.class);
        startActivity(splitIntent);
    }
}