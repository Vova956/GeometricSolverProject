package com.geom.geometricsolver3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.geom.geometricsolver3.POJO.Formula;
import com.geom.geometricsolver3.R;

import java.util.ArrayList;

public class FormulaAdapter extends ArrayAdapter<Formula> {

    public FormulaAdapter(Context context, ArrayList<Formula> formulas) {
        super(context, 0, formulas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Formula formula = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.formula_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.item_name);
        TextView description = convertView.findViewById(R.id.item_description);

        name.setText(formula.getName());
        description.setText(formula.getDescription());

        return convertView;
    }


}
