package com.vgroup.flexfit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.vgroup.flexfit.R;

public class EditProfileActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    AutoCompleteTextView autocompleteTV;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        String workout_genres[] = {"High Intensity", "Strength Building", "Flexibility"};
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
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