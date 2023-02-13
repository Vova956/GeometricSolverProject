package com.geom.geometricsolver3.CameraAI;

import android.graphics.Bitmap;

import com.geom.geometricsolver3.Interfaces.IPostAction;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class AIThread extends Thread{
    private Bitmap target;

    private IPostAction action;
    private ArrayList<Integer> numbers;

    public AIThread(Bitmap target){
        numbers = new ArrayList<>();
        this.target = target;
    }

    @Override
    public void run() {
        Mat mat = new Mat();
        Bitmap bmp32 = target.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, mat);

        //process image



        if(action != null)
            action.perform();
    }

    public ArrayList<Integer> getNumbers(){
        return numbers;
    }
}
