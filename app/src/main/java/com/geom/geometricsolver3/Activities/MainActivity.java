package com.geom.geometricsolver3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.geom.geometricsolver3.R;

public class MainActivity extends AppCompatActivity {
    //ca-app-pub-5814028506169648/4816446203
    //ca-app-pub-5814028506169648~9401153507

    //ca-app-pub-5814028506169648~7879675580
    //ca-app-pub-5814028506169648/4234646872
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Geometry calculator");



        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ImageButton imageButton1 = findViewById(R.id.main_imageButton1);
        imageButton1.setOnClickListener(evt ->{
            Intent intent = new Intent(this, TriangleCalculator1.class);
            startActivity(intent);
        });

        ImageButton imageButton2 = findViewById(R.id.main_imageButton2);
        imageButton2.setOnClickListener(evt ->{
            Intent intent = new Intent(this,TriangleCalculator2.class);
            startActivity(intent);
        });

        ImageButton imageButton3 = findViewById(R.id.main_imageButton3);
        imageButton3.setOnClickListener(evt ->{
            Intent intent = new Intent(this,TriangleCalculator3.class);
            startActivity(intent);
        });

        ImageButton imageButton4 = findViewById(R.id.main_imageButton4);
        imageButton4.setOnClickListener(evt ->{
            Intent intent = new Intent(this,ParallelogramCalculator1.class);
            startActivity(intent);
        });

        ImageButton imageButton5 = findViewById(R.id.main_imageButton5);
        imageButton5.setOnClickListener(evt ->{
            Intent intent = new Intent(this,FigureCalculator1.class);
            startActivity(intent);
        });


        checkFirstStart();

    }

    private void checkFirstStart() {

        SharedPreferences sp = getSharedPreferences("hasVisited", Context.MODE_PRIVATE);

        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {

            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();


            Intent intent = new Intent(this,FirstTimeActivity.class);
            startActivity(intent);

        }else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.aboutAction:
                intent = new Intent(this,FormulaeActivity.class);
                startActivity(intent);
                break;
            case R.id.helpCenterAction:
                intent = new Intent(this,HelpActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}