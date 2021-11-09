package com.example.geometricsolver3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Keyboard extends Fragment {
    private TextView[] textViews;
    private int current = 0;

    private void setTextView(int difference){
        if(current + difference >= 0 && current + difference < textViews.length){
            current += difference;
        }
    }


    public void setTextViewIndex(int index){
        current = index;
    }

    public void setTextsViews(TextView[] textsViews){
        this.textViews = textsViews;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.keyboard,null);

        Button button_0 = rootView.findViewById(R.id.keyboard_button0);
        Button button_1 = rootView.findViewById(R.id.keyboard_button1);
        Button button_2 = rootView.findViewById(R.id.keyboard_button2);
        Button button_3 = rootView.findViewById(R.id.keyboard_button3);
        Button button_4 = rootView.findViewById(R.id.keyboard_button4);
        Button button_5 = rootView.findViewById(R.id.keyboard_button5);
        Button button_6 = rootView.findViewById(R.id.keyboard_button6);
        Button button_7 = rootView.findViewById(R.id.keyboard_button7);
        Button button_8 = rootView.findViewById(R.id.keyboard_button8);
        Button button_9 = rootView.findViewById(R.id.keyboard_button9);
        Button button_upArrow = rootView.findViewById(R.id.keyboard_buttonUpArrow);
        Button button_downArrow = rootView.findViewById(R.id.keyboard_buttonDownArrow);
        Button button_openBrace = rootView.findViewById(R.id.keyboard_buttonOpenBrace);
        Button button_closeBrace = rootView.findViewById(R.id.keyboard_buttonCloseBrace);
        Button button_dot = rootView.findViewById(R.id.keyboard_buttonDot);
        Button button_delete = rootView.findViewById(R.id.keyboard_buttonDelete);
        Button button_square = rootView.findViewById(R.id.keyboard_buttonSquareRoot);

        button_0.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('0'));
        });

        button_1.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('1'));
        });

        button_2.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('2'));
        });

        button_3.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('3'));
        });

        button_4.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('4'));
        });

        button_5.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('5'));
        });

        button_6.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('6'));
        });

        button_7.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('7'));
        });

        button_8.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('8'));
        });

        button_9.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('9'));
        });

        button_upArrow.setOnClickListener(evt->{
            setTextView(-1);
        });

        button_downArrow.setOnClickListener(evt->{
            setTextView(1);
        });

        button_openBrace.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('('));
        });

        button_closeBrace.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append(')'));
        });

        button_dot.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('.'));
        });

        button_dot.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append('.'));
        });

        button_square.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            textViews[current].setText(stringBuffer.append("√("));
        });

        button_delete.setOnClickListener(evt->{
            StringBuffer stringBuffer = new StringBuffer(textViews[current].getText().toString());
            if(stringBuffer.length()-1 >= 0)
                textViews[current].setText(stringBuffer.deleteCharAt(stringBuffer.length()-1));
        });



        return rootView;
    }
}
