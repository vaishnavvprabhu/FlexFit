package com.vgroup.flexfit;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import static com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class activity_exercise_genres extends AppCompatActivity {
MaterialCardView Hcard, Scard, Fcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_genres);
        Hcard=findViewById(R.id.card1);
        Scard=findViewById(R.id.card2);
        Fcard=findViewById(R.id.card3);


        Hcard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Hcard.setChecked(true);
                return true;
            }
        });
        Scard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Scard.setChecked(true);
                return true;
            }
        });
        Fcard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Fcard.setChecked(true);
                return true;
            }
        });
    }
}

