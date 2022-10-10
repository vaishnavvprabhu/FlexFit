package com.vgroup.flexfit;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.vgroup.flexfit.activities.HomeActivity;
import com.vgroup.flexfit.adapters.exerciseAdapter;
import com.vgroup.flexfit.data.exercises;

import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class HomeFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    Button goToDiet;
    private RecyclerView recyclerview;

    DatabaseReference databasetReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userReference;
    TextView user;
    public String userid, greeting = null;

    //Object of adapter Class
    exerciseAdapter adapter;

    //Object of Firebase Realtime db
    DatabaseReference mbase,muser,mname;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        bottomNavigationView = view.findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case (R.id.navigation_home):

                            return true;

                        case (R.id.navigation_workout):

                            /*Learnt from https://learntodroid.com/how-to-move-between-fragments-using-the-navigation-component/*/
                            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_workoutFragment);

                            return true;
                        case (R.id.navigation_diet):

                            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_dietFragment);

                            return true;

                    }
                    return false;
                }
            });



        //Create a instance of db & get instance
        mbase = FirebaseDatabase.getInstance().getReference().child("global/exercises/wg/day1");

        //User Database Reference and instance
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userid = currentFirebaseUser.getUid();
        userReference = FirebaseDatabase.getInstance().getReference("user/"+userid);

        recyclerview = view.findViewById(R.id.recycler1);

        //Display recylcer in a linear form
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);

        //Using a Firebase UI provided class to query and fetch data
        FirebaseRecyclerOptions<exercises> options = new FirebaseRecyclerOptions.Builder<exercises>().setQuery(mbase, exercises.class).build();

        //Connect object (of the req adapter) to adapter class itself
        adapter = new exerciseAdapter(options);        //recycler connect to adapter class
        recyclerview.setAdapter(adapter);

        return view;
    }







}