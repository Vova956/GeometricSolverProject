package solver.geom.geometricsolver3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TriangleCalculator1 extends AppCompatActivity {
    private Keyboard keyboard;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private boolean isKeyboard = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle_calculator1);

        setTitle("Equalateral triangle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView editText_R = this.findViewById(R.id.calculator1_editText_R);
        TextView editText_r = this.findViewById(R.id.calculator1_editText_r);
        TextView editText_a = this.findViewById(R.id.calculator1_editText_a);

        keyboard = new Keyboard();
        TextView[] textViews = {editText_R, editText_r, editText_a};
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


        Button solveButton = findViewById(R.id.calculator1_solutionButton);
        TextView answer = findViewById(R.id.calculation1_answerTextView);


        solveButton.setOnClickListener(evt -> {
            answer.setTextColor(Color.BLACK);
            answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            answer.setText("...");

            try {
                if (!MistakeChecker.checkForMistakes(editText_R.getText().toString()) || !MistakeChecker.checkForMistakes(editText_r.getText().toString()) || !MistakeChecker.checkForMistakes(editText_a.getText().toString())) {
                    throw new Exception("INCORRECT INPUT");
                }

                SquareNumber R = new SquareNumber(editText_R.getText().toString());
                SquareNumber r = new SquareNumber(editText_r.getText().toString());
                SquareNumber a = new SquareNumber(editText_a.getText().toString());

                Calculation1SolveClass calculator = new Calculation1SolveClass(R, r, a);
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


            } catch (GeometryException e) {
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
