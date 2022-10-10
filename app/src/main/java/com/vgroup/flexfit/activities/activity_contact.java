package com.vgroup.flexfit.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vgroup.flexfit.R;

public class activity_contact extends AppCompatActivity {
    ImageView gmail,twt,insta;
    Button customerSupp;
    TextView toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // author- Vaishnavi

        setContentView(R.layout.activity_contact);
        twt = findViewById(R.id.twitter);
        insta = findViewById(R.id.instagram);
        gmail = findViewById(R.id.gmail);
        customerSupp = findViewById(R.id.custButton);

        getSupportActionBar().hide();
        toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
//        toolbar_title.setText("");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


      twt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://twitter.com/sicsrpune?lang=en");
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.instagram.com/sicsrpune_official/?hl=en");
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "flexfit@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");

                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //gmail intent
                }
            }
        });
        customerSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + "18002345678"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }

        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}
