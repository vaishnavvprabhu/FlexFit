package com.vgroup.flexfit.activities;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vgroup.flexfit.R;

public class InfoPage extends AppCompatActivity {

    TextView exname;
    //Code Author - VVP
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);



        exname = findViewById(R.id.exercise);
        String name = "test";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("exercise");
            //The key argument here must match that used in the other activity
        }
        exname.setText(name);
    }
}
