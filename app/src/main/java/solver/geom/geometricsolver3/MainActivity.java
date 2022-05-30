package solver.geom.geometricsolver3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Geometry calculator");

        ///
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ///

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
                intent = new Intent(this,AboutActivity.class);
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