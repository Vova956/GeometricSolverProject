package com.geom.geometricsolver3.DB;

import android.content.Context;

import com.geom.geometricsolver3.R;

public class FormulaFiller {
    private FormulaData formulaData;

    public static void FillTable(Context context){
        FormulaDB db = FormulaDB.getInstance(context);

        FormulaData formulaData = new FormulaData();
        formulaData.setName(context.getResources().getString(R.string.Formula1));
        formulaData.setDescription(context.getResources().getString(R.string.Formula1_description));
        db.formulaDAO().insert(formulaData);

        formulaData.setName(context.getResources().getString(R.string.Formula2));
        formulaData.setDescription(context.getResources().getString(R.string.Formula2_description));
        db.formulaDAO().insert(formulaData);

        formulaData.setName(context.getResources().getString(R.string.Formula3));
        formulaData.setDescription(context.getResources().getString(R.string.Formula4_description));
        db.formulaDAO().insert(formulaData);

        formulaData.setName(context.getResources().getString(R.string.Formula4));
        formulaData.setDescription(context.getResources().getString(R.string.Formula4_description));
        db.formulaDAO().insert(formulaData);

        formulaData.setName(context.getResources().getString(R.string.Formula5));
        formulaData.setDescription(context.getResources().getString(R.string.Formula5_description));
        db.formulaDAO().insert(formulaData);

        formulaData.setName(context.getResources().getString(R.string.Formula6));
        formulaData.setDescription(context.getResources().getString(R.string.Formula6_description));
        db.formulaDAO().insert(formulaData);

        formulaData.setName(context.getResources().getString(R.string.Formula7));
        formulaData.setDescription(context.getResources().getString(R.string.Formula7_description));
        db.formulaDAO().insert(formulaData);

        formulaData.setName(context.getResources().getString(R.string.Formula8));
        formulaData.setDescription(context.getResources().getString(R.string.Formula8_description));
        db.formulaDAO().insert(formulaData);

    }
}
