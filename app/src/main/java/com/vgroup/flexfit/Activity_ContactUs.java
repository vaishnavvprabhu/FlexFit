package com.vgroup.flexfit;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class Activity_ContactUs extends AppCompatActivity {
    Button csb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        csb = findViewById(R.id.containedButton);
        csb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //respond to button
            }
        });
    }

}