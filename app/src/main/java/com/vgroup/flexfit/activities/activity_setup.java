package com.vgroup.flexfit.activities;

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

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vgroup.flexfit.R;

public class activity_setup extends AppCompatActivity {

    public double inage, inheight, inweight;
    private TextInputEditText ageTextView, heightTextView, weightTextView;
    private Button nxt;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference userinfoDb;
    public String userid, useremail, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        //Author:vVp

        //Hide Action bar
        getSupportActionBar().hide();
        //Firebase INstance
        mAuth = FirebaseAuth.getInstance();

        //Initialise Components
        nxt = (Button) findViewById(R.id.nxt);
        ageTextView = (TextInputEditText) findViewById(R.id.textInputAge);
        heightTextView = (TextInputEditText) findViewById(R.id.textInputHeight);
        weightTextView = (TextInputEditText) findViewById(R.id.textInputWeight);

        mAuth = FirebaseAuth.getInstance();

        //Set On Click listener
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addDetailsToNewUser(); }
        });


    }

    private void addDetailsToNewUser() {
        String nage, nheight, nweight;

        nage = ageTextView.getText().toString().trim();
        nheight = heightTextView.getText().toString().trim();
        nweight = weightTextView.getText().toString().trim();


        if (ageTextView == null || heightTextView == null || weightTextView == null || TextUtils.isEmpty(nage) || TextUtils.isEmpty(nheight) || TextUtils.isEmpty(nweight)) {
            Toast.makeText(getApplicationContext(),"Please Enter Age", Toast.LENGTH_LONG) .show();
            Log.v(TAG, "Please check the data entered!");
            return;
        }

        if (Integer.parseInt(nage) > 100 ||Integer.parseInt(nheight) > 500) {
            Toast.makeText(getApplicationContext(),"Please Enter Valid Credentials", Toast.LENGTH_LONG).show();
            Log.v(TAG, "Please Enter Valid Credentials");
            return;
        }

/*      else if (weightTextView == null) {
            Toast.makeText(getApplicationContext(),"Please Enter Weight", Toast.LENGTH_LONG).show();
            Log.v(TAG, "Please Enter Weight");
            return;
        }
       else if (TextUtils.isEmpty(nage)) {
            Toast.makeText(getApplicationContext(),"Please Enter Correct Age", Toast.LENGTH_LONG) .show();
            Log.v(TAG, "Please Enter Email");
            return;
        }

        else if (TextUtils.isEmpty(nheight)) {
            Toast.makeText(getApplicationContext(),"Please Enter Correct Height", Toast.LENGTH_LONG).show();
            return;
        }
        else if (TextUtils.isEmpty(nweight)) {
            Toast.makeText(getApplicationContext(),"Please Enter Correct Weight", Toast.LENGTH_LONG).show();
            return;
        }*/
        else {
            //Accept Value of fields
            inage = Double.parseDouble(ageTextView.getText().toString());
            inheight = Double.parseDouble(heightTextView.getText().toString());
            inweight = Double.parseDouble(weightTextView.getText().toString());

            //Starting with database data entry

            //create/register new user
            Log.v(TAG, "Starting with Data Entry");


            try {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                userid = currentFirebaseUser.getUid();
                useremail = currentFirebaseUser.getEmail();

                //Initialise and insert Uid into Realtime Database
                {
                    userinfoDb = FirebaseDatabase.getInstance().getReference().child("user");
                    insertUserData();
                }
                //make toast and navigate to login
                /*            Toast.makeText(getApplicationContext(), "Registration Successful. Now Login using the details you entered.", Toast.LENGTH_LONG).show();*/


            } catch (Exception e) {
                System.out.println("Error= " + e);
                Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_LONG).show();
            }

        }
    }

    private void insertUserData() {
        String name = "test";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("username");
            //The key argument here must match that used in the other activity
        }
        System.out.println(name);
        String email = useremail;
        String useridentity = userid;


        double age = inage;
        double weight = inweight;
        double height = inheight/100;

        //Calculate BMI Range
        double heightsquared = height*height;
        double BMI = weight / heightsquared;
        String BMI_range;
        if (BMI<18.5){
            BMI_range = "Under Weight";
        }
        else if (BMI<25){
            BMI_range = "Normal";
        }
        else if (BMI<30){
            BMI_range = "Over Weight";
        }
        else {
            BMI_range = "Obese";
        }
        String pref_workout = "0";

        Intent intent = new Intent(activity_setup.this, activity_exercise_genres.class);
        intent.putExtra("username",name);
        intent.putExtra("email",email);
        intent.putExtra("useridentity",useridentity);
        intent.putExtra("age",age);
        intent.putExtra("weight",weight);
        intent.putExtra("height",height);
        intent.putExtra("BMI",BMI);
        intent.putExtra("BMI_range",BMI_range);
        intent.putExtra("pref_workout",pref_workout);
        startActivity(intent);


        /*User user = new User(name, email, useridentity, age, weight, height, BMI, BMI_range, pref_workout);

        userinfoDb.push().setValue(user);*/
    }
}