package com.geom.geometricsolver3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geom.geometricsolver3.Calculations.Calculation2SolveClass;
import com.geom.geometricsolver3.CameraAI.AIThread;
import com.geom.geometricsolver3.Math.CalculatorThread;
import com.geom.geometricsolver3.Math.GeometryException;
import com.geom.geometricsolver3.Fragments.Keyboard;
import com.geom.geometricsolver3.Math.MistakeChecker;
import com.geom.geometricsolver3.R;
import com.geom.geometricsolver3.Math.SquareNumber;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class TriangleCalculator2 extends AppCompatActivity{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Keyboard keyboard;
    private boolean isKeyboard = false;
    private final int Pick_image = 1;

    private TextView answer;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i("OpenCV", "OpenCV loaded successfully");
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };


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

        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setShowSoftInputOnFocus(false);
            int finalI = i;
            textViews[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (!isKeyboard) {
                        transaction = fragmentManager.beginTransaction();
                        transaction.add(R.id.calculator2_keyboard, keyboard);
                        transaction.commit();
                        isKeyboard = true;
                    }
                    keyboard.setTextViewIndex(finalI);
                    return false;
                }
            });
        }

        Button solveButton = findViewById(R.id.calculator2_solutionButton);
        answer = findViewById(R.id.calculation2_answerTextView);

        solveButton.setOnClickListener(evt->{
            answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            answer.setTextColor(Color.BLACK);
            answer.setText("...");


            try {
                for (int i = 0; i < 3; i++) {
                    if (!MistakeChecker.checkForMistakes(textViews[i].getText().toString())) {
                        throw new Exception("INVALID INPUT");
                    }
                }

                for (int i = 3; i < 6; i++) {
                    if (!MistakeChecker.checkForAngleMistakes(textViews[i].getText().toString())) {
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

        Button pictureButton = findViewById(R.id.solve_by_picture_Button);
        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Pick_image);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri imageUri = imageReturnedIntent.getData();
                        InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                        AIThread aiThread = new AIThread(selectedImage);
                        aiThread.setExitStrategy(()->{
                            answer.setText("IT WORKED!");
                        });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d("OpenCV", "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
}