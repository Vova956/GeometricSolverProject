package com.example.geometricsolver3;

import java.util.ArrayList;

public class SumOfSquareNumbers {
    private ArrayList<SquareNumber> sum;

    public SumOfSquareNumbers(){
        sum = new ArrayList<>();
    }

    public SumOfSquareNumbers(SquareNumber squareNumber){
        sum = new ArrayList<>();
        sum.add(squareNumber);
    }

    public void addToSum(SquareNumber squareNumber){
        for (int i = 0; i < sum.size(); i++) {
            if(squareNumber.getIntNumber() == sum.get(i).getIntNumber()){
                sum.get(i).addToInt(squareNumber.getIntNumber());
                break;
            }
        }

        sum.add(squareNumber);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sum.size(); i++) {
            stringBuilder.append(sum.get(i).toString());
            stringBuilder.append(" + ");
        }

        return stringBuilder.toString();
    }

    public double toDouble(){
        double buff = 0;
        for (int i = 0; i < sum.size(); i++) {
            buff += sum.get(i).toDouble();
        }
        return buff;
    }



}
