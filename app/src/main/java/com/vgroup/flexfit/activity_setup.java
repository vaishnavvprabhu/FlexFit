package com.vgroup.flexfit;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vgroup.flexfit.data.User;

public class activity_setup extends AppCompatActivity {

    public int inage, inheight, inweight;
    private TextInputEditText ageTextView, heightTextView, weightTextView;
    private Button nxt;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference userinfoDb;
    public String userid, useremail;
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
        nxt = (Button) findViewById(R.id.containedButton);
        ageTextView = (TextInputEditText) findViewById(R.id.textInputAge);
        heightTextView = (TextInputEditText) findViewById(R.id.textInputHeight);
        weightTextView = (TextInputEditText) findViewById(R.id.textInputWeight);

        mAuth = FirebaseAuth.getInstance();

        //Set On Click listener
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addDetailsToNewUser();
                Intent intent = new Intent(activity_setup.this, activity_exercise_genres.class);
                startActivity(intent);}
        });


    }

    private void addDetailsToNewUser() {

        //Accept Value of fields

        inage = Integer.parseInt(String.valueOf(ageTextView));
        inheight = Integer.parseInt(String.valueOf(heightTextView));
        inweight = Integer.parseInt(String.valueOf(weightTextView));

        if (ageTextView.length() < 0) {
            Toast.makeText(getApplicationContext(),"Please Enter Age", Toast.LENGTH_LONG) .show();
            Log.v(TAG, "Please Enter Age");
            return;
        }

        if (heightTextView.length() < 0) {
            Toast.makeText(getApplicationContext(),"Please Enter Height", Toast.LENGTH_LONG).show();
            Log.v(TAG, "Please Enter Height");
            return;
        }

        if (weightTextView.length() < 0) {
            Toast.makeText(getApplicationContext(),"Please Enter Weight", Toast.LENGTH_LONG).show();
            Log.v(TAG, "Please Enter Weight");
            return;
        }


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
            Toast.makeText(getApplicationContext(), "Registration Successful. Now Login using the details you entered.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(activity_setup.this, activity_login.class);
            startActivity(intent);
        }
        catch (Exception e) {
            System.out.println("Error= "+e);
        }


    }

    private void insertUserData() {
        String email = useremail;
        String useridentity = userid;


        int age = inage;
        int weight = inweight;
        int height = inheight/100;

        //Calculate BMI Range
        double BMI = weight / Math.pow(height, 2);
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

        User user = new User(email, useridentity, age, weight, height, BMI, BMI_range, pref_workout);

        userinfoDb.push().setValue(user);

        Toast.makeText(getApplicationContext(), "Data Inserted" + user + " ", Toast.LENGTH_LONG).show();
    }
}