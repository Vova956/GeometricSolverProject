package com.example.geometricsolver3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TriangleCalculator2 extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Keyboard keyboard;
    private boolean isKeyboard = false;

    private boolean checkForMistakes(String str) {
        int OpenBraces = 0;
        int CloseBraces = 0;
        int squareRoots = 0;

        if (str.length() == 0)
            return true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                OpenBraces++;

            else if (str.charAt(i) == ')')
                CloseBraces++;

            else if (str.charAt(i) == '√')
                squareRoots++;

        }

        return OpenBraces == CloseBraces && OpenBraces == squareRoots && (str.charAt(0) != '0');
    }

    private boolean checkForAngleMistakes(String str) {
        int OpenBraces = 0;
        int CloseBraces = 0;
        int squareRoots = 0;

        if (str.length() == 0)
            return true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                OpenBraces++;

            else if (str.charAt(i) == ')')
                CloseBraces++;

            else if (str.charAt(i) == '√')
                squareRoots++;

        }

        return OpenBraces == 0 && squareRoots == 0 && CloseBraces == 0 && (str.charAt(0) != '0');
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle_calculator2);

        setTitle("Triangle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView editText_a = this.findViewById(R.id.calculator2_editText_a);
        TextView editText_b = this.findViewById(R.id.calculator2_editText_b);
        TextView editText_c = this.findViewById(R.id.calculator2_editText_c);
        TextView editText_angle_a = this.findViewById(R.id.calculator2_editText_1);
        TextView editText_angle_b = this.findViewById(R.id.calculator2_editText_2);
        TextView editText_angle_c = this.findViewById(R.id.calculator2_editText_3);

        keyboard = new Keyboard();
        TextView[] textViews = {editText_a,editText_b,editText_c,editText_angle_a,editText_angle_b,editText_angle_c};
        keyboard.setTextsViews(textViews);
        fragmentManager = getSupportFragmentManager();

        editText_a.setShowSoftInputOnFocus(false);
        editText_b.setShowSoftInputOnFocus(false);
        editText_c.setShowSoftInputOnFocus(false);
        editText_angle_a.setShowSoftInputOnFocus(false);
        editText_angle_b.setShowSoftInputOnFocus(false);
        editText_angle_c.setShowSoftInputOnFocus(false);

        editText_a.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isKeyboard) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.calculator2_keyboard, keyboard);
                    transaction.commit();
                    isKeyboard = true;
                }
                keyboard.setTextViewIndex(0);
                return false;
            }

        });

        editText_b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isKeyboard) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.calculator2_keyboard, keyboard);
                    transaction.commit();
                    isKeyboard = true;
                }
                keyboard.setTextViewIndex(1);
                return false;
            }

        });

        editText_c.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isKeyboard) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.calculator2_keyboard, keyboard);
                    transaction.commit();
                    isKeyboard = true;
                }
                keyboard.setTextViewIndex(2);
                return false;
            }

        });

        editText_angle_a.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isKeyboard) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.calculator2_keyboard, keyboard);
                    transaction.commit();
                    isKeyboard = true;
                }
                keyboard.setTextViewIndex(3);
                return false;
            }

        });

        editText_angle_b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isKeyboard) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.calculator2_keyboard, keyboard);
                    transaction.commit();
                    isKeyboard = true;
                }
                keyboard.setTextViewIndex(4);
                return false;
            }

        });

        editText_angle_c.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isKeyboard) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.calculator2_keyboard, keyboard);
                    transaction.commit();
                    isKeyboard = true;
                }
                keyboard.setTextViewIndex(5);
                return false;
            }

        });

        Button solveButton = findViewById(R.id.calculator2_solutionButton);
        TextView answer = findViewById(R.id.calculation2_answerTextView);

        solveButton.setOnClickListener(evt->{
            try {
                for (int i = 0; i < 3; i++) {
                    if (!checkForMistakes(textViews[i].getText().toString())) {
                        throw new Exception("INVALID INPUT");
                    }
                }

                for (int i = 3; i < 6; i++) {
                    if (!checkForAngleMistakes(textViews[i].getText().toString())) {
                        throw new Exception("INVALID INPUT");
                    }
                }

                SquareNumber a = new SquareNumber(editText_a.getText().toString());
                SquareNumber b = new SquareNumber(editText_b.getText().toString());
                SquareNumber c = new SquareNumber(editText_c.getText().toString());

                double angle_a;
                double angle_b;
                double angle_c;

                try {
                    angle_a = Double.parseDouble(editText_angle_a.getText().toString());
                }catch (Exception e){
                    angle_a = 0;
                }

                try {
                    angle_b = Double.parseDouble(editText_angle_b.getText().toString());
                }catch (Exception e){
                    angle_b = 0;
                }
                try {
                    angle_c = Double.parseDouble(editText_angle_c.getText().toString());
                }catch (Exception e){
                    angle_c = 0;
                }


                Calculation2SolveClass calculator = new Calculation2SolveClass(a,b,c,angle_a,angle_b,angle_c);
                String str = calculator.solve();

                answer.setTextColor(Color.BLACK);
                answer.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                answer.setText(str);

            }catch (Exception e){
                answer.setTextColor(Color.RED);
                answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                answer.setText(e.getMessage());
            }
        });


    }
}