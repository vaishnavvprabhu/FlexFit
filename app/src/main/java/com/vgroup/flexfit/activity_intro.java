package com.vgroup.flexfit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_intro extends AppCompatActivity {

    private Button lg,sg;
    private VideoView videoview;
    private FirebaseAuth mAuth;


    //Code Author - VVP
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getSupportActionBar().hide();
        VideoView videoview = (VideoView) findViewById(R.id.videoview);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.home);
        videoview.setVideoURI(uri);

        videoview.start();


        //Initialise mAuth
        mAuth = FirebaseAuth.getInstance();
        //initialising buttons
        lg = (Button) findViewById(R.id.login);
        sg = (Button) findViewById(R.id.signup);

        //onclick listeners

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(activity_intro.this, activity_login.class);
            startActivity(intent);
            }
        });

        sg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_intro.this, activity_register.class);
                startActivity(intent);
            }
        });

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }


    //VideoView Note - VideoView does not retain its full state when going into the background.
    // In particular, it does not restore the current play state, play position, selected tracks, or any subtitle tracks added via addSubtitleSource().
    // Applications should save and restore these on their own in Activity.onSaveInstanceState(Bundle) and Activity.onRestoreInstanceState(Bundle)
    // Link - https://developer.android.com/reference/android/widget/VideoView
    //https://developer.android.com/reference/android/app/Activity#onResume()

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {
        getSupportActionBar().hide();
        VideoView videoview = (VideoView) findViewById(R.id.videoview);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.home);
        videoview.setVideoURI(uri);

        videoview.start();
    }

    //Code to continue video from resume state
    @Override
    protected void onResume () {
        super.onResume();
        getSupportActionBar().hide();
        VideoView videoview = (VideoView) findViewById(R.id.videoview);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.home);
        videoview.setVideoURI(uri);

        videoview.start();
    }

    //Check Whether user already logged in?
    @Override
    protected void onStart () {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Intent intent = new Intent(activity_intro.this,activity_home.class);
            startActivity(intent);
            this.finish();
        }
    }
}
