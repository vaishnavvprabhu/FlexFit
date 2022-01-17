package com.vgroup.flexfit;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull;

import java.io.IOException;

public class activity_register extends AppCompatActivity {

    private TextInputEditText emailTextView, passwordTextView, nameTextView;
    private Button btn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Author:vvp

        //hide action bar
        getSupportActionBar().hide();
        //Take Firebase Instance
        mAuth = FirebaseAuth.getInstance();

        //Initialising all components through IDs
        emailTextView = findViewById(R.id.textInputEmail);
        passwordTextView = findViewById(R.id.textInputPassword);

        btn = findViewById(R.id.containedButton);

        //Set On click listener on register
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }
    private void registerNewUser() {
        //Accept value of fields
        String email, password;
        email = emailTextView.getText().toString().trim();
        password = passwordTextView.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),"Please Enter Email", Toast.LENGTH_LONG) .show();
            Log.v(TAG, "Please Enter Email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),"Please Enter Password", Toast.LENGTH_LONG).show();
            return;
        }


        //create/register new user
        Log.v(TAG, "Starting with mAuth");
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@Nonnull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Successful. Now Login using the details you entered.", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(activity_register.this, activity_login.class);
                                startActivity(intent);
                            }
                            else{
                                //Reg fail toast make

                                System.out.println("Error= "+task.getException().getMessage());
                                Toast.makeText(getApplicationContext(),"Registration Failure", Toast.LENGTH_LONG).show();
                            }


                    }
                });
    }
}