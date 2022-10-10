package com.vgroup.flexfit.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.vgroup.flexfit.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;

    DatabaseReference databasetReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userReference;
    TextView user;
    public String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Object for username
        Query query = databasetReference.child("user").equalTo(userid);

        //User Database Reference and instance
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userid = currentFirebaseUser.getUid();
        userReference = FirebaseDatabase.getInstance().getReference("user/"+userid);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_home);
        user = findViewById(R.id.actionbar_title_text);
        System.out.println(query);




    }
}