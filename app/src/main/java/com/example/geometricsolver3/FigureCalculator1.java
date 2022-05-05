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

public class FigureCalculator1 extends AppCompatActivity {
    private Keyboard keyboard;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private boolean isKeyboard = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure_calculator1);

        setTitle("N-Figure");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView editText_a = this.findViewById(R.id.calculator5_editText_a);
        TextView editText_r = this.findViewById(R.id.calculator5_editText_r);
        TextView editText_R = this.findViewById(R.id.calculator5_editText_R);
        TextView editText_n = this.findViewById(R.id.calculator5_editText_n);
        TextView editText_α = this.findViewById(R.id.calculator5_editText_α);

        keyboard = new Keyboard();
        TextView[] textViews = {editText_a, editText_r, editText_R, editText_n, editText_α};
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
                        transaction.add(R.id.calculator1_keyboard, keyboard);
                        transaction.commit();
                        isKeyboard = true;
                    }
                    keyboard.setTextViewIndex(finalI);
                    return false;
                }
            });
        }

        Button solveButton = findViewById(R.id.calculator5_solutionButton);
        TextView answer = findViewById(R.id.calculation5_answerTextView);

        solveButton.setOnClickListener(evt -> {
            answer.setTextColor(Color.BLACK);
            answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            answer.setText("...");

            try {
                for (int i = 0; i < textViews.length-1; i++) {
                    if(!MistakeChecker.checkForMistakes(textViews[i].getText().toString()))
                        throw new Exception("INCORRECT INPUT");
                }

                if(!MistakeChecker.checkForAngleMistakes(editText_α.getText().toString()))
                    throw new Exception("INCORRECT INPUT");

                if(!MistakeChecker.checkForIntMistakes(editText_n.getText().toString()))
                    throw new Exception("INCORRECT INPUT");

                SquareNumber R = new SquareNumber(editText_R.getText().toString());
                SquareNumber r = new SquareNumber(editText_r.getText().toString());
                SquareNumber a = new SquareNumber(editText_a.getText().toString());
                int n = (int)Double.parseDouble(editText_n.toString());
                double α = Double.parseDouble(editText_α.toString());

                Calculation5SolveClass calculator = new Calculation5SolveClass(a, r, R,n ,α);
                CalculatorThread thread = new CalculatorThread(calculator);

                thread.setExitStrategy(() ->{
                    runOnUiThread(()->{
                        try{
                            answer.setTextColor(Color.BLACK);
                            answer.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                            answer.setText(thread.getResult());
                        }catch (Exception e){
                            answer.setTextColor(Color.RED);
                            answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            answer.setText(e.getMessage());
                        }

                    });
                });
                thread.start();


            } catch (Exception e) {
                answer.setTextColor(Color.RED);
                answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                answer.setText(e.getMessage());
            }

        });
    }
}