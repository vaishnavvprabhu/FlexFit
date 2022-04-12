package com.vgroup.flexfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vgroup.flexfit.R;

public class SettingsActivity extends AppCompatActivity {
    View line,line2;
    TextView fab;
    TextView about,contact,logout;
    TextView toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        line=findViewById(R.id.horizontal_line);
        line.getBackground().setAlpha(20);
        line2=findViewById(R.id.horizontal_line2);
        line2.getBackground().setAlpha(20);

        getSupportActionBar().hide();
        toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
        toolbar_title.setText("Settings");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fab=findViewById(R.id.edit_profile);
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
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebase logout
            }
        });
    }
}
