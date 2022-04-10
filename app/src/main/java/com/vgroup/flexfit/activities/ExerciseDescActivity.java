package com.vgroup.flexfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vgroup.flexfit.R;

;


public class ExerciseDescActivity extends AppCompatActivity {
    //author- Vibha
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_desc);
        overridePendingTransition(R.anim.slide_up1, R.anim.slide_down1);
        btn = findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseDescActivity.this, activity_exercise_timer.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_up1,
                R.anim.slide_down1);
    }
}
