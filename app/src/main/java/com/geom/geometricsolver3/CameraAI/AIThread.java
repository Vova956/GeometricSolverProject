package com.geom.geometricsolver3.CameraAI;

import android.graphics.Bitmap;

import com.geom.geometricsolver3.Interfaces.IPostAction;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;
import java.util.ArrayList;

public class AIThread extends Thread{
    private Bitmap target;
    private IPostAction action;
    private ArrayList<Integer> numbers;

    public AIThread(Bitmap target){
        numbers = new ArrayList<>();
        this.target = target;
    }

    public void setExitStrategy(IPostAction iPostAction){
        this.action = iPostAction;
    }

    @Override
    public void run() {
        Mat mat = new Mat();
        Bitmap bmp32 = target.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, mat);

        //process image
        RecognizeCounters recognizeCounters = new RecognizeCounters();
        Mat origin = mat;
        Mat dst = new Mat();
        ArrayList<Figure> figures = recognizeCounters.detectCounters(origin,dst);


        double max = figures.get(0).getArea();
        int FigureIndex = 0;

        for (int i = 0; i < figures.size(); i++) {
            if(max < figures.get(i).getArea()){
                max = figures.get(i).getArea();
                FigureIndex = i;
            }
        }


        for (int i = 0; i < figures.size(); i++) {
            if(i != FigureIndex){
                figures.get(i).setType(FigureEnum.NUMBER);
            }
        }

        for (int i = 0; i < figures.size(); i++) {
            if(i == FigureIndex) continue;
            try{
                Mat stuff = figures.get(i).getObjectMat();
                figures.get(i).setFigureType(FigureEnum.NUMBER);
            }catch (Exception e){
                System.out.println("FAILED TO PROCESS MAT NUMBER " + i);
            }
        }

        Figure figure = figures.get(FigureIndex);
        figure.setFigureType();
        System.out.println(figure.getType());

        ArrayList<Figure> numbers = new ArrayList<>();
        for (int i = 0; i < figures.size(); i++) {
            if (i != FigureIndex){
                numbers.add(figures.get(i));
            }
        }

        System.out.println("NUMBERS FOUND : " + numbers.size());

        ArrayList<Integer> sides = new ArrayList<>();

        NumberAI numberAI = new NumberAI();
        for (int i = 0; i < numbers.size(); i++) {
            int a = 0;
            try {
                a = numberAI.getNumberByMat(numbers.get(i).getObjectMat());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("number is " + a);
            sides.add(a);
        }

        for (int i = 0; i < sides.size(); i++) {
            this.numbers.add(sides.get(i));
        }


        if(action != null)
            action.perform();
    }

    public ArrayList<Integer> getNumbers(){
        return numbers;
    }
}
