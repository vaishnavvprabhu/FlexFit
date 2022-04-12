package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.adapters.WrapContentLinearLayoutManager;
import com.vgroup.flexfit.adapters.exerciseAdapter;
import com.vgroup.flexfit.data.exercises;

import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class WorkoutActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;

    private TextView title,daynum;
    //Object of adapter Class
    exerciseAdapter adapter;

    //Object of Firebase Realtime db
    DatabaseReference mbase;
    DatabaseReference userReference;

    public static String name;

    //Code Author - VVP
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity_workout);
        //nav bar

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.navigation_workout);
        try{

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case (R.id.navigation_workout):
                            return true;
                        case (R.id.navigation_home):
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            overridePendingTransition(0,0);
                            return  true;
                        case (R.id.navigation_diet):
                            startActivity(new Intent(getApplicationContext(), DietActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                    }
                    return false;
                }
            });


        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        //nav bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_home);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_toolbar));
        getSupportActionBar().setElevation(0);


        daynum = (TextView) findViewById(R.id.daynumber);

        //Create a instance of db & get instance
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = currentFirebaseUser.getUid();
        userReference = FirebaseDatabase.getInstance().getReference("user/" + userid);


        //Retrieving data referred from geeks for geeks
        //https://www.geeksforgeeks.org/how-to-retrieve-data-from-the-firebase-realtime-database-in-android/
        userReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                    Log.d(TAG, "" +  map);
                    name = snapshot.child("pref_workout").getValue(String.class);
                    System.out.println(name);
                    displayData(name);
                    returnPrefWorkout(name);
                /*user.setText(greeting + name);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WorkoutActivity.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });



        /*Toast.makeText(getApplicationContext(),""+name, Toast.LENGTH_SHORT).show();*/
        //Get Day of the week, Use it for Query & display on text field
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        String dayToRetrieve = "day"+dayOfWeek;
        String dayToDisplay = "Day "+dayOfWeek;
        daynum.setText(dayToDisplay);
        mbase = FirebaseDatabase.getInstance().getReference().child("global/exercises/"+name+"/"+dayToRetrieve);

        recyclerview = (RecyclerView) findViewById(R.id.recycler_workout_view);
        //Display recycler in a linear form
        recyclerview.setLayoutManager(new WrapContentLinearLayoutManager(WorkoutActivity.this, LinearLayoutManager.VERTICAL, false));

        //Using a Firebase UI provided class to query and fetch data
        FirebaseRecyclerOptions<exercises> options = new FirebaseRecyclerOptions.Builder<exercises>().setQuery(mbase, exercises.class).build();

        //Connect object (of the req adapter) to adapter class itself
        adapter = new exerciseAdapter(options);

        //recycler connect to adapter class
        recyclerview.setAdapter(adapter);



    }

    private void displayData(String exname) {

    }

    //Code to get data from database on activity start
    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    //Stop recieving data on activity stop
    @Override protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    //return to Home screen on back pressed
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        overridePendingTransition(0, 0);
    }
    private void getUserPreferedWorkout(){


    }

    private String returnPrefWorkout(String name) {
        return name;
    }


}