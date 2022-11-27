package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class registrationform extends AppCompatActivity {

    EditText regemail;
    EditText regpass;

    Button register;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationform);

        regemail = findViewById(R.id.regemail);
        regpass = findViewById(R.id.regpass);

        register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(view -> {
            createUser();
        });


    }

    private void createUser() {
        String email = regemail.getText().toString();
        String pass = regpass.getText().toString();


        if (TextUtils.isEmpty(email)) {
            regemail.setError("Email cannot be empty");
            regemail.requestFocus();

        }else if (TextUtils.isEmpty(pass)){
            regpass.setError("Password cannot be empty");
            regpass.requestFocus();


        }else{
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(registrationform.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(registrationform.this, LoginActivity.class));
                    }else{
                        Toast.makeText(registrationform.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

