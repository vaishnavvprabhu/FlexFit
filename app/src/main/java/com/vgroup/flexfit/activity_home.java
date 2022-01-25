package com.vgroup.flexfit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vgroup.flexfit.adapters.ExercisePullAdapter;
import com.vgroup.flexfit.data.FetchExercises;

import java.util.ArrayList;
import java.util.List;

public class activity_home extends AppCompatActivity {

    private RecyclerView exercise;
    private DatabaseReference mDatabase;

    List<FetchExercises> fetchExercises;
    RecyclerView recyclerview;
    ExercisePullAdapter exercisePullAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Getting the context
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);

        //Author: VVP
        //Initialise other Variables
        exercise = findViewById(R.id.dash_exercises);
        exercise.setHasFixedSize(true);
        exercise.setLayoutManager(new LinearLayoutManager(this));
        fetchExercises=new ArrayList<>();

        mDatabase=FirebaseDatabase.getInstance().getReference("global/exercises/wg/day1");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                FetchExercises data=ds.getValue(FetchExercises.class);
                fetchExercises.add(data);
                }
                exercisePullAdapter=new ExercisePullAdapter(fetchExercises);
                recyclerview.setAdapter(exercisePullAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
