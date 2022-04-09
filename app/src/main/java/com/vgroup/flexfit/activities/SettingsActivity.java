package com.vgroup.flexfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vgroup.flexfit.R;

public class SettingsActivity extends AppCompatActivity {
    View line;
    FloatingActionButton fab;
    Button about;
    Button contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        line=findViewById(R.id.horizontal_line);
        line.getBackground().setAlpha(20);
        fab=(FloatingActionButton)findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
        about=findViewById(R.id.textButton1);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,AboutusActivity.class);
                startActivity(intent);
            }
        });
        contact=findViewById(R.id.textButton2);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,activity_contact.class);
                startActivity(intent);
            }
        });
    }
}
