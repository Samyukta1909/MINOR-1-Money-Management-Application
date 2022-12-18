package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.dummy.DetailActivity.name_amount;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SplitwiseCal extends AppCompatActivity {

    // static List prinBill;

    static ArrayList<ArrayList> FINALprintBill = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitwise_cal);
        findPath(name_amount);
        System.out.println("FinalPrintBill: "+FINALprintBill);
        Log.i("has", String.valueOf(name_amount));
    }



    public static void findPath(HashMap<String, Integer> details) {
        ArrayList printBill = new ArrayList();
        int Max_Value = Collections.max(details.values());
        int Min_Value = Collections.min(details.values());
        if (Max_Value != Min_Value) {
            String Max_Key = getKeyFromValue(details, Max_Value).toString();
            String Min_Key = getKeyFromValue(details, Min_Value).toString();
            int result = Max_Value + Min_Value;
            if ((result >= 0)) {
                printBill.add(Min_Key + " needs to pay " + Max_Key + ":" + (Math.abs(Min_Value)));


                System.out.println(Min_Key + " needs to pay " + Max_Key + ":" +(Math.abs(Min_Value)));
                details.remove(Max_Key);
                details.remove(Min_Key);
                details.put(Max_Key, result);
                details.put(Min_Key, 0);
            } else {
                printBill.add(Min_Key + " needs to pay " + Max_Key + ":" + (Math.abs(Max_Value)));


                System.out.println(Min_Key + " needs to pay " + Max_Key + ":" + (Math.abs(Max_Value)));
                details.remove(Max_Key);
                details.remove(Min_Key);
                details.put(Max_Key, 0);
                details.put(Min_Key, result);
            }
            FINALprintBill.add(printBill);
            findPath(details);
        }

    }

    public static Object getKeyFromValue(HashMap hm, int value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public void SplitDetails(View view) {

        Intent splitint = new Intent(this, FINAL_SPLIT.class);
        splitint.putExtra("Split array", FINALprintBill);
        Log.i("hs", String.valueOf(FINALprintBill));
        startActivity(splitint);
    }

}