package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Homepage extends AppCompatActivity {
    Button signout,display,add,clearall;
    private FirebaseAuth.AuthStateListener mFirebaseStateListner;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        signout=findViewById(R.id.button);
        add=findViewById(R.id.add);
        display=findViewById(R.id.display);
        clearall=findViewById(R.id.clearall);




        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(Homepage.this,MainActivity.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Homepage.this,Additem.class);
                startActivity(i);
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Homepage.this,Displayitem.class);
                startActivity(i);
            }
        });
        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference().child("Data_Item");
                ref.removeValue();

                Toast.makeText(Homepage.this,"All Data will be Deleted",Toast.LENGTH_LONG).show();

            }
        });

    }
}
