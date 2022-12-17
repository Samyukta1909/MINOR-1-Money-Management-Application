package com.example.dummy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DetailsAdapter extends ArrayAdapter<Detail> {
    public DetailsAdapter(Context context, List<Detail> ndet)
    {
        super(context,0, ndet);
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Detail det = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.details, parent, false);

        TextView name = convertView.findViewById(R.id.nameview);
        TextView amount = convertView.findViewById(R.id.amountview);
        TextView contact = convertView.findViewById(R.id.cnoview);
        TextView button = convertView.findViewById(R.id.button);

        name.setText(det.getName());
        amount.setText(Integer.toString(det.getAmount()));
        contact.setText(det.getCno());
        button.setText(det.getButton());

        return convertView;
    }
}
