package com.vgroup.flexfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class activity_forgotpassword extends AppCompatActivity {
    private TextInputEditText emailTextView;
    Button contButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        emailTextView = findViewById(R.id.textInputEmail2);
        contButton=findViewById(R.id.custButton);
        }
    }
