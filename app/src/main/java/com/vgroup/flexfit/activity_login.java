package com.vgroup.flexfit;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull;

public class activity_login extends AppCompatActivity {

    private TextInputEditText emailTextView, passwordTextView, nameTextView;
    private Button btn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount() {

        //accept value from login screen
        String email,password;
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
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(
                                    @Nonnull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(getApplicationContext(),"Login Successful.", Toast.LENGTH_LONG).show();

                                 //Navigate User to Home page on successful login
                                 Intent intent = new Intent(activity_login.this,MainActivity.class);
                                 startActivity(intent);
                             }

                             else{
                                 System.out.println("Error= "+task.getException().getMessage());
                                 Toast.makeText(getApplicationContext(),"Login Failed. Please Check the entered details", Toast.LENGTH_LONG).show();
                             }
                            }
                        }
                );
    }
}