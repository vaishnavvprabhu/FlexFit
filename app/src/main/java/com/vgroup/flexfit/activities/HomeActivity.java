package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;


import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vgroup.flexfit.GoogleFitService;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.adapters.WrapContentLinearLayoutManager;
import com.vgroup.flexfit.adapters.homeBlogsAdapter;
import com.vgroup.flexfit.adapters.homeExerciseAdapter;
import com.vgroup.flexfit.data.blogs;
import com.vgroup.flexfit.data.diet;
import com.vgroup.flexfit.data.exercises;

import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview, blogRecyclerview;



    DatabaseReference databasetReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userReference;
    TextView user;
    public String userid, greeting;

    //Object of adapter Class
    homeExerciseAdapter adapter;
    homeBlogsAdapter blogAdapter;

    //Object of Firebase Realtime db
    DatabaseReference mbase,mblogs,mname;
    private Menu mMenu;
    public String pref_workout;
    private long backPressed;
    private static final int TIME_INTERVAL = 2000;


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        this.mMenu = menu;
        getMenuInflater().inflate(R.menu.top_bar_menu, mMenu);

        MenuItem itemBack = mMenu.findItem(R.id.acc_set);
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        }

        itemBack.setVisible(true);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.acc_set){
            Intent i=new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    //Code Author - VVP
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //nav bar
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        try {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case (R.id.navigation_home):
                            return true;

                        case (R.id.navigation_workout):
                            startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case (R.id.navigation_diet):
                            startActivity(new Intent(getApplicationContext(), DietActivity.class));
                            overridePendingTransition(0, 0);
                            return true;

                    }
                    return false;
                }
            });

        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        //nav bar
        //Object for username
        Query query = databasetReference.child("user").equalTo(userid);

        mblogs = FirebaseDatabase.getInstance().getReference().child("global/blogs");

        blogRecyclerview = (RecyclerView) findViewById(R.id.recycler2);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

/*      //menu related
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_alternative_logo);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_toolbar));
        getSupportActionBar().setCustomView(R.layout.actionbar_home);
        user = findViewById(R.id.actionbar_title_text);
        System.out.println(query);

        //Create a instance of db & get instance
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = currentFirebaseUser.getUid();
        userReference = FirebaseDatabase.getInstance().getReference("user/" + userid);

        userReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, String> map = (Map<String, String>) snapshot.getValue();

                Log.d(TAG, "" +  map);
                /*pref_workout = snapshot.child("pref_workout").getValue(String.class);*/
                /*System.out.println("pref Workout: "+pref_workout);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });

        //Create a instance of db & get instance
        mbase = FirebaseDatabase.getInstance().getReference().child("global/exercises/warmup");
        //User Database Reference and instance
        userid = currentFirebaseUser.getUid();
        userReference = FirebaseDatabase.getInstance().getReference("user/"+userid);
        /*user.setText(greeting + name);*/
        //User Database Reference and instance
        recyclerview = findViewById(R.id.recycler1);

        //Display recycler in a linear form
        recyclerview.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        blogRecyclerview.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //Using a Firebase UI provided class to query and fetch data
        FirebaseRecyclerOptions<exercises> options = new FirebaseRecyclerOptions.Builder<exercises>().setQuery(mbase, exercises.class).build();
        FirebaseRecyclerOptions<blogs> l_options = new FirebaseRecyclerOptions.Builder<blogs>().setQuery(mblogs, blogs.class).build();

        //Connect object (of the req adapter) to adapter class itself
        adapter = new homeExerciseAdapter(options);
        blogAdapter = new homeBlogsAdapter(l_options);
        //recycler connect to adapter class
       // getUserData();
        recyclerview.setAdapter(adapter);
        blogRecyclerview.setAdapter(blogAdapter);

    }

    //Code to get data from database on activity start
    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
        blogAdapter.startListening();
    }

/*    @Override protected void onResume() {

        super.onResume();
        adapter.startListening();
    }*/

    //Stop recieving data on activity stop
    @Override protected void onStop(){
        super.onStop();
        adapter.stopListening();
        blogAdapter.stopListening();
    }


 /*  private void getUserData(){
        mname = FirebaseDatabase.getInstance().getReference("user/"+userid);

        //Retrieving data referred from geeks for geeks
        //https://www.geeksforgeeks.org/how-to-retrieve-data-from-the-firebase-realtime-database-in-android/
        userReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                if(!snapshot.exists()){
                    Toast.makeText(getApplicationContext(),"We would need some additional details about you",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HomeActivity.this, activity_setup.class);
                    startActivity(intent);
                }
                else{
                    Log.d(TAG, "" +  map);
                    String name = snapshot.child("name").getValue(String.class);
                    pref_workout = snapshot.child("pref_workout").getValue(String.class);
                    System.out.println(name);
                    setGreeting();
                    if(greeting == null){
                        greeting = "Greetings, ";
                    }
                }

                *//*user.setText(greeting + name);*//*

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });
       //return pref_workout;
    }*/

    //Not Working as of now
    //TODO:Work on Set Greeting on the home page. It shows null greeting as of now.
    private void setGreeting(){
        int time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if(time>=4 && time <12){
            greeting = "Good Morning, ";
        }
        else if (time>=12 && time <16){
            greeting = "Good Afternoon, ";
        }
        else if (time>=16 || time <4){
            greeting = "Good Evening, ";
        }
        Log.d(TAG, ""+time+" "+greeting);
    }

     //Back button listener.
     //Will close the application if the back button pressed twice.
    @Override
    public void onBackPressed()
    {
        if(backPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }
        else
        {
            Toast.makeText(this, "Press Back Again to Exit App", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }

}