package com.geom.geometricsolver3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.geom.geometricsolver3.DB.FormulaFiller;
import com.geom.geometricsolver3.R;

public class FirstTimeActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        Button exit = findViewById(R.id.exitButton);
        exit.setOnClickListener(evt ->{
            finish();
        });

        context = this;

        Thread thread = new Thread(()->{
            FormulaFiller.FillTable(context);
        });

        thread.start();
    }
}