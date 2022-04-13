package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.data.User;

import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    private DatabaseReference userinfoDb;
    public String userid, useremail, msg;
    AutoCompleteTextView autocompleteTV;
    Button save;
    DatabaseReference mset;
    TextInputEditText edit_name, edit_email, edit_age, edit_weight, edit_height;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();
       // toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
     //   toolbar_title.setText("");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String workout_genres[] = {"High Intensity", "Weight Gain", "Flexibility"};
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_items, R.id.textViewitems, workout_genres);
        // get reference to the autocomplete text view
        autocompleteTV = findViewById(R.id.autoCompleteTextView);
                // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter);

        getUserData();
        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_age = findViewById(R.id.edit_age);
        edit_weight = findViewById(R.id.edit_weight);
        edit_height = findViewById(R.id.edit_height);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                if(selectedItem.contains("Flexibility")){
                    msg="fl";
                }
                else if(selectedItem.contains("Weight Gain")){
                    msg="wg";
                    /*addDetailsToNewUser();*/
                }
                else if(selectedItem.contains("High Intensity")){
                    msg="hi";
                    /*addDetailsToNewUser();*/
                }
            }
        });
        save=findViewById(R.id.custButton2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(msg==null) {
                    Toast.makeText(getApplicationContext(), "You have not selected any option. Kindly click on any type of exercise that you prefer.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addDetailsToNewUser();
                }
            }
        });
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

                    Log.d(TAG, "" +  map);
                    String name = snapshot.child("name").getValue(String.class);
                    String uid = snapshot.child("useridentity").getValue(String.class);
                    double age = snapshot.child("age").getValue(Double.class);
                    double weight = snapshot.child("weight").getValue(Double.class);
                    double height = snapshot.child("height").getValue(Double.class);
                    String pref_workout = snapshot.child("pref_workout").getValue(String.class);
                    String workout_genres = null;
                    edit_name.setText(name);
                    edit_email.setText(uid);
                    edit_age.setText(String.valueOf(age));
                    edit_weight.setText(String.valueOf(weight));
                    edit_height.setText(String.valueOf(height));
                    if (pref_workout.contains("fl")){
                        autoCompleteTextView.setListSelection(2);
                    }
                    else if (pref_workout.contains("wg"))
                    { autoCompleteTextView.setListSelection(1);}
                    else if (pref_workout.contains("hi"))
                    { autoCompleteTextView.setListSelection(0);}
                    System.out.println(name);
                    System.out.println(uid);
                //*user.setText(greeting + name);*//*
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditProfileActivity.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });}

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
                updateUserData(useremail);
            }
            //make toast and navigate to home
            Toast.makeText(getApplicationContext(), "Details Successfully Updated!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditProfileActivity.this,SettingsActivity.class);
            startActivity(intent);


        }
        catch (Exception e) {
            System.out.println("Error= "+e);
        }

    }

    private void updateUserData(String email) {
        String name = String.valueOf(edit_name.getText());
        String useridentity = String.valueOf(edit_email.getText());
        double age = Double.parseDouble(String.valueOf(edit_age.getText())), weight = Double.parseDouble(String.valueOf(edit_weight.getText())), height = Double.parseDouble(String.valueOf(edit_height.getText()));
        //Calculate BMI Range
        double heightsquared = (height/100)*(height/100);
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
        String pref_workout=null;
        pref_workout= msg;

        User user = new User(name, email, useridentity, age, weight, height, BMI, BMI_range, pref_workout);

        System.out.println(user);
        userinfoDb.child(user.getUseridentity()).setValue(user);
    }
}