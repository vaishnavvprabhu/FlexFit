package com.vgroup.flexfit.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.adapters.food.dietAdapter;
import com.vgroup.flexfit.data.diet;

import java.util.Calendar;
import java.util.Objects;

public class DietActivity extends AppCompatActivity {
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


        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_home);

        title = (TextView) findViewById(R.id.actionbar_title_text);
        daynum = (TextView) findViewById(R.id.daynumber);
        title.setText("Workout");

        //Get Day of the week, Use it for Query & display on text field
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String dayToRetrieve = "day"+dayOfWeek;
        String dayToDisplay = "Day "+dayOfWeek;
        daynum.setText(dayToDisplay);

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



}
