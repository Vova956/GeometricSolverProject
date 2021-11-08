package com.example.geometricsolver3;

public class Calculation1SolveClass {
    private SquareNumber R;
    private SquareNumber r;
    private SquareNumber a;
    public boolean canSolve = true;

    public Calculation1SolveClass(SquareNumber r, SquareNumber r1, SquareNumber a) {
        R = r;
        this.r = r1;
        this.a = a;
    }

    public String solve() throws GeometryException {

        SquareNumber squareNumber3 = new SquareNumber("√(3)");
        SquareNumber squareNumber23 = new SquareNumber("2√(3)");

        if(!R.fromNullString && !r.fromNullString && !r.getMultiply(2).equals(R))
            canSolve = false;

        if(!R.fromNullString && !a.fromNullString && !R.getMultiply(squareNumber3).equals(a))
            canSolve = false;

        if(!a.fromNullString && !r.fromNullString && !r.getMultiply(squareNumber23).equals(a))
            canSolve = false;

        if(canSolve) {
            StringBuilder answerString = new StringBuilder();

            if (R.fromNullString) {
                if (!r.fromNullString) {
                    R.multiply(r);
                    R.multiply(2);
                    answerString.append("R = 2r = 2 * " + r.toString() + " = " + R.toString());

                    double buff = R.toDouble();
                    if (buff != (int) (buff * 10000.0) / 10000.0) {
                        answerString.append(" ≈ " + (int) (buff * 10000.0) / 10000.0);
                    }
                    answerString.append("\n");
                } else if (!a.fromNullString) {
                    R = a.getDivide(squareNumber3);
                    answerString.append("R = a/(√3) = " + a.toString() + "/(√3)  = " + R.toString());

                    double buff = R.toDouble();
                    if (buff != ((int) (buff * 10000.0)) / 10000.0) {
                        answerString.append(" ≈ " + (int) (buff * 10000.0) / 10000.0);
                    }
                    answerString.append("\n");
                }
                R.setFromNullString(true);
            }

            if (r.fromNullString) {
                if (!R.fromNullString) {
                    r = R.getDivide(2);
                    answerString.append("r = R/2 = " + R.toString() + "/2 = " + r.toString());

                    double buff = r.toDouble();
                    if (buff != (int) (buff * 10000.0) / 10000.0) {
                        answerString.append(" ≈ " + (int) (buff * 10000.0) / 10000.0);
                    }
                    answerString.append("\n");
                } else if (!a.fromNullString) {
                    r = a.getDivide(squareNumber23);
                    answerString.append("r = a/(2√3) = " + a.toString() + "/(2√3) = " + r.toString());

                    double buff = r.toDouble();
                    if (buff != (int) (buff * 10000.0) / 10000.0) {
                        answerString.append(" ≈ " + (int) (buff * 10000.0) / 10000.0);
                    }
                    answerString.append("\n");

                }
                r.setFromNullString(true);
            }

            if (a.fromNullString) {
                if (!R.fromNullString) {
                    a = R.getMultiply(squareNumber3);
                    answerString.append("a = R * √3 = " + R.toString() + " * √3 = " + a.toString());

                    double buff = a.toDouble();
                    if (buff != (int) (buff * 10000.0) / 10000.0) {
                        answerString.append(" ≈ " + (int) (buff * 10000.0) / 10000.0);
                    }
                    answerString.append("\n");
                }

                if (!r.fromNullString) {
                    a = r.getMultiply(squareNumber23);
                    answerString.append("a = r * 2√3 = " + r.toString() + " * 2√3 = " + a.toString());

                    double buff = a.toDouble();
                    if (buff != (int) (buff * 10000.0) / 10000.0) {
                        answerString.append(" ≈ " + (int) (buff * 10000.0) / 10000.0);
                    }
                    answerString.append("\n");
                }
            }


            return answerString.toString();
        }
        else if(!canSolve){
            throw  new GeometryException("INVALID MATH INPUT");
        }

        return null;
    }

}
