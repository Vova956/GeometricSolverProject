package com.geom.geometricsolver3;

public class Calculation6SolveClass implements ISolver{
    private SquareNumber a;
    private SquareNumber b;
    private SquareNumber c;
    private SquareNumber h;

    public Calculation6SolveClass(SquareNumber a, SquareNumber b, SquareNumber c, SquareNumber h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = h;
    }

    public String solve() throws GeometryException {
        if (!a.fromNullString && !b.fromNullString && !c.fromNullString) {
            if (a.getSquareOf().toDouble() + b.getSquareOf().toDouble() != c.getSquareOf().toDouble()) {
                throw new GeometryException("a² + b² ≠ c²");
            }

            if (!h.fromNullString) {
                if (c.getMultiply(h).toDouble() != a.getMultiply(b).toDouble()) {
                    throw new GeometryException("h * c ≠ a * b");
                }
            }
        }

        StringBuilder builder = new StringBuilder();

        if (!a.fromNullString) {
            if (!b.fromNullString) {
                if (c.fromNullString) {
                    SumOfSquareNumbers sum = new SumOfSquareNumbers();
                    sum.addToSum(a.getSquareOf());
                    sum.addToSum(b.getSquareOf());
                    SquareRootSum root = new SquareRootSum(sum);

                    c = new SquareNumber(root.toDouble(), 1);


                    builder.append(" c = √(a² + b²) = ").append(root.toString());
                    if (root.toDouble() != (int) root.toDouble()) {
                        builder.append(" ≈ ").append(c.toString());
                    }
                }

                if (h.fromNullString) {
                    h = a.getMultiply(b).getDivide(c);
                    builder.append("h = a*b/c = ").append(h.toString());
                }

                return builder.toString();
            }

            if (!c.fromNullString) {
                SumOfSquareNumbers sum = new SumOfSquareNumbers();
                sum.addToSum(c.getSquareOf());
                sum.addToSum(b.getSquareOf().getMultiply(-1));
                SquareRootSum root = new SquareRootSum(sum);

                b = new SquareNumber(root.toDouble(), 1);


                builder.append("b = √(c² - a²) = ").append(root.toString());
                if (root.toDouble() != (int) root.toDouble()) {
                    builder.append(" ≈ ").append(b.toString());
                }

                if(h.fromNullString){
                    h = a.getMultiply(b).getDivide(c);
                    builder.append("h = a*b/c = ").append(h.toString());
                }
                return builder.toString();
            }
        }

        throw new GeometryException("CAN NOT SOLVE");
    }
}
