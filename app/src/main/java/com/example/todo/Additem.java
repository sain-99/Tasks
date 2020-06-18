package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Additem extends AppCompatActivity {
    EditText et1,et2;
    Button button;
    DatabaseReference ref;
    Data_Item data_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        et1=findViewById(R.id.editText1);
        et2=findViewById(R.id.editText2);
        button=findViewById(R.id.button);
        data_item=new Data_Item();
        ref= FirebaseDatabase.getInstance().getReference().child("Data_Item");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date,item_name;
                String item_price;
                item_name=et1.getText().toString().trim();
                item_price=et2.getText().toString().trim();
                Calendar calendar=Calendar.getInstance();
                date= DateFormat.getDateInstance().format(calendar.getTime());
                String id =ref.push().getKey();

                data_item.setItem_Name(item_name);
                data_item.setItem_Price(item_price);
                data_item.setDate(date);
                data_item.setId(id);
                ref.child(id).setValue(data_item);

                Toast.makeText(Additem.this,"Successfully inserted Item",Toast.LENGTH_LONG).show();


            }
        });

    }
}
