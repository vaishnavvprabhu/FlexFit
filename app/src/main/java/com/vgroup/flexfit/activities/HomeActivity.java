package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.adapters.exerciseAdapter;
import com.vgroup.flexfit.data.exercises;

import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerview;

    DatabaseReference databasetReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userReference;
    TextView user;
    public String userid, greeting;

    //Object of adapter Class
    exerciseAdapter adapter;

    //Object of Firebase Realtime db
    DatabaseReference mbase,muser,mname;
    private Menu mMenu;


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        this.mMenu = menu;
        getMenuInflater().inflate(R.menu.top_bar_menu, mMenu);

        MenuItem itemBack = mMenu.findItem(R.id.acc_set);
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            }
        }

        itemBack.setVisible(true);

        return true;
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


        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

/*      //menu related
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_alternative_logo);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setCustomView(R.layout.actionbar_home);
        user = findViewById(R.id.actionbar_title_text);
        System.out.println(query);


        //Create a instance of db & get instance
        mbase = FirebaseDatabase.getInstance().getReference().child("global/exercises/wg/day1");

        //User Database Reference and instance
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userid = currentFirebaseUser.getUid();
        userReference = FirebaseDatabase.getInstance().getReference("user/"+userid);

        recyclerview = findViewById(R.id.recycler1);

        //Display recylcer in a linear form
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        //Using a Firebase UI provided class to query and fetch data
        FirebaseRecyclerOptions<exercises> options = new FirebaseRecyclerOptions.Builder<exercises>().setQuery(mbase, exercises.class).build();

        //Connect object (of the req adapter) to adapter class itself
        adapter = new exerciseAdapter(options);        //recycler connect to adapter class
        getUserData();
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


   private void getUserData(){

        mname = FirebaseDatabase.getInstance().getReference("user/"+userid);

        //Retrieving data referred from geeks for geeks
        //https://www.geeksforgeeks.org/how-to-retrieve-data-from-the-firebase-realtime-database-in-android/
        userReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                Log.d(TAG, "" +  map);
                String name = snapshot.child("name").getValue(String.class);
                System.out.println(name);
                setGreeting();
                if(greeting == null){
                    greeting = "Greetings, ";
                }
                /*user.setText(greeting + name);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this,"Data Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

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
}