package com.example.geometricsolver3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParallelogramCalculator1 extends AppCompatActivity {

    private Keyboard keyboard;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private boolean isKeyboard = false;

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallelogram_calculator1);

        setTitle("Square");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView editText_a = this.findViewById(R.id.parallegram_calculator1_editTextA);
        TextView editText_d = this.findViewById(R.id.parallegram_calculator1_editTextD);
        TextView editText_P = this.findViewById(R.id.parallegram_calculator1_editTextP);
        TextView editText_S = this.findViewById(R.id.parallegram_calculator1_editTextS);

        keyboard = new Keyboard();
        TextView[] textViews = {editText_a, editText_d, editText_P,editText_S};
        keyboard.setTextsViews(textViews);
        fragmentManager = getSupportFragmentManager();

        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setShowSoftInputOnFocus(false);
            int finalI = i;
            textViews[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (!isKeyboard) {
                        transaction = fragmentManager.beginTransaction();
                        transaction.add(R.id.parallegram_calculator1_keyboard, keyboard);
                        transaction.commit();
                        isKeyboard = true;
                    }
                    keyboard.setTextViewIndex(finalI);
                    return false;
                }
            });
        }

        Button solveButton = findViewById(R.id.parallegram_calculator1_solutionButton);
        TextView answer = findViewById(R.id.parallegram_calculator1_answerText);

        solveButton.setOnClickListener(evt ->{
            try {
                for (int i = 0; i < textViews.length; i++) {
                    if (!MistakeChecker.checkForMistakes(textViews[i].getText().toString())) {
                        throw new Exception("INVALID INPUT");
                    }
                }

                SquareNumber a = new SquareNumber(editText_a.getText().toString());
                SquareNumber d = new SquareNumber(editText_d.getText().toString());
                SquareNumber P = new SquareNumber(editText_P.getText().toString());
                SquareNumber S = new SquareNumber(editText_S.getText().toString());

                Calculation4SolveClass solution = new Calculation4SolveClass(P, S, a, d);

                String solutionString = solution.solve();


                answer.setTextColor(Color.BLACK);
                answer.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                answer.setText(solutionString);

            }catch(Exception e){
                answer.setTextColor(Color.RED);
                answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                answer.setText(e.getMessage());
                Log.d("Error",e.getMessage());
            }
        });




    }
}