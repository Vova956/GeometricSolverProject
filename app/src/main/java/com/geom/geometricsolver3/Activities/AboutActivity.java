package com.geom.geometricsolver3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.geom.geometricsolver3.R;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("Formulae");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}