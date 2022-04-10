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
import com.vgroup.flexfit.adapters.food.dietAdapter;
import com.vgroup.flexfit.data.diet;

import java.util.Calendar;
import java.util.Objects;

public class DietActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView b_recyclerview,l_recyclerview,d_recyclerview;

    private TextView title,daynum;
    //Author : VVP
    //Object of adapter Class
    dietAdapter b_adapter, l_adapter, d_adapter;

    //Object of Firebase Realtime db
    DatabaseReference mbaseb, mbasel, mbased;

    //Code Author - VVP
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity_diet);
    //nav bar

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.navigation_diet);
        try {


            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override

                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case (R.id.navigation_diet):
                            return true;
                        case (R.id.navigation_workout):
                            startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case (R.id.navigation_home):
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                    }
                    return false;
                }
            });
        } catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
//-- nav bar--

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_home);
        getSupportActionBar().setElevation(0);


        daynum = (TextView) findViewById(R.id.daynumber);


        //Create a instance of db & get instance
        mbaseb = FirebaseDatabase.getInstance().getReference().child("global/food/breakfast");
        mbasel = FirebaseDatabase.getInstance().getReference().child("global/food/lunch");
        mbased = FirebaseDatabase.getInstance().getReference().child("global/food/dinner");


        b_recyclerview = (RecyclerView) findViewById(R.id.recycler_breakfast_view);
        l_recyclerview = (RecyclerView) findViewById(R.id.recycler_lunch_view);
        d_recyclerview = (RecyclerView) findViewById(R.id.recycler_dinner_view);


        //Display recylcer in a linear form
        b_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        l_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        d_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //Using a Firebase UI provided class to query and fetch data
        FirebaseRecyclerOptions<diet> options = new FirebaseRecyclerOptions.Builder<diet>().setQuery(mbaseb, diet.class).build();
        FirebaseRecyclerOptions<diet> l_options = new FirebaseRecyclerOptions.Builder<diet>().setQuery(mbasel, diet.class).build();
        FirebaseRecyclerOptions<diet> d_options = new FirebaseRecyclerOptions.Builder<diet>().setQuery(mbased, diet.class).build();

        //Connect object (of the req adapter) to adapter class itself
        b_adapter = new dietAdapter(options);
        l_adapter = new dietAdapter(l_options);
        d_adapter = new dietAdapter(d_options);
        //recycler connect to adapter class
        b_recyclerview.setAdapter(b_adapter);
        l_recyclerview.setAdapter(l_adapter);
        d_recyclerview.setAdapter(d_adapter);
    }

    //Code to get data from database on activity start
    @Override protected void onStart() {

        super.onStart();
        b_adapter.startListening();
        l_adapter.startListening();
        d_adapter.startListening();

    }

    //Stop recieving data on activity stop
    @Override protected void onStop(){
        super.onStop();
        b_adapter.stopListening();
        l_adapter.stopListening();
        d_adapter.stopListening();
    }

    //return to Home screen on back pressed
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        overridePendingTransition(0, 0);
    }



}
