package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull;
import com.vgroup.flexfit.R;

public class activity_login extends AppCompatActivity {

    private TextInputEditText emailTextView, passwordTextView, nameTextView;
    private Button btn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

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
        emailTextView = findViewById(R.id.textInputEditText1);
        passwordTextView = findViewById(R.id.textInputPassword2);

        btn = findViewById(R.id.loginBtn);

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
                                 Intent intent = new Intent(activity_login.this,HomeActivity.class);
                                 startActivity(intent);
                             }

                             else{
                                 System.out.println("Error= "+task.getException().getMessage());

                             }
                            }

                        }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            notifyUser("Invalid password");
                        } else if (e instanceof FirebaseAuthInvalidUserException) {

                            String errorCode =
                                    ((FirebaseAuthInvalidUserException) e).getErrorCode();
                            if (errorCode.equals("ERROR_USER_NOT_FOUND")) {
                                notifyUser("No matching account found");
                            } else if (errorCode.equals("ERROR_USER_DISABLED")) {
                                notifyUser("User account has been disabled");
                            } else {
                                notifyUser(e.getLocalizedMessage());
                            }
                        }
                    }

                    private void notifyUser(String error) {
                        Toast.makeText(getApplicationContext(),"Login Failed: "+ error, Toast.LENGTH_LONG).show();
                    }
                });


    }
}