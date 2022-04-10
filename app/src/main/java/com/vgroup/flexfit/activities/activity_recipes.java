package com.vgroup.flexfit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.vgroup.flexfit.R;

public class activity_recipes extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        overridePendingTransition(R.anim.slide_up1, R.anim.slide_down1);
        textView = findViewById(R.id.watchVid);
        try{
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
        catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_up1,
                R.anim.slide_down1);
    }
}