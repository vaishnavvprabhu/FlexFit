package com.vgroup.flexfit.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vgroup.flexfit.R;

public class EditProfileActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    AutoCompleteTextView autocompleteTV;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();
       // toolbar_title=findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbarFlexfit);
     //   toolbar_title.setText("");
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String workout_genres[] = {"High Intensity", "Strength Building", "Flexibility"};
        arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_items, R.id.textViewitems, workout_genres);
        // get reference to the autocomplete text view
        autocompleteTV = findViewById(R.id.autoCompleteTextView);
                // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter);

        save=findViewById(R.id.custButton2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}