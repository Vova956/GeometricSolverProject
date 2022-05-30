package solver.geom.geometricsolver3;

public class AngleFunctions {
    public static SquareNumber getCos(double angle) {
        if (angle == 45)
            return new SquareNumber(0.5, 2);

        else if (angle == 30)
            return new SquareNumber(0.5, 3);

        else if (angle == 60)
            return new SquareNumber(0.5, 1);

        else if (angle == 135)
            return new SquareNumber(-0.5, 2);

        else if (angle == 150)
            return new SquareNumber(-0.5, 3);

        else if (angle == 120)
            return new SquareNumber(-0.5, 3);

        else
            return new SquareNumber(Math.cos(Math.toRadians(angle)), 1);
    }

    public static SquareNumber getSin(double angle) {
        if (angle == 45)
            return new SquareNumber(0.5, 2);

        else if (angle == 30)
            return new SquareNumber(0.5, 1);

        else if (angle == 60)
            return new SquareNumber(0.5, 3);

        else if (angle == 135)
            return new SquareNumber(0.5, 2);

        else if (angle == 150)
            return new SquareNumber(0.5, 1);

        else if (angle == 120)
            return new SquareNumber(0.5, 3);

        else
            return new SquareNumber(Math.sin(Math.toRadians(angle)), 1);
    }

    public static double getAngleBySin(SquareNumber sin) {
        if (sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 1 || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.25))
            return 30;

        else if (sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 3 || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.75))
            return 60;

        else if (sin.getIntNumber() == 0.5 && sin.getSquareNumber() == 2 || (sin.getIntNumber() == 1 && sin.getSquareNumber() == 0.5))
            return 45;

        else if (sin.getIntNumber() == 1 && sin.getSquareNumber() == 1)
            return 90;

        else if (sin.getIntNumber() == 0 || sin.getSquareNumber() == 0)
            return 0;

        else
            return Math.toDegrees(Math.asin(sin.toDouble()));
    }

    public static double getAngleByCos(SquareNumber cos) {
        if (cos.getIntNumber() == 0.5 && cos.getSquareNumber() == 1 || (cos.getIntNumber() == 1 && cos.getSquareNumber() == 0.25))
            return 60;

        else if (cos.getIntNumber() == 0.5 && cos.getSquareNumber() == 3 || (cos.getIntNumber() == 1 && cos.getSquareNumber() == 0.75))
            return 30;

        else if ((cos.getIntNumber() == 0.5 && cos.getSquareNumber() == 2) || (cos.getIntNumber() == 1 && cos.getSquareNumber() == 0.5))
            return 45;

        else if (cos.getIntNumber() == 1 && cos.getSquareNumber() == 1)
            return 0;

        else if (cos.getIntNumber() == 0 || cos.getSquareNumber() == 0)
            return 90;

        else
            return Math.toDegrees(Math.acos(cos.toDouble()));
    }

    public static SquareNumber getTg(double angle) {
        if (angle == 30) {
            return new SquareNumber(0.33333, 3);
        } else if (angle == 60) {
            return new SquareNumber(1, 3);
        } else
            return new SquareNumber(Math.tan(Math.toRadians(angle)), 1);

    }

    public static double getAngleByTg(double tg) {
        if (tg == 0)
            return 180;
        return Math.toDegrees(Math.atan(tg));
    }
}
