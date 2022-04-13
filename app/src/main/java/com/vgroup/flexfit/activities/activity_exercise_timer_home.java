package com.vgroup.flexfit.activities;

import static android.content.ContentValues.TAG;
import static com.vgroup.flexfit.activities.WorkoutActivity.name;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
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

import java.util.Calendar;
import java.util.Map;

public class activity_exercise_timer_home extends AppCompatActivity {
    TextView timer, exname;
    Button ExitBtn;
    TextView toolbar_title;
    ImageView ex_img;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, exerciseDatabaseReference;
    DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_timer);
        timer = findViewById(R.id.timer);
        //Hide Action bar
        getSupportActionBar().hide();
        ExitBtn = findViewById(R.id.exitBtn);
        ExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //connect to firebase to get time in millisec
                //long duration=0;
            onBackPressed();

            }
        });

        String timetaken;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                timetaken= null;
            } else {
                timetaken= extras.getString("time");
            }
        } else {
            timetaken= (String) savedInstanceState.getSerializable("time");
        }
        String exername;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                exername= null;
            } else {
                exername= extras.getString("name");
            }
        } else {
            exername= (String) savedInstanceState.getSerializable("name");
        }

        String exerpref;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                exerpref= null;
            } else {
                exerpref= extras.getString("expref");
            }
        } else {
            exerpref= (String) savedInstanceState.getSerializable("expref");
        }

        int position=0;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                position=0;
            } else {
                position= extras.getInt("position");
            }
        } else {
            exername= (String) savedInstanceState.getSerializable("position");
        }
        int positionNumber = position+1;


        String timetaken1 = timetaken.replace(" Mins","");

        Double doubleTimeTaken = Double.parseDouble(timetaken1);
        //notifyUser(doubleTimeTaken);
        double time =doubleTimeTaken;//firebase
        reverseTimer(time,timer);
        toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
        toolbar_title.setText("Exercise Timer");


        String expref = getUserData();

        getExerciseData(exername,expref,positionNumber);

    }

    public void reverseTimer(double Minutes, final TextView timer) {
        new CountDownTimer((long) (Minutes*60000+1000), 1000) {

            public void onTick(long millisUntilFinished) {
                //tTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext resource id
                // Duration
                long Mmin = (millisUntilFinished / 1000) / 60;
                long Ssec = (millisUntilFinished / 1000) % 60;
                if (Ssec < 10) {
                    timer.setText("" + Mmin + ":0" + Ssec);
                } else timer.setText("" + Mmin + ":" + Ssec);
            }

            public void onFinish() {
                timer.setText("00:00");
            }

        }.start();
    }

    private void getExerciseData(String exercisename, String expref, int position) {

        //Get Day of the week, Use it for Query & display on text field
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String dayToRetrieve = "day"+dayOfWeek;

        exerciseDatabaseReference = FirebaseDatabase.getInstance().getReference("global/exercises/warmup");


        //Retrieving data referred from geeks for geeks
        //https://www.geeksforgeeks.org/how-to-retrieve-data-from-the-firebase-realtime-database-in-android/
        exerciseDatabaseReference.orderByChild("name").equalTo(exercisename).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                if(!snapshot.exists()){
                    Toast.makeText(getApplicationContext(),"An Unknown Error Occurred. Please try after some time.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(activity_exercise_timer_home.this, activity_setup.class);
                    startActivity(intent);
                }
                else{
                    Log.d(TAG, "" +  map);
                    exercises dinosaur = snapshot.getValue(exercises.class);
                    int positionNumber = position;
                    String positionToRetrieve =  "e"+positionNumber;
                    Log.d(TAG, ""+positionToRetrieve);

                    exercises exercise = snapshot.child(positionToRetrieve).getValue(exercises.class);
                    //                      ^^^^^^^^^^^^^^^^
                    String name = exercise.getName();
                    //Long time_taken = exercise.getTime_taken();
                    //String desc = exercise.getDesc();
                    //String equipment = exercise.getEquipment();
                    //Long calories = exercise.getCalories();
                    //String difficulty = exercise.getDifficulty();
                    String image = exercise.getImage();


                    //exname = (TextView) findViewById(R.id.exc_title);
                    exname = (TextView) findViewById(R.id.exc_title);
                    exname.setText(name);

                    //Picasso loads image into recycler from firebase link
                    //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
                    //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
                    ex_img = (ImageView) findViewById(R.id.exercise_img);
                    Picasso.get().load(image).into(ex_img);
                    System.out.println(name);
                    //notifyUser(name);

                }

                /*user.setText(greeting + name);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(activity_exercise_timer_home.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });
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
                Toast.makeText(activity_exercise_timer_home.this,"Data Error", Toast.LENGTH_SHORT).show();
            }

        });
        return name;
    }

    public void notifyUser(String str){
        Toast.makeText(activity_exercise_timer_home.this,""+str,Toast.LENGTH_LONG).show();
    }
}