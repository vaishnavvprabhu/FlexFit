package com.vgroup.flexfit;

import static android.app.ProgressDialog.show;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull;
import com.google.firebase.database.DatabaseReference;
import com.vgroup.flexfit.data.User;

public class activity_register extends AppCompatActivity {

    private TextInputEditText emailTextView, passwordTextView, nameTextView;
    private Button btn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference userinfoDb;
    public String userid, useremail, username;

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
        nameTextView = findViewById(R.id.textInputName);

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
        String email, password, name;
        email = emailTextView.getText().toString().trim();
        password = passwordTextView.getText().toString().trim();
        name = nameTextView.getText().toString().trim();

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
                                //Getting Uid
                                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                userid = currentFirebaseUser.getUid();
                                useremail = currentFirebaseUser.getEmail();
                                //Navigate User to details page
                                Intent getmoredetails = new Intent(activity_register.this,activity_setup.class);
                                getmoredetails.putExtra("username",name);
                                System.out.println(name);
                                startActivity(getmoredetails);
                            }
                            else{
                                //Reg fail toast make

                                System.out.println("Error= "+task.getException().getMessage());
                                Toast.makeText(getApplicationContext(),"Registration Failure", Toast.LENGTH_LONG).show();
                            }


                    }
                });
    }
    private void insertUserData(){
        String name = username;
        String email = useremail;
        String useridentity = userid;
        int age = 0;
        int weight = 0;
        int height = 0;
        int BMI = 0;
        String BMI_range = "0";
        String pref_workout = "0";

        User user = new User(name, email, useridentity, age, weight, height, BMI, BMI_range, pref_workout);

        userinfoDb.push().setValue(user);

        Toast.makeText(getApplicationContext(),"Welcome On Board "+name+"! Please fill these details.",Toast.LENGTH_LONG).show();
    }
}