package com.vgroup.flexfit.activities;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.adapters.exerciseAdapter;
import com.vgroup.flexfit.data.exercises;

import java.util.Calendar;
import java.util.Objects;

public class WorkoutActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;

    private TextView title,daynum;
    //Object of adapter Class
    exerciseAdapter adapter;

    //Object of Firebase Realtime db
    DatabaseReference mbase;

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
        getSupportActionBar().setElevation(0);

        daynum = (TextView) findViewById(R.id.daynumber);


        //Get Day of the week, Use it for Query & display on text field
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String dayToRetrieve = "day"+dayOfWeek;
        String dayToDisplay = "Day "+dayOfWeek;
        daynum.setText(dayToDisplay);

        //Create a instance of db & get instance
        mbase = FirebaseDatabase.getInstance().getReference().child("global/exercises/wg/"+dayToRetrieve);

        recyclerview = (RecyclerView) findViewById(R.id.recycler_workout_view);

        //Display recylcer in a linear form
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //Using a Firebase UI provided class to query and fetch data
        FirebaseRecyclerOptions<exercises> options = new FirebaseRecyclerOptions.Builder<exercises>().setQuery(mbase, exercises.class).build();

        //Connect object (of the req adapter) to adapter class itself
        adapter = new exerciseAdapter(options);
        //recycler connect to adapter class
        recyclerview.setAdapter(adapter);
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



}