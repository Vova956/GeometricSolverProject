package com.example.geometricsolver3;

public class Calculation2SolveClass {
    private SquareNumber side_a;
    private SquareNumber side_b;
    private SquareNumber side_c;
    private double angle_a;
    private double angle_b;
    private double angle_c;

    public Calculation2SolveClass(SquareNumber side_a, SquareNumber side_b, SquareNumber side_c, double angle_a, double angle_b, double angle_c) {
        this.side_a = side_a;
        this.side_b = side_b;
        this.side_c = side_c;
        this.angle_a = angle_a;
        this.angle_b = angle_b;
        this.angle_c = angle_c;
    }

    private void solveTriangleByThreeSides(){
        
    }

    public String solve(){
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.toString();
    }

    public SquareNumber getSide_a() {
        return side_a;
    }

    public void setSide_a(SquareNumber side_a) {
        this.side_a = side_a;
    }

    public SquareNumber getSide_b() {
        return side_b;
    }

    public void setSide_b(SquareNumber side_b) {
        this.side_b = side_b;
    }

    public SquareNumber getSide_c() {
        return side_c;
    }

    public void setSide_c(SquareNumber side_c) {
        this.side_c = side_c;
    }

    public double getAngle_a() {
        return angle_a;
    }

    public void setAngle_a(double angle_a) {
        this.angle_a = angle_a;
    }

    public double getAngle_b() {
        return angle_b;
    }

    public void setAngle_b(double angle_b) {
        this.angle_b = angle_b;
    }

    public double getAngle_c() {
        return angle_c;
    }

    public void setAngle_c(double angle_c) {
        this.angle_c = angle_c;
    }
}
