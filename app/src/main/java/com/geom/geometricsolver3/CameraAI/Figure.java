package com.geom.geometricsolver3.CameraAI;

import org.opencv.core.*;
import java.util.ArrayList;

public class Figure {
    private FigureEnum type;
    private Mat src;
    private Rect rect;
    private MatOfPoint matOfPoint;
    private ArrayList<Point> MainPoints = new ArrayList<>();

    public Figure(FigureEnum type, Mat src, Rect rect, MatOfPoint matOfPoint) {
        this.type = type;
        this.src = src;
        this.rect = rect;
        this.matOfPoint = matOfPoint;
    }

    public Figure(){
        type = FigureEnum.UNDEFINED;
        src = new Mat();
        rect = new Rect();
        matOfPoint = new MatOfPoint();
    }

    public Mat getObjectMat(){
        if(type == FigureEnum.NUMBER) {
            Rect overSize = new Rect(rect.x - 5, rect.y - 5, rect.width + 5, rect.height + 5);
            return new Mat(src, overSize);
        }

        return new Mat(src, rect);
    }

    private boolean checkForBadPoint(Point p1, Point p2, Point p3){
        double y = (p3.x - p1.x) * (p2.y - p1.y)/(p2.x-p1.x) + p1.y;
        
        return p3.y > y - 20 && p3.y < y + 20;
    }

    private void printPoint(Point p){
        System.out.println("X : " + p.x + " Y : " + p.y);
    }

    public Point[] getCornerPoints(){
        Point LeftPoint= new Point(1000,1000),RightPoint = new Point(),UpPoint= new Point(1000,1000),DownPoint = new Point();
        Point AllPoint[] = matOfPoint.toArray();

        for (int i = 0; i < AllPoint.length; i++) {
            if(LeftPoint.x > AllPoint[i].x)
                LeftPoint = AllPoint[i];

            if(RightPoint.x < AllPoint[i].x)
                RightPoint = AllPoint[i];

            if(UpPoint.y > AllPoint[i].y)
                UpPoint = AllPoint[i];

            if(DownPoint.y < AllPoint[i].y)
                DownPoint = AllPoint[i];
        }

        Point cornerPoints[] = new Point[4];
        cornerPoints[0] = UpPoint;
        cornerPoints[1] = LeftPoint;
        cornerPoints[2] = RightPoint;
        cornerPoints[3] = DownPoint;

        return cornerPoints;
    }

    public boolean isTriangle(){
        Point LeftPoint= new Point(1000,1000),RightPoint = new Point(),
                UpPoint= new Point(1000,1000),DownPoint = new Point();

        Point AllPoint[] = matOfPoint.toArray();

        for (int i = 0; i < AllPoint.length; i++) {
            if(LeftPoint.x > AllPoint[i].x)
                LeftPoint = AllPoint[i];

            if(RightPoint.x < AllPoint[i].x)
                RightPoint = AllPoint[i];

            if(UpPoint.y > AllPoint[i].y)
                UpPoint = AllPoint[i];

            if(DownPoint.y < AllPoint[i].y)
                DownPoint = AllPoint[i];
        }
        
        Point cornerPoints[] = new Point[4];
        cornerPoints[0] = UpPoint;
        cornerPoints[1] = LeftPoint;
        cornerPoints[2] = RightPoint;
        cornerPoints[3] = DownPoint;

        for (int i = 0; i < cornerPoints.length; i++) {
            printPoint(cornerPoints[i]);
        }

        int pattern[][] = {{0,2,3},{0,1,2},{0,1,3},{1,2,3},{2,3,0}};

        int badPointIndex = -1;
        for (int i = 0; i < pattern.length; i++) {
            if(checkForBadPoint(cornerPoints[pattern[i][0]], cornerPoints[pattern[i][1]], cornerPoints[pattern[i][2]])){
                badPointIndex = pattern[i][2];
                break;
            }
        }

        if(badPointIndex != -1){
            for (int i = 0; i < cornerPoints.length; i++) {
                if(badPointIndex != i){
                    MainPoints.add(cornerPoints[i]);
                }
            }
        }

        return badPointIndex != -1;

    }

    public ArrayList<Point> getMainPoints(){
        return MainPoints;
    }

    public void setFigureType(){
        if(isTriangle())
            type = FigureEnum.TRIANGLE;
    }

    public void setFigureType(FigureEnum figureType){
        type = figureType;
    }

    public Point[] getImportantPoints(){
        Point[] allPoints = matOfPoint.toArray();
        Point center = getRectCenter();

        ArrayList<Point> valuablePoints = new ArrayList<Point>();

        double maxDistance = 0;
        for (int i = 0; i < allPoints.length; i++) {
            double distance = Math.sqrt(Math.pow(allPoints[i].x - center.x,2) + Math.pow(allPoints[i].y - center.y,2));
            if(distance > maxDistance)
                maxDistance = distance;
        }

        double approximation = 70;

        for (int i = 0; i < allPoints.length; i++) {
            double distance = Math.sqrt(Math.pow(allPoints[i].x - center.x,2) + Math.pow(allPoints[i].y - center.y,2));
            if(distance >= maxDistance - approximation || distance == maxDistance){
                valuablePoints.add(allPoints[i]);
            }
        }

        Point[] returnPoints = new Point[valuablePoints.size()];
        for (int i = 0; i < returnPoints.length; i++) {
            returnPoints[i] = valuablePoints.get(i);
        }

        System.out.println("AMOUNT OF VAL POINTS : " + returnPoints.length);

        return returnPoints;
    } //is BullShit

    public Point getRectCenter(){
        return new Point((2*rect.x + rect.width)/2.0, (2*rect.y + rect.height)/2.0);
    }

    public Point[] getRectanglePoints(){
        Point[] points = new Point[4];
        points[0] = (new Point(rect.x, rect.y));
        points[1] = (new Point(rect.x + rect.width, rect.y));
        points[2] = (new Point(rect.x, rect.y + rect.height));
        points[3] = (new Point(rect.x + rect.width, rect.y + rect.height));

        return points;
    }

    public double getArea(){
        return rect.area();
    }

    public FigureEnum getType() {
        return type;
    }

    public void setType(FigureEnum type) {
        this.type = type;
    }

    public Mat getSrc() {
        return src;
    }

    public void setSrc(Mat src) {
        this.src = src.clone();
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect.clone();
    }

    public MatOfPoint getMatOfPoint() {
        return matOfPoint;
    }

    public void setMatOfPoint(MatOfPoint matOfPoint) {
        this.matOfPoint = (MatOfPoint) matOfPoint.clone();
    }
}
