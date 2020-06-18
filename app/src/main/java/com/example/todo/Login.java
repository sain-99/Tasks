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

public class Login extends AppCompatActivity {
    EditText emailid,password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mfirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignIn = findViewById(R.id.button);
        tvSignUp = findViewById(R.id.textView);

        mAuthStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mfirebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null)
                {
                    Toast.makeText(Login.this,"You are login",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Login.this,Homepage.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Login.this,"Please login",Toast.LENGTH_LONG).show();
                }
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
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
                    mfirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Login.this, "Login Error Please login again.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent i=new Intent(Login.this,Homepage.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Login.this, "Error Occured.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
