package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vgroup.flexfit.R;

import java.util.Map;

public class SettingsActivity extends AppCompatActivity {
    View line,line2;
    TextView fab,useremail,userWorkout,user_greeting;
    TextView about,contact,logout;
    TextView toolbar_title;
    DatabaseReference mset;
    DatabaseReference databasetReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        line=findViewById(R.id.horizontal_line);
        line.getBackground().setAlpha(20);
        line2=findViewById(R.id.horizontal_line2);
        line2.getBackground().setAlpha(20);

        getSupportActionBar().hide();
        toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
        toolbar_title.setText("Settings");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fab=findViewById(R.id.edit_profile);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
        about=findViewById(R.id.textButton1);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,AboutusActivity.class);
                startActivity(intent);
            }
        });
        contact=findViewById(R.id.textButton2);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,activity_contact.class);
                startActivity(intent);
            }
        });
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebase logout
                logout();
            }
        });

        useremail = (TextView) findViewById(R.id.useremail);
        userWorkout = (TextView) findViewById(R.id.userWorkout);
        user_greeting = (TextView) findViewById(R.id.user_greeting);
        getUserData();
    }

    private void getUserData(){
        //Create a instance of db & get instance
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = currentFirebaseUser.getUid();
        mset = FirebaseDatabase.getInstance().getReference("user/"+userid);

        //Retrieving data referred from geeks for geeks
        //https://www.geeksforgeeks.org/how-to-retrieve-data-from-the-firebase-realtime-database-in-android/
        mset.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                if(!snapshot.exists()){
                    Toast.makeText(getApplicationContext(),"We would need some additional details about you",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SettingsActivity.this, activity_setup.class);
                    startActivity(intent);
                }
                else{
                    Log.d(TAG, "" +  map);
                    String name = snapshot.child("name").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    double age = snapshot.child("age").getValue(Double.class);
                    double weight = snapshot.child("weight").getValue(Double.class);
                    double height = snapshot.child("height").getValue(Double.class);
                    String pref_workout = snapshot.child("pref_workout").getValue(String.class);
                    String workout_genres = null;
                    useremail.setText(email);
                    if (pref_workout.contains("fl")){
                        workout_genres = "Flexibility";
                        userWorkout.setText(workout_genres);
                    }
                    else if (pref_workout.contains("wg"))
                    {
                        workout_genres = "Strength Training";
                        userWorkout.setText(workout_genres);
                    }
                    else if (pref_workout.contains("hi"))
                    {
                        workout_genres = "High Intensity";
                        userWorkout.setText(workout_genres);
                    }
                    else
                    {
                        userWorkout.setText("NaN");
                    }
                    System.out.println(pref_workout);
                    System.out.println(name);
                    System.out.println(email);
                }
                //*user.setText(greeting + name);*//*
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SettingsActivity.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });}

    void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setMessage("Are you sure you want to logout?").setTitle("We will miss you!");

        builder.setCancelable(true)
                .setPositiveButton("Yes", (dialog, id) -> {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(SettingsActivity.this, activity_intro.class);
                    startActivity(intent);
                })
                .setNegativeButton("No", (dialog, id) -> {
                    dialog.dismiss();
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
