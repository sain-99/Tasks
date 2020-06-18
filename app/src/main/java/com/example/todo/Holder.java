package com.example.todo;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {
    View view;
    public Holder(@NonNull View itemView) {
        super(itemView);

        view= itemView;

    }

    public void setView(Context context,String iname,String iprice,String idate)
    {

        TextView name=view.findViewById(R.id.iname);
        TextView price=view.findViewById(R.id.iprice);
        TextView date=view.findViewById(R.id.idate);

        name.setText(iname);
        price.setText(iprice);
        date.setText(idate);

    }
}
