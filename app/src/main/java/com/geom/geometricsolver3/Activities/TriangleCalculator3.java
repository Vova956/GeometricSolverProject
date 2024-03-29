package com.geom.geometricsolver3.Activities;

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

import com.geom.geometricsolver3.Calculations.Calculation3SolveClass;
import com.geom.geometricsolver3.Math.CalculatorThread;
import com.geom.geometricsolver3.Math.GeometryException;
import com.geom.geometricsolver3.Fragments.Keyboard;
import com.geom.geometricsolver3.Math.MistakeChecker;
import com.geom.geometricsolver3.R;
import com.geom.geometricsolver3.Math.SquareNumber;

public class TriangleCalculator3 extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Keyboard keyboard;
    private boolean isKeyboard = false;
    TextView answer;

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle_calculator3);
        setTitle("Similarity of triangles");

        TextView editText1 = this.findViewById(R.id.calculation3_editText1);
        TextView editText2 = this.findViewById(R.id.calculation3_editText2);
        TextView editText3 = this.findViewById(R.id.calculation3_editText3);
        TextView editText4 = this.findViewById(R.id.calculation3_editText4);
        TextView editText5 = this.findViewById(R.id.calculation3_editText5);
        TextView editText6 = this.findViewById(R.id.calculation3_editText6);
        TextView editText7 = this.findViewById(R.id.calculation3_editText7);

        Button solveButton = findViewById(R.id.calculation3_solveButton);
        answer = findViewById(R.id.calculation3_answer);

        keyboard = new Keyboard();
        TextView[] textViews = {editText1,editText2,editText3,editText4,editText5,editText6,editText7};
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
                        transaction.add(R.id.calculation3_keyboard, keyboard);
                        transaction.commit();
                        isKeyboard = true;
                    }
                    keyboard.setTextViewIndex(finalI);
                    return false;
                }
            });
        }

        solveButton.setOnClickListener(evt->{
            answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            answer.setTextColor(Color.BLACK);
            answer.setText("...");
            try {
                for (int i = 0; i < textViews.length; i++) {
                    if (!MistakeChecker.checkForMistakes(textViews[i].toString())) {
                        throw new Exception("INVALID INPUT");

                    }
                }

                SquareNumber a = new SquareNumber(editText1.getText().toString());
                SquareNumber b = new SquareNumber(editText2.getText().toString());
                SquareNumber c = new SquareNumber(editText3.getText().toString());
                SquareNumber a1 = new SquareNumber(editText4.getText().toString());
                SquareNumber b1 = new SquareNumber(editText5.getText().toString());
                SquareNumber c1 = new SquareNumber(editText6.getText().toString());
                SquareNumber k = new SquareNumber(editText7.getText().toString());

                Calculation3SolveClass calculator = new Calculation3SolveClass(a,b,c,a1,b1,c1,k);
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
            }catch (GeometryException e) {
                answer.setTextColor(Color.RED);
                answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                answer.setText(e.getMessage());
            } catch (Exception e){
                answer.setTextColor(Color.RED);
                answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                answer.setText("INVALID INPUT");
            }
        });



    }
}