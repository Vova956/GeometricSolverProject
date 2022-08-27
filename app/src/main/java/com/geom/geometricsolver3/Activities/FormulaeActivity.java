package com.geom.geometricsolver3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.geom.geometricsolver3.Adapters.FormulaAdapter;
import com.geom.geometricsolver3.DB.FormulaDB;
import com.geom.geometricsolver3.DB.FormulaData;
import com.geom.geometricsolver3.POJO.Formula;
import com.geom.geometricsolver3.R;

import java.util.ArrayList;
import java.util.List;

public class FormulaeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulae);

        FormulaDB db = FormulaDB.getInstance(this);
        List<FormulaData> formulas = db.formulaDAO().getAll();
        ArrayList<Formula> formulasToShow = new ArrayList<>();

        for (int i = 0; i < formulas.size(); i++) {
            FormulaData buff = formulas.get(i);
            formulasToShow.add(new Formula(buff.getName(),buff.getDescription()));
        }

        FormulaAdapter formulaAdapter = new FormulaAdapter(this,formulasToShow);
        ListView listView = findViewById(R.id.formulaListView);
        listView.setAdapter(formulaAdapter);
    }
}