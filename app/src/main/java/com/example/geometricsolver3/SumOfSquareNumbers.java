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
            if(squareNumber.getSquareNumber() == sum.get(i).getSquareNumber()){
                sum.get(i).addToInt(squareNumber.getIntNumber());
                return;
            }
        }

        sum.add(squareNumber);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sum.size(); i++) {
            if(i > 0 || sum.get(0).getIntNumber() < 0){
                if(sum.get(i).getIntNumber() > 0 )
                    stringBuilder.append(" + ");
                else{
                    stringBuilder.append(" - ");
                }
            }
            stringBuilder.append(sum.get(i).getAbsStr());
        }

        return stringBuilder.toString();
    }

    public double toDouble(){
        double buff = 0;
        for (int i = 0; i < sum.size(); i++) {
            buff += sum.get(i).toDouble();
        }
        return ((int)(buff*10000.0))/10000.0;
    }

    public boolean canDivide(double a){
        int amount = 0;
        for (int i = 0; i < sum.size(); i++) {
            if(sum.get(i).getIntNumber() % a == 0)
                amount++;
        }

        return amount == sum.size();
    }

    public boolean canDivideSquare(SquareNumber squareNumber){
        int amount = 0;
        for (int i = 0; i < sum.size(); i++) {
            if(sum.get(i).getSquareNumber() % squareNumber.getSquareNumber() == 0)
                amount++;
        }

        return amount == sum.size();
    }

    public boolean canDivideInt(SquareNumber squareNumber){
        int amount = 0;
        if(squareNumber.getIntNumber() == 1)
            return false;

        for (int i = 0; i < sum.size(); i++) {
            if(sum.get(i).getIntNumber() % squareNumber.getIntNumber() == 0)
                amount++;
        }

        return amount == sum.size();
    }

    public void divide(double a){
        if(canDivide(a)){
            for (int i = 0; i < sum.size(); i++) {
                sum.set(i,sum.get(i).getDivide(a));
            }
        }
    }

    public void divide(SquareNumber squareNumber){
        if(canDivideInt(squareNumber)){
            divide(squareNumber.getIntNumber());
        }
        if(canDivideSquare(squareNumber)){

            SquareNumber buff = new SquareNumber("√(" + squareNumber.getSquareNumber() + ")");
            for (int i = 0; i < sum.size(); i++) {
                sum.set(i, sum.get(i).getDivide(buff));
            }

        }
    }

}