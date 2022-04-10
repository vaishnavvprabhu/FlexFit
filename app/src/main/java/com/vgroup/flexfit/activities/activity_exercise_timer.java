package com.vgroup.flexfit.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vgroup.flexfit.R;

public class activity_exercise_timer extends AppCompatActivity {
    TextView timer;
    Button ExitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_timer);
        timer = findViewById(R.id.timer);
        //Hide Action bar
        //getSupportActionBar().hide();
        ExitBtn = findViewById(R.id.exitBtn);
        ExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //connect to firebase to get time in millisec
                //long duration=0;
            onBackPressed();

            }
        });
        double time =1.50;//firebase
        reverseTimer(time,timer);
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
}