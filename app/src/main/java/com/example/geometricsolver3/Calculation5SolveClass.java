package com.example.geometricsolver3;

public class Calculation5SolveClass {
    private SquareNumber a;
    private SquareNumber r;
    private SquareNumber R;
    private int n;
    private double angle;

    public Calculation5SolveClass(SquareNumber a, SquareNumber r, SquareNumber r1, int n, double angle) {
        this.a = a;
        this.r = r;
        R = r1;
        this.n = n;
        this.angle = angle;
    }

    public String solve() throws Exception{
        StringBuilder builder = new StringBuilder();

        if(angle != 0 && n != 0) {
            if (angle != 180 * (n - 2.0) / n)
                throw new Exception("α ≠ 180*(n-2)/n");
        }

        if(!R.fromNullString && !a.fromNullString && n != 0){
            if(R.getMultiply(AngleFunctions.getSin(180.0/n)).toDouble() != a.getDivide(2).toDouble())
                throw new Exception("R * sin(180/n) ≠ a/2");
        }

        if(!r.fromNullString && !a.fromNullString && n != 0){
            if(r.getMultiply(AngleFunctions.getTg(180.0/n)).toDouble() != a.getDivide(2).toDouble())
                throw new Exception("r * tg(180/n) ≠ a/2");
        }

        if(angle != 0){
            if(angle < 0 || angle > 180)
                throw new Exception("α ∉ (0;180)");

            double buff = 360/(180-angle);
            if(buff != (int)buff)
                throw new Exception("n ∉ ℤ");

            if(!R.fromNullString && !a.fromNullString){
                if(R.getMultiply(AngleFunctions.getSin(180/buff)).toDouble() != a.getDivide(2).toDouble())
                    throw new Exception("R * sin(180/n) ≠ a/2");
            }

            if(r.getMultiply(AngleFunctions.getTg(180/buff)).toDouble() != a.getDivide(2).toDouble())
                throw new Exception("r * tg(180/n) ≠ a/2");

        }

        if(!a.fromNullString){
            if(n != 0){
                if(angle == 0) {
                    angle = 180 * (n - 2.0) / n;
                    builder.append("α = 180*(n-2)/n = ");
                    builder.append(angle);
                }

                if(R.fromNullString){
                    R = a.getDivide(AngleFunctions.getSin(180.0/n));
                    R.divide(2);
                    builder.append("R = a/(2*sin(180/n)) = ");
                    builder.append(R.toString());
                }

                if(r.fromNullString){
                    r = a.getDivide(AngleFunctions.getTg(180.0/n));
                    r.divide(2);
                    builder.append("r = a/(2*tg(180/n)) = ");
                    builder.append(r.toString());
                }
                return builder.toString();
            }

            if(angle != 0){
                n = (int) (360/(180-angle));
                builder.append("n = 360/(180-α) = ");
                builder.append(n);

                if(R.fromNullString){
                    R = a.getDivide(AngleFunctions.getSin(180.0/n));
                    R.divide(2);
                    builder.append("R = a/(2*sin(180/n)) = ");
                    builder.append(R.toString());
                }

                if(r.fromNullString){
                    r = a.getDivide(AngleFunctions.getTg(180.0/n));
                    r.divide(2);
                    builder.append("r = a/(2*tg(180/n)) = ");
                    builder.append(r.toString());
                }
                return builder.toString();
            }
        }

        if(!r.fromNullString){
            if(!a.fromNullString){
                n = (int) (180/AngleFunctions.getAngleByTg(a.toDouble()/(2*r.toDouble())));
                builder.append("n = 180/arctg(a/2r) = ").append(n);

                if(angle == 0) {
                    angle = 180 * (n - 2.0) / n;
                    builder.append("α = 180(n-2)/n = ").append(angle);
                }

                if (R.fromNullString) {
                    R = a.getDivide(AngleFunctions.getSin(180.0 / n));
                    R.divide(2);
                    builder.append("R = a/(2*sin(180/n)) = ").append(R.toString());
                }

                return builder.toString();
            }

            if(n != 0){
                a = AngleFunctions.getTg(180.0 / n).getMultiply(r).getMultiply(2);
                builder.append("a = 2*tg(180/n)*r = ").append(a.toString());

                if(angle == 0) {
                    angle = 180 * (n - 2.0) / n;
                    builder.append("α = 180(n-2)/n = ").append(angle);
                }

                if(R.fromNullString) {
                    R = a.getDivide(AngleFunctions.getSin(180.0 / n));
                    R.divide(2);
                    builder.append("R = a/(2*sin(180/n)) = ").append(R.toString());
                }

                return builder.toString();
            }

            if(angle != 0){
                n = (int) (360/(180-angle));
                builder.append("n = 360/(180-α) = ");
                builder.append(n);

                a = AngleFunctions.getTg(180.0 / n).getMultiply(r).getMultiply(2);
                builder.append("a = 2*tg(180/n)*r = ").append(a.toString());

                if(R.fromNullString) {
                    R = a.getDivide(AngleFunctions.getSin(180.0 / n));
                    R.divide(2);
                    builder.append("R = a/(2*sin(180/n)) = ").append(R.toString());
                }

                return builder.toString();
            }

        }

        if(!R.fromNullString){
            if(!a.fromNullString){
                n = (int) (180/AngleFunctions.getAngleBySin(a.getDivide(R).getDivide(2)));
                builder.append("n = 180/arcsin(a/2R) = ").append(n);

                if(angle == 0) {
                    angle = 180 * (n - 2.0) / n;
                    builder.append("α = 180(n-2)/n = ").append(angle);
                }

                if(r.fromNullString) {
                    r = a.getDivide(AngleFunctions.getTg(180.0 / n));
                    r.divide(2);
                    builder.append("r = a/(2*tg(180/n)) = ").append(r.toString());
                }
                return builder.toString();
            }

            if(n != 0){
                a = R.getMultiply(AngleFunctions.getSin(180.0/n)).getMultiply(2);
                builder.append("a = 2R * sin(180/n) = ").append(a.toString());

                if(angle == 0) {
                    angle = 180 * (n - 2.0) / n;
                    builder.append("α = 180(n-2)/n = ").append(angle);
                }

                if(r.fromNullString) {
                    r = a.getDivide(AngleFunctions.getTg(180.0 / n));
                    r.divide(2);
                    builder.append("r = a/(2*tg(180/n)) = ").append(r.toString());
                }

                return builder.toString();
            }

            if(angle != 0){
                n = (int) (360/(180-angle));
                builder.append("n = 360/(180-α) = ");
                builder.append(n);

                a = R.getMultiply(AngleFunctions.getSin(180.0/n)).getMultiply(2);
                builder.append("a = 2R * sin(180/n) = ").append(a.toString());

                if(r.fromNullString) {
                    r = a.getDivide(AngleFunctions.getTg(180.0 / n));
                    r.divide(2);
                    builder.append("r = a/(2*tg(180/n)) = ").append(r.toString());
                }
                return builder.toString();
            }
        }

        if(!R.fromNullString && !r.fromNullString){
            angle = AngleFunctions.getAngleByCos(r.getDivide(R));
            builder.append("α = arccos(r/R) = ").append(angle);

            if(n == 0) {
                n = (int) (360 / (180 - angle));
                builder.append("n = 360/(180-α) = ");
                builder.append(n);
            }

            if(a.fromNullString) {
                a = R.getMultiply(AngleFunctions.getSin(180.0 / n)).getMultiply(2);
                builder.append("a = 2R * sin(180/n) = ").append(a.toString());
            }
            return builder.toString();
        }

        throw new Exception("CANNOT SOLVE");
    }

}