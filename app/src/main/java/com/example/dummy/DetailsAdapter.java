package com.example.dummy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends ArrayAdapter<Detail> {


    private Context a;

    private List<Detail> mylist;
    public DetailsAdapter(Context context, List<Detail> ndet)
    {
        super(context,0, ndet);
        a=context;
        mylist=ndet;
    }

    @Override
    public int getCount() {
        return mylist.size();
    }



    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {


        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(a).inflate(R.layout.details,parent,false);

        Detail currentdetail = mylist.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.nameview);
        TextView amount = (TextView) listItem.findViewById(R.id.amountview);
        TextView contact = (TextView) listItem.findViewById(R.id.cnoview);
        TextView button = (TextView) listItem.findViewById(R.id.button);

        name.setText(currentdetail.getName());
        amount.setText(Integer.toString(currentdetail.getAmount()));
        contact.setText(currentdetail.getCno());
        button.setText(currentdetail.getButton());

        return listItem;
    }
}