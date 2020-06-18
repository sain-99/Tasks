package com.example.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Displayitem extends AppCompatActivity {
   RecyclerView  rv;
    DatabaseReference ref;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayitem);

        ref= FirebaseDatabase.getInstance().getReference("Data_Item");
        rv=findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Data_Item");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data_Item,Holder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Data_Item, Holder>(
                Data_Item.class,R.layout.data,Holder.class,ref
        ) {
            @Override
            protected void populateViewHolder(Holder holder, Data_Item data_item, int i) {
            holder.setView(getApplicationContext(),data_item.getItem_Name(),data_item.getItem_Price(),data_item.getDate());
            }
        };

        rv.setAdapter(firebaseRecyclerAdapter);
    }
}
