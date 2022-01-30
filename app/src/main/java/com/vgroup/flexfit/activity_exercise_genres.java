package com.vgroup.flexfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import static com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class activity_exercise_genres extends AppCompatActivity {
    private MaterialCardView Hcard, Scard, Fcard;
    private Button cbtn;
    //Author-Vibha
    //Function for single card selection
    private void SetCheckedCard(MaterialCardView checkedcard, MaterialCardView uncheckedcard,MaterialCardView uncheckedcard2){
        checkedcard.setChecked(true);
        uncheckedcard.setChecked(false);
        uncheckedcard2.setChecked(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_genres);
        Hcard=findViewById(R.id.card1);
        Scard=findViewById(R.id.card2);
        Fcard=findViewById(R.id.card3);
        cbtn=findViewById(R.id.containedButton);

        Hcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetCheckedCard(Hcard,Scard,Fcard);
            }
        });

        Scard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetCheckedCard(Scard,Hcard,Fcard);
            }
        });

        Fcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetCheckedCard(Fcard,Scard,Hcard);
            }
        });

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg= "";
                if(Hcard.isChecked()){
                    msg="High Intensity (hi)";
                }
                else if(Scard.isChecked()){
                    msg="Strength Building (wg)";
                }
                else{
                    msg="Flexibility (fl)";
                }
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity_exercise_genres.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}

