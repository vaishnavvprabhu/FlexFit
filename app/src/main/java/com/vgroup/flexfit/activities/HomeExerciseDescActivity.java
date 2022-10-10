package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;
import static com.vgroup.flexfit.activities.WorkoutActivity.name;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.data.exercises;

import java.util.Map;


public class HomeExerciseDescActivity extends AppCompatActivity{
//author vibha,vvp
    Button btn;
TextView toolbar_title2, time_taken_tv, diff_tv, equip_tv, cal_tv, textView, textView16;
String dayToRetrieve;
ImageView ex_img;

    //Object of Firebase Realtime db
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, exerciseDatabaseReference;
    DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_desc);
        overridePendingTransition(R.anim.slide_up1, R.anim.slide_down1);
        btn = findViewById(R.id.start);

        getSupportActionBar().hide();
        toolbar_title2=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
        toolbar_title2.setText("Exercise");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);

        textView = (TextView) findViewById(R.id.exc_name);
        textView16 = (TextView) findViewById(R.id.textView16);
        time_taken_tv = (TextView) findViewById(R.id.time_taken_tv);
        diff_tv = (TextView) findViewById(R.id.diff_tv);
        equip_tv = (TextView) findViewById(R.id.equip_tv);
        cal_tv = (TextView) findViewById(R.id.cal_tv);

        String exercisename;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                exercisename= null;
            } else {
                exercisename= extras.getString("name");
            }
        } else {
            exercisename= (String) savedInstanceState.getSerializable("name");
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
            position= (Integer) savedInstanceState.getSerializable("position");
        }
        /*Toast.makeText(getApplicationContext(),"The position is : "+position,Toast.LENGTH_LONG).show();*/


        String expref = getUserData();
        getExerciseData(exercisename,expref, position);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseTimeAndNavigateToTimedExercise(position, expref);
            }
        });
        /*exerciseDetailReference = FirebaseDatabase.getInstance().getReference().orderByChild("global/exercises/"+dayToRetrieve);*/

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
}

    private void parseTimeAndNavigateToTimedExercise(int position, String expref) {
        String time = (String) time_taken_tv.getText();
        String exercisename = (String) textView.getText();
        navigateToTimedExercise(time, exercisename, expref, position);
    }

    private void navigateToTimedExercise(String time, String exercisename, String expref, int position) {
        Intent intent = new Intent(HomeExerciseDescActivity.this, activity_exercise_timer_home.class);
        intent.putExtra("time",time);
        intent.putExtra("name",exercisename);
        intent.putExtra("expref",expref);
        intent.putExtra("position",position);
        //notifyUser("intent put extra: "+time);
        startActivity(intent);
    }

    private String getUserData() {
        //Create a instance of db & get instance
        firebaseDatabase = FirebaseDatabase.getInstance();
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
                String name = snapshot.child("pref_workout").getValue(String.class);
                String pref_workout = snapshot.child("pref_workout").getValue(String.class);
                String difficulty = snapshot.child("pref_workout").getValue(String.class);

                System.out.println(name);
                /*user.setText(greeting + name);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeExerciseDescActivity.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });
        return name;
    }

    private void getExerciseData(String exercisename, String expref, int position) {

        exerciseDatabaseReference = FirebaseDatabase.getInstance().getReference("global/exercises/warmup");


        //Retrieving data referred from geeks for geeks
        //https://www.geeksforgeeks.org/how-to-retrieve-data-from-the-firebase-realtime-database-in-android/
        exerciseDatabaseReference.orderByChild("name").equalTo(exercisename).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                    Log.d(TAG, "" +  map);
                    exercises dinosaur = snapshot.getValue(exercises.class);
                    int positionNumber = position + 1;
                    String positionToRetrieve =  "e"+positionNumber;

                    exercises exercise = snapshot.child(positionToRetrieve).getValue(exercises.class);
                    //                      ^^^^^^^^^^^^^^^^
                    String name = exercise.getName();
                    Long time_taken = exercise.getTime_taken();
                    String desc = exercise.getDesc();
                    String equipment = exercise.getEquipment();
                    Long calories = exercise.getCalories();
                    String difficulty = exercise.getDifficulty();
                    String image = exercise.getImage();




                    textView.setText(name);
                    textView16.setText(desc);
                    double ex_tt_to_num = Double.parseDouble(String.valueOf(time_taken));
                    ex_tt_to_num = ex_tt_to_num/60;
                    String ex_tt_long_to_string = String.valueOf(ex_tt_to_num);
                    time_taken_tv.setText(ex_tt_long_to_string+" Mins");
                    diff_tv.setText(difficulty);
                    equip_tv.setText(equipment);
                    cal_tv.setText(String.valueOf(calories));

                    //Picasso loads image into recycler from firebase link
                    //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
                    //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
                    ex_img = (ImageView) findViewById(R.id.exc_img);
                    Picasso.get().load(image).into(ex_img);
                    System.out.println(name);
                    //notifyUser(name);


                /*user.setText(greeting + name);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeExerciseDescActivity.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void notifyUser(String str){
        Toast.makeText(HomeExerciseDescActivity.this,""+str,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_up1,
                R.anim.slide_down1);
    }
}
