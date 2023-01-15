package com.geom.geometricsolver3.CameraAI;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.Core.BORDER_DEFAULT;
import static org.opencv.core.Core.StsOutOfRange;
import static org.opencv.core.CvType.CV_8U;
import static org.opencv.imgproc.Imgproc.*;


public class MyRecognizeText {

    public List<MatOfPoint2f> convertToMatOfPoints(List<MatOfPoint> data, Mat src) {
        List<MatOfPoint2f> matrix = new ArrayList<MatOfPoint2f>();
        Imgproc.findContours(src, data, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        for (int i = 0; i < data.size(); i++) {
            MatOfPoint2f myPt = new MatOfPoint2f();
            data.get(i).convertTo(myPt, CvType.CV_32FC2);
            matrix.add(myPt);
        }
        return matrix;
    }

    public static MatOfPoint2f MatToMatOfPoint2f(Point points[]){
        MatOfPoint points1f = new MatOfPoint(points);
        MatOfPoint2f points2f = new MatOfPoint2f(points1f);
        return points2f;
    }

    public ArrayList<MyFigure> detectCounters(Mat src, Mat dst) {
        if (dst != src) {
            src.copyTo(dst);
        }

        ArrayList<MyFigure> myFigures = new ArrayList<>();

        Mat img_gray, img_sobel, img_threshold, element;

        img_gray = new Mat();
        Imgproc.cvtColor(src, img_gray, Imgproc.COLOR_RGB2GRAY);

        img_sobel = new Mat();
        Imgproc.Sobel(img_gray, img_sobel, CvType.CV_8U, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT);

        img_threshold = new Mat();
        Imgproc.threshold(img_sobel, img_threshold, 0, 255, Imgproc.THRESH_OTSU + Imgproc.THRESH_BINARY);

        element = new Mat();
        element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(30, 30));
        Imgproc.morphologyEx(img_threshold, img_threshold, Imgproc.MORPH_CLOSE, element);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();

        Imgproc.findContours(img_threshold, contours, hierarchy, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE);
        List<MatOfPoint> contours_poly = new ArrayList<>(contours.size());
        contours_poly.addAll(contours);

        MatOfPoint2f mMOP2f1, mMOP2f2;
        mMOP2f1 = new MatOfPoint2f();
        mMOP2f2 = new MatOfPoint2f();

        System.out.println("AMOUNT OF COUNTERS FOUND = " + contours.size());
        for (int i = 0; i < contours.size(); i++) {

            contours.get(i).convertTo(mMOP2f1, CvType.CV_32FC2);
            Imgproc.approxPolyDP(mMOP2f1, mMOP2f2, 3, true);
            mMOP2f2.convertTo(contours_poly.get(i), CvType.CV_32S);
            Rect appRect = Imgproc.boundingRect(contours_poly.get(i));

            if(appRect.area() < 100)
                continue;

            MyFigure figure = new MyFigure(FigureEnum.UNDEFINED,src,appRect,contours_poly.get(i));
            myFigures.add(figure);

            Imgproc.rectangle(dst, new Point(appRect.x, appRect.y), new Point(appRect.x + appRect.width, appRect.y + appRect.height), new Scalar(255, 0, 0));

            Point[] points = contours.get(i).toArray();
            for (int j = 0; j < points.length; j++) {
                Imgproc.circle(dst,points[j],2,new Scalar(0,255,255));
            }
        }

        return myFigures;
    }
}