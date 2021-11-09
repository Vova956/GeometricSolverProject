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

    private Double cosinusTheoremForAngle(SquareNumber oppositeSide, SquareNumber side1, SquareNumber side2) {
        // cos A = (b^2 + c^2 - a^2) / 2bc, where A = our angle, a = opposite side to that angle and b, c are the other two sides

        SumOfSquareNumbers sum = new SumOfSquareNumbers();

        sum.addToSum(side1.getSquareOf());
        sum.addToSum(side2.getSquareOf());
        sum.addToSum(oppositeSide.getSquareOf().changeSign());

        sum.divide(2);
        sum.divide(side1);
        sum.divide(side2);

        return Math.acos(sum.toDouble());
    }

    private SumOfSquareNumbers cosinusTheoremForSide(SquareNumber side1, SquareNumber side2, Double angle) { //can I return a SquareNumber somehow?
        SumOfSquareNumbers sum = new SumOfSquareNumbers();
        SumOfSquareNumbers sum2 = new SumOfSquareNumbers();

        sum.addToSum(side1.getSquareOf());
        sum.addToSum(side2.getSquareOf());
        sum.addToSum(side2.getSquareOf());

        SquareNumber buff = side1.getMultiply(side2);
        //you don`t have a method to multiply a sum. Do I have to divide by 1/n?
        //i don`t have any time left, so I`ll just push what I have

        return sum;

    }

    private void solveTriangleByThreeSides() {
        angle_a = cosinusTheoremForAngle(side_a, side_b, side_c);
        angle_b = cosinusTheoremForAngle(side_b, side_a, side_c);
        angle_c = 180 - angle_a - angle_b;
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
