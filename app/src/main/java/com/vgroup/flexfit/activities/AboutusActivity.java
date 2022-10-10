package com.vgroup.flexfit.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vgroup.flexfit.R;

public class AboutusActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        // author- Vaishnavi

        getSupportActionBar().hide();
       // toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
       // toolbar_title.setText("");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

}