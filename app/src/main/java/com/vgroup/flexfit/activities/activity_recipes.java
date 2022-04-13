package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.data.diet;
import com.vgroup.flexfit.data.exercises;

import java.util.Map;

public class activity_recipes extends AppCompatActivity {
    public static String positionNew;
TextView textView,toolbar_title,tstep1,tstep2,tstep3;
ImageView exercise_img;
DatabaseReference activityDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        overridePendingTransition(R.anim.slide_up1, R.anim.slide_down1);
        getSupportActionBar().hide();
        toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
        toolbar_title.setText("Recipe");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        textView = findViewById(R.id.watchVid);
        try{
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
        catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        String foodname;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                foodname= null;
            } else {
                foodname= extras.getString("name");
            }
        } else {
            foodname= (String) savedInstanceState.getSerializable("name");
        }
        String type;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                type= null;
            } else {
                type= extras.getString("type");
            }
        } else {
            type= (String) savedInstanceState.getSerializable("type");
        }
        int position;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                position= 0;
            } else {
                position= extras.getInt("position");
            }
        } else {
            position= (int) savedInstanceState.getSerializable("position");
        }
        getExerciseData(foodname, type, position);
    }

    private void getExerciseData(String foodname, String type, int position) {

        position = position + 1;
        if(type.contains("breakfast"))
        {
            positionNew = "b"+position;
        }
        else if (type.contains("lunch"))
        {
            positionNew = "l"+position;
        }
        else if (type.contains("dinner"))
        {
            positionNew = "d"+position;
        }
        else {
            positionNew = "lol";
        }
        activityDatabaseReference = FirebaseDatabase.getInstance().getReference("global/food/"+type);



        //Retrieving data referred from geeks for geeks
        //https://www.geeksforgeeks.org/how-to-retrieve-data-from-the-firebase-realtime-database-in-android/
        String finalPositionNew1 = positionNew;
        System.out.println(""+positionNew);
        activityDatabaseReference.orderByChild("name").equalTo(foodname).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                Log.d(TAG, "" +  map);
                diet recipe = snapshot.child(finalPositionNew1).getValue(diet.class);
                //                      ^^^^^^^^^^^^^^^^
                String name = recipe.getName();
                String image = recipe.getImage();
                Long calories = recipe.getCalories();
                String difficulty = recipe.getDifficulty();
                String step1 = recipe.getStep1();
                String step2 = recipe.getStep2();
                String step3 = recipe.getStep3();

                tstep1=findViewById(R.id.s1desc);
                tstep2=findViewById(R.id.s2desc);
                tstep3=findViewById(R.id.s3desc);

                tstep1.setText(step1);
                tstep2.setText(step2);
                tstep3.setText(step3);


                //Picasso loads image into recycler from firebase link
                //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
                //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
                exercise_img = findViewById(R.id.exercise_img);
                Picasso.get().load(image).into(exercise_img);
                System.out.println(name);
                //notifyUser(name);


                /*user.setText(greeting + name);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(activity_recipes.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_up1,
                R.anim.slide_down1);
    }
}