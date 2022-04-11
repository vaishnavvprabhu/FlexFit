package com.vgroup.flexfit.activities;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vgroup.flexfit.R;

public class activity_recipes extends AppCompatActivity {
TextView textView,toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        getSupportActionBar().hide();
        toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
        toolbar_title.setText("Recipe");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        textView = findViewById(R.id.watchVid);
        try{
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
        catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}