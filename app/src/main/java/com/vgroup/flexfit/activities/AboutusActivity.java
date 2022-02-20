package com.vgroup.flexfit.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vgroup.flexfit.R;

public class AboutusActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.navigation_workout);
try{

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case (R.id.navigation_workout):
                        return true;
                    case (R.id.navigation_home):
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case (R.id.navigation_diet):
                        startActivity(new Intent(getApplicationContext(), activity_contact.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
catch (Exception e){
    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
}
    }
}