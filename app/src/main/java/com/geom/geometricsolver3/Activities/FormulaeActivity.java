package com.geom.geometricsolver3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.geom.geometricsolver3.DB.FormulaDB;
import com.geom.geometricsolver3.DB.FormulaData;
import com.geom.geometricsolver3.R;

public class FormulaeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulae);

        FormulaDB db = FormulaDB.getInstance(this);

        TextView textView = findViewById(R.id.textView5);
        textView.setText(db.formulaDAO().getAll().get(0).getName());
    }
}