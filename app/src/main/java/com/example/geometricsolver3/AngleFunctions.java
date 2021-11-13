package com.example.geometricsolver3;

public class AngleFunctions {
    public static SquareNumber getCos(double angle){
        if(angle == 45)
            return new SquareNumber(0.5,2);

        else if(angle == 30)
            return new SquareNumber(0.5,3);

        else if(angle == 60)
            return new SquareNumber(0.5,1);

        else if(angle == 135)
            return new SquareNumber(-0.5,2);

        else if(angle == 150)
            return new SquareNumber(-0.5,3);

        else if(angle == 120)
            return new SquareNumber(-0.5,3);

        else
            return new SquareNumber(Math.cos(Math.toRadians(angle)),1);
    }

    public static SquareNumber getSin(double angle){
        if(angle == 45)
            return new SquareNumber(0.5,2);

        else if(angle == 30)
            return new SquareNumber(0.5,1);

        else if(angle == 60)
            return new SquareNumber(0.5,3);

        else if(angle == 135)
            return new SquareNumber(0.5,2);

        else if(angle == 150)
            return new SquareNumber(0.5,1);

        else if(angle == 120)
            return new SquareNumber(0.5,3);

        else
            return new SquareNumber(((int)(10000.0 * Math.sin(Math.toRadians(angle)))/10000.0),1);
    }

    public static double getAngleBySin(SquareNumber sin){
        if(sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 1 || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.25))
            return 30;

        else if(sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 3 || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.75))
            return 60;

        else if(sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 2 || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.5))
            return 45;

        else if(sin.getIntNumber() == 1 && sin.getSquareNumber() == 1)
            return 90;

        else if(sin.getIntNumber() == 0 || sin.getSquareNumber() == 0)
            return 0;

        else
            return Math.toDegrees(Math.asin(sin.toDouble()));
    }

    public static double getAngleByCos(SquareNumber sin){
        if(sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 1 || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.25))
            return 60;

        else if(sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 3 || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.75))
            return 30;

        else if((sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 2) || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.5))
            return 45;

        else if(sin.getIntNumber() == 1 && sin.getSquareNumber() == 1)
            return 0;

        else if(sin.getIntNumber() == 0 || sin.getSquareNumber() == 0)
            return 90;

        else
            return Math.toDegrees(Math.asin(sin.toDouble()));
    }
}
