package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.data.User;

import androidx.appcompat.app.AppCompatActivity;


public class activity_exercise_genres extends AppCompatActivity {
    private MaterialCardView Hcard, Scard, Fcard;
    private Button cbtn;
    private TextInputEditText emailTextView, passwordTextView, nameTextView;
    private Button btn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference userinfoDb;
    public String userid, useremail, username, msg= null;
    private TextView hi,st,fl;
    //Author-Vibha
    //Function for single card selection
    private void SetCheckedCard(MaterialCardView checkedcard, MaterialCardView uncheckedcard,MaterialCardView uncheckedcard2){
        checkedcard.setChecked(true);
        uncheckedcard.setChecked(false);
        uncheckedcard2.setChecked(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_genres);
        Hcard=findViewById(R.id.card1);
        Scard=findViewById(R.id.card2);
        Fcard=findViewById(R.id.card3);
        cbtn=findViewById(R.id.containedButton);

        //Hide Action bar
        getSupportActionBar().hide();

        //Component Mapping
        hi = findViewById(R.id.hi_rec);
        st = findViewById(R.id.st_rec);
        fl = findViewById(R.id.fl_rec);

        //Adding a condition wherein the card shows recommended based on BMI
        String rec_bmi = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rec_bmi = extras.getString("BMI_range");
        }

/*        if(rec_bmi == "Under Weight" || rec_bmi == "Normal")
        {

            System.out.println(rec_bmi);
        }

        else if(rec_bmi == "Over Weight")
        {

            System.out.println(rec_bmi);
        }

        else if(rec_bmi == "Obese")
        {

            System.out.println(rec_bmi);
        }*/


        Hcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetCheckedCard(Hcard,Scard,Fcard);
            }
        });

        Scard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetCheckedCard(Scard,Hcard,Fcard);
            }
        });

        Fcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetCheckedCard(Fcard,Scard,Hcard);
            }
        });

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Hcard.isChecked()){
                    msg="hi";
                }
                else if(Scard.isChecked()){
                    msg="st";
                }
                else if(Fcard.isChecked()){
                    msg="fl";
                }
                else{
                    Toast.makeText(getApplicationContext(), "You have not selected any option. Kindly click on any type of exercise that you prefer.", Toast.LENGTH_SHORT).show();
                }

                addDetailsToNewUser();
                Intent intent = new Intent(activity_exercise_genres.this, activity_login.class);
                startActivity(intent);
            }
        });
    }

//AUTHOR : VVP {
    private void addDetailsToNewUser(){

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
            Toast.makeText(getApplicationContext(), "Registration Successful! Now Login using your details.", Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {
            System.out.println("Error= "+e);
        }

    }


    private void insertUserData() {
        String name = null,email = null,useridentity = null,BMI_range = null;
        double age = 0, weight = 0, height = 0, BMI = 0;
        String pref_workout=null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("username");
            email = extras.getString("email");
            useridentity = extras.getString("useridentity");
            age = extras.getDouble("age");
            weight = extras.getDouble("weight");
            height = extras.getDouble("height");
            BMI = extras.getDouble("BMI");
            BMI_range = extras.getString("BMI_range");
        }
        pref_workout= msg;

        User user = new User(name, email, useridentity, age, weight, height, BMI, BMI_range, pref_workout);

        System.out.println(user);
        userinfoDb.child(user.getUseridentity()).setValue(user);
    }
}
//}