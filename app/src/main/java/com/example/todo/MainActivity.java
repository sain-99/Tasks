package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailid,password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mfirebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mfirebaseAuth=FirebaseAuth.getInstance();
        emailid=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        btnSignUp=findViewById(R.id.button);
        tvSignIn=findViewById(R.id.textView);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailid.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if(email.isEmpty())
                {
                    emailid.setError("Please enter username");
                    emailid.requestFocus();
                }
                else if(pass.isEmpty())
                {
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                else if(!(email.isEmpty() && pass.isEmpty()))
                {
                    mfirebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent i=new Intent(MainActivity.this,Login.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Sign Up Unsuccessfull.", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error Occured.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
    }
}
