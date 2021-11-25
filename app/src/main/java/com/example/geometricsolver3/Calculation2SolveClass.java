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

    public double cosTheoremForAngle(SquareNumber oppositeSide, SquareNumber side1, SquareNumber side2) {
        SumOfSquareNumbers sum = new SumOfSquareNumbers();

        sum.addToSum(side1.getSquareOf());
        sum.addToSum(side2.getSquareOf());
        sum.addToSum(oppositeSide.getSquareOf().changeSign());

        double rest1 = sum.divide(2);
        SquareNumber rest2 = sum.divide(side1.getMultiply(side2));

        return Math.toDegrees(Math.acos(sum.toDouble() / (rest1 * rest2.toDouble())));
    }

    public SquareRootSum cosTheoremForSide(SquareNumber side1, SquareNumber side2, double angle) {
        SumOfSquareNumbers sum = new SumOfSquareNumbers();

        sum.addToSum(side1.getSquareOf());
        sum.addToSum(side2.getSquareOf());
        sum.addToSum(side1.getMultiply(side2).getMultiply(-2).getMultiply(AngleFunctions.getCos(angle)));


        SquareRootSum sq = new SquareRootSum(sum);
        sq.getIntNumber();
        return sq;

    }

    private void solveTriangleByThreeSides() {
        angle_a = cosTheoremForAngle(side_a, side_b, side_c);
        angle_b = cosTheoremForAngle(side_b, side_a, side_c);
        angle_c = cosTheoremForAngle(side_c, side_b, side_a);
    }

    public String solve() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        int AmountOfKnownSides = getAmountOfKnownSides();
        int AmountOfKnownAngles = getAmountOfKnownAngles();

        if(AmountOfKnownSides == 3){
            if (side_a.toDouble() + side_b.toDouble() <= side_c.toDouble() || side_b.toDouble() + side_c.toDouble() <= side_a.toDouble() ||
                    side_a.toDouble() + side_c.toDouble() <= side_b.toDouble()) {
                throw new Exception("TRIANGLE CAN NOT EXIST");
            }
        }


        if (angle_a + angle_c + angle_b > 180) {
            throw new Exception("∠α + ∠β + ∠γ ≠ 180°");
        }

        if (!side_a.fromNullString && !side_b.fromNullString && !side_c.fromNullString) {
            if (angle_a != 0) {
                if (cosTheoremForAngle(side_a, side_b, side_c) != angle_a)
                    throw new Exception("a² ≠ b² + c² -2bc * cosα");
            }

            if (angle_b != 0) {
                if (cosTheoremForAngle(side_b, side_a, side_c) != angle_b)
                    throw new Exception("b² ≠ a² + c² -2ac * cosβ");
            }

            if (angle_c != 0) {
                if (cosTheoremForAngle(side_c, side_b, side_a) != angle_c)
                    throw new Exception("c² ≠ a² + b² -2ab * cosγ");
            }
        }

        if (!side_a.fromNullString && angle_a != 0) {
            if (!side_b.fromNullString && angle_b != 0) {
                if (!side_b.getMultiply(AngleFunctions.getSin(angle_a)).equals(side_a.getMultiply(AngleFunctions.getSin(angle_b))))
                    throw new Exception("a * sinβ ≠ b * sinα");
            }
            if (!side_c.fromNullString && angle_c != 0) {
                if (!side_c.getMultiply(AngleFunctions.getSin(angle_a)).equals(side_a.getMultiply(AngleFunctions.getSin(angle_c))))
                    throw new Exception("c * sinα ≠ a * sinγ");
            }
        }

        if (!side_c.fromNullString && angle_c != 0 && !side_b.fromNullString && angle_b != 0) {
            if (!side_c.getMultiply(AngleFunctions.getSin(angle_b)).equals(side_b.getMultiply(AngleFunctions.getSin(angle_c))))
                throw new Exception("c * sinβ ≠ b * sinγ");
        }

        if (!side_b.fromNullString && !side_a.fromNullString && !side_c.fromNullString && angle_a != 0 && angle_b != 0 && angle_c != 0) {
            stringBuilder.append("Triangle is already solved");
            return stringBuilder.toString();
        }

        if (AmountOfKnownSides == 3) {
            if (angle_a == 0 && angle_b != 0 && angle_c != 0) {
                angle_a = 180 - angle_b - angle_c;
                stringBuilder.append("∠α = 180° - ∠β - ∠γ = 180° - ");
                stringBuilder.append(angle_b);
                stringBuilder.append("° - ");
                stringBuilder.append(angle_c);
                stringBuilder.append("° = ");
                stringBuilder.append(angle_a);
                stringBuilder.append("°");

                return stringBuilder.toString();
            }

            if (angle_b == 0 && angle_a != 0 && angle_c != 0) {
                angle_b = 180 - angle_a - angle_c;
                stringBuilder.append("∠β = 180° - ∠α - ∠γ = 180° - ");
                stringBuilder.append(angle_a);
                stringBuilder.append("° - ");
                stringBuilder.append(angle_c);
                stringBuilder.append("° = ");
                stringBuilder.append(angle_b);
                stringBuilder.append("°");

                return stringBuilder.toString();
            }

            if (angle_c == 0 && angle_a != 0 && angle_b != 0) {
                angle_c = 180 - angle_a - angle_b;
                stringBuilder.append("∠γ = 180° - ∠α - ∠β = 180° - ");
                stringBuilder.append(angle_a);
                stringBuilder.append("° - ");
                stringBuilder.append(angle_b);
                stringBuilder.append("° = ");
                stringBuilder.append(angle_c);
                stringBuilder.append("°");

                return stringBuilder.toString();
            }

            if (angle_a != 0) {
                angle_c = AngleFunctions.getAngleBySin(side_c.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(side_a));
                stringBuilder.append("∠γ = arcsin((c * sinα) / a) = arcsin((");
                stringBuilder.append(side_c);
                stringBuilder.append(" * sin");
                stringBuilder.append(angle_a);
                stringBuilder.append("°) /");
                stringBuilder.append(side_a);
                stringBuilder.append(") = ");
                stringBuilder.append(angle_c);
                stringBuilder.append("°\n");

                angle_b = AngleFunctions.getAngleBySin(side_b.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(side_a));
                stringBuilder.append("∠β = arcsin((b * sinα) / a) = arcsin((");
                stringBuilder.append(side_b);
                stringBuilder.append(" * sin");
                stringBuilder.append(angle_a);
                stringBuilder.append("°) /");
                stringBuilder.append(side_a);
                stringBuilder.append(") = ");
                stringBuilder.append(angle_b);
                stringBuilder.append("°");

                return stringBuilder.toString();
            }

            if (angle_b != 0) {
                angle_a = AngleFunctions.getAngleBySin(side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(side_b));
                stringBuilder.append("∠α = arcsin((a * sinβ) / b) = arcsin((");
                stringBuilder.append(side_a);
                stringBuilder.append(" * sin");
                stringBuilder.append(angle_b);
                stringBuilder.append("°) /");
                stringBuilder.append(side_b);
                stringBuilder.append(") = ");
                stringBuilder.append(angle_a);
                stringBuilder.append("°\n");

                angle_c = AngleFunctions.getAngleBySin(side_c.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(side_b));
                stringBuilder.append("∠γ = arcsin((c * sinβ) / b) = arcsin((");
                stringBuilder.append(side_c);
                stringBuilder.append(" * sin");
                stringBuilder.append(angle_b);
                stringBuilder.append("°) /");
                stringBuilder.append(side_b);
                stringBuilder.append(") = ");
                stringBuilder.append(angle_c);
                stringBuilder.append("°");

                return stringBuilder.toString();
            }

            if (angle_c != 0) {
                angle_a = AngleFunctions.getAngleBySin(side_a.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(side_c));
                stringBuilder.append("∠α = arcsin((a * sinγ) / c) = arcsin((");
                stringBuilder.append(side_a);
                stringBuilder.append(" * sin");
                stringBuilder.append(angle_c);
                stringBuilder.append("°) /");
                stringBuilder.append(side_c);
                stringBuilder.append(") = ");
                stringBuilder.append(angle_a);
                stringBuilder.append("°\n");

                angle_b = AngleFunctions.getAngleBySin(side_b.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(side_c));
                stringBuilder.append("∠β = arcsin((b * sinγ) / c) = arcsin((");
                stringBuilder.append(side_b);
                stringBuilder.append(" * sin");
                stringBuilder.append(angle_c);
                stringBuilder.append("°) /");
                stringBuilder.append(side_c);
                stringBuilder.append(") = ");
                stringBuilder.append(angle_b);
                stringBuilder.append("°");

                return stringBuilder.toString();
            } else {
                solveTriangleByThreeSides();
                stringBuilder.append("∠α = arccos((b² + c² - a²)/2bc) = ");
                stringBuilder.append(angle_a);
                stringBuilder.append("°\n");

                stringBuilder.append("∠β = arccos((a² + c² - b²)/2ac) = ");
                stringBuilder.append(angle_b);
                stringBuilder.append("°\n");

                stringBuilder.append("∠γ = arccos((a² + b² - c²)/2ab) = ");
                stringBuilder.append(angle_c);
                stringBuilder.append("°");

                return stringBuilder.toString();
            }


        }

        //by to sides
        else if (AmountOfKnownSides == 2) {
            if (AmountOfKnownAngles == 3) {
                if (side_a.fromNullString) {
                    side_a = side_b.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(AngleFunctions.getSin(angle_b));
                    stringBuilder.append("a = (sinα * b) / sinβ = (");
                    stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                    stringBuilder.append(" * ");
                    stringBuilder.append(side_b);
                    stringBuilder.append(") / ");
                    stringBuilder.append(AngleFunctions.getSin(angle_b).toString());
                    stringBuilder.append(" = ");
                    stringBuilder.append(side_a);

                    return stringBuilder.toString();
                }

                if (side_b.fromNullString) {
                    side_b = side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(AngleFunctions.getSin(angle_a));
                    stringBuilder.append("b = (sinβ * a) / sinα = (");
                    stringBuilder.append(AngleFunctions.getSin(angle_b).toString());
                    stringBuilder.append(" * ");
                    stringBuilder.append(side_a);
                    stringBuilder.append(") / ");
                    stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                    stringBuilder.append(" = ");
                    stringBuilder.append(side_b);

                    return stringBuilder.toString();
                }

                if (side_c.fromNullString) {
                    side_c = side_a.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(AngleFunctions.getSin(angle_a));
                    stringBuilder.append("c = (sinγ * a) / sinα = (");
                    stringBuilder.append(AngleFunctions.getSin(angle_c).toString());
                    stringBuilder.append(" * ");
                    stringBuilder.append(side_a);
                    stringBuilder.append(") / ");
                    stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                    stringBuilder.append(" = ");
                    stringBuilder.append(side_c);

                    return stringBuilder.toString();
                }
            }

            else if (getAmountOfKnownAngles() == 2) {
                if (angle_b == 0) {
                    angle_b = 180 - angle_a - angle_c;
                    stringBuilder.append("∠β = 180° - ∠α - ∠γ = 180° - ");
                    stringBuilder.append(angle_a);
                    stringBuilder.append("° - ");
                    stringBuilder.append(angle_c);
                    stringBuilder.append("° = ");
                    stringBuilder.append(angle_b);
                    stringBuilder.append("°\n");

                    stringBuilder.append(findOneSideWithThreeAngles());
                    return stringBuilder.toString();
                }

                if (angle_a == 0) {
                    angle_a = 180 - angle_b - angle_c;
                    stringBuilder.append("∠α = 180° - ∠β - ∠γ = 180° - ");
                    stringBuilder.append(angle_b);
                    stringBuilder.append("° - ");
                    stringBuilder.append(angle_c);
                    stringBuilder.append("° = ");
                    stringBuilder.append(angle_a);
                    stringBuilder.append("°\n");

                    stringBuilder.append(findOneSideWithThreeAngles());
                    return stringBuilder.toString();
                }

                if (angle_c == 0) {
                    angle_c = 180 - angle_a - angle_b;
                    stringBuilder.append("∠γ = 180° - ∠α - ∠β = 180° - ");
                    stringBuilder.append(angle_a);
                    stringBuilder.append("° - ");
                    stringBuilder.append(angle_b);
                    stringBuilder.append("° = ");
                    stringBuilder.append(angle_c);
                    stringBuilder.append("°\n");

                    stringBuilder.append(findOneSideWithThreeAngles());
                    return stringBuilder.toString();
                }

            }
            else if(AmountOfKnownAngles == 1){
                if(!side_a.fromNullString && !side_b.fromNullString){
                    if(angle_b != 0){
                        angle_a = AngleFunctions.getAngleBySin(side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(side_b));
                        stringBuilder.append("∠α = arcsin((a * sinβ) / b) = arcsin((");
                        stringBuilder.append(side_a);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_b);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°\n");

                        angle_c = 180 - angle_a - angle_b;
                        stringBuilder.append("∠γ = 180° - ∠α - ∠β = 180° - ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°\n");

                        side_c = side_a.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(AngleFunctions.getSin(angle_a));
                        stringBuilder.append("c = (sinγ * a) / sinα = (");
                        stringBuilder.append(AngleFunctions.getSin(angle_c).toString());
                        stringBuilder.append(" * ");
                        stringBuilder.append(side_a);
                        stringBuilder.append(") / ");
                        stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                        stringBuilder.append(" = ");
                        stringBuilder.append(side_c);

                        return stringBuilder.toString();
                    }

                    else if(angle_a != 0){
                        angle_b = AngleFunctions.getAngleBySin(side_b.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(side_a));
                        stringBuilder.append("∠β = arcsin((b * sinα) / a) = arcsin((");
                        stringBuilder.append(side_b);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_a);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°\n");

                        angle_c = 180 - angle_a - angle_b;
                        stringBuilder.append("∠γ = 180° - ∠α - ∠β = 180° - ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°\n");

                        side_c = side_a.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(AngleFunctions.getSin(angle_a));
                        stringBuilder.append("c = (sinγ * a) / sinα = (");
                        stringBuilder.append(AngleFunctions.getSin(angle_c).toString());
                        stringBuilder.append(" * ");
                        stringBuilder.append(side_a);
                        stringBuilder.append(") / ");
                        stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                        stringBuilder.append(" = ");
                        stringBuilder.append(side_c);

                        return stringBuilder.toString();
                    }

                    else if(angle_c != 0){
                        SquareRootSum defined_c = cosTheoremForSide(side_b,side_a,angle_c);
                        stringBuilder.append("c = √(a² + b² -2ab * cosγ) = ");
                        stringBuilder.append(defined_c.toString());
                        stringBuilder.append("\n");

                        angle_b = AngleFunctions.getAngleBySin(side_b.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(defined_c.toDouble()));
                        stringBuilder.append("∠β = arcsin((b * sinγ) / c) = arcsin((");
                        stringBuilder.append(side_b);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_c);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°\n");

                        angle_a = 180 - angle_b - angle_c;
                        stringBuilder.append("∠α = 180° - ∠β - ∠γ = 180° - ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°\n");

                        return stringBuilder.toString();
                    }
                }

                if(!side_a.fromNullString && !side_c.fromNullString){
                    if(angle_a != 0){
                        angle_c = AngleFunctions.getAngleBySin(side_c.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(side_a));
                        stringBuilder.append("∠γ = arcsin((c * sinα) / a) = arcsin((");
                        stringBuilder.append(side_c);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_a);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°\n");

                        angle_b = 180 - angle_a - angle_c;
                        stringBuilder.append("∠β = 180° - ∠α - ∠γ = 180° - ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°\n");

                        side_b = side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(AngleFunctions.getSin(angle_a));
                        stringBuilder.append("b = (sinβ * a) / sinα = (");
                        stringBuilder.append(AngleFunctions.getSin(angle_b).toString());
                        stringBuilder.append(" * ");
                        stringBuilder.append(side_a);
                        stringBuilder.append(") / ");
                        stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                        stringBuilder.append(" = ");
                        stringBuilder.append(side_b);

                        return stringBuilder.toString();
                    }

                    else if(angle_c != 0){
                        angle_a = AngleFunctions.getAngleBySin(side_a.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(side_c));
                        stringBuilder.append("∠α = arcsin((a * sinγ) / c) = arcsin((");
                        stringBuilder.append(side_a);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_c);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°\n");

                        angle_b = 180 - angle_a - angle_c;
                        stringBuilder.append("∠β = 180° - ∠α - ∠γ = 180° - ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°\n");

                        side_b = side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(AngleFunctions.getSin(angle_a));
                        stringBuilder.append("b = (sinβ * a) / sinα = (");
                        stringBuilder.append(AngleFunctions.getSin(angle_b).toString());
                        stringBuilder.append(" * ");
                        stringBuilder.append(side_a);
                        stringBuilder.append(") / ");
                        stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                        stringBuilder.append(" = ");
                        stringBuilder.append(side_b);

                        return stringBuilder.toString();
                    }

                    else if(angle_b != 0){
                        SquareRootSum defined_b = cosTheoremForSide(side_c,side_a,angle_b);
                        stringBuilder.append("b = √(a² + c² -2ac * cosβ = ");
                        stringBuilder.append(defined_b.toString());
                        stringBuilder.append("\n");

                        angle_a = AngleFunctions.getAngleBySin(side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(defined_b.toDouble()));
                        stringBuilder.append("∠α = arcsin((a * sinβ) / b) = arcsin((");
                        stringBuilder.append(side_a);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_b);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°\n");

                        angle_c = 180 - angle_a - angle_b;
                        stringBuilder.append("∠γ = 180° - ∠α - ∠β = 180° - ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°\n");


                        return stringBuilder.toString();

                    }
                }

                if(!side_b.fromNullString && !side_c.fromNullString){
                    if(angle_b != 0){
                        angle_c = AngleFunctions.getAngleBySin(side_c.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(side_b));
                        stringBuilder.append("∠γ = arcsin((c * sinβ) / b) = arcsin((");
                        stringBuilder.append(side_c);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_b);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°\n");

                        angle_a = 180 - angle_b - angle_c;
                        stringBuilder.append("∠α = 180° - ∠β - ∠γ = 180° - ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°\n");

                        side_a = side_b.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(AngleFunctions.getSin(angle_b));
                        stringBuilder.append("a = (sinα * b) / sinβ = (");
                        stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                        stringBuilder.append(" * ");
                        stringBuilder.append(side_b);
                        stringBuilder.append(") / ");
                        stringBuilder.append(AngleFunctions.getSin(angle_b).toString());
                        stringBuilder.append(" = ");
                        stringBuilder.append(side_a);

                        return stringBuilder.toString();
                    }

                    else if(angle_c != 0){
                        angle_b = AngleFunctions.getAngleBySin(side_b.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(side_c));
                        stringBuilder.append("∠β = arcsin((b * sinγ) / c) = arcsin((");
                        stringBuilder.append(side_b);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_c);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°\n");

                        angle_a = 180 - angle_b - angle_c;
                        stringBuilder.append("∠α = 180° - ∠β - ∠γ = 180° - ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°\n");

                        side_a = side_b.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(AngleFunctions.getSin(angle_b));
                        stringBuilder.append("a = (sinα * b) / sinβ = (");
                        stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
                        stringBuilder.append(" * ");
                        stringBuilder.append(side_b);
                        stringBuilder.append(") / ");
                        stringBuilder.append(AngleFunctions.getSin(angle_b).toString());
                        stringBuilder.append(" = ");
                        stringBuilder.append(side_a);

                        return stringBuilder.toString();
                    }

                    else if(angle_a != 0){
                        SquareRootSum defined_a = cosTheoremForSide(side_c,side_b,angle_a);
                        stringBuilder.append("a = √(b² + c² -2bc * cosα = ");
                        stringBuilder.append(defined_a.toString());
                        stringBuilder.append("\n");

                        angle_b = AngleFunctions.getAngleBySin(side_b.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(defined_a.toDouble()));
                        stringBuilder.append("∠β = arcsin((b * sinα) / a) = arcsin((");
                        stringBuilder.append(side_b);
                        stringBuilder.append(" * sin");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("°) /");
                        stringBuilder.append(side_a);
                        stringBuilder.append(") = ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("°\n");

                        angle_c = 180 - angle_a - angle_b;
                        stringBuilder.append("∠γ = 180° - ∠α - ∠β = 180° - ");
                        stringBuilder.append(angle_a);
                        stringBuilder.append("° - ");
                        stringBuilder.append(angle_b);
                        stringBuilder.append("° = ");
                        stringBuilder.append(angle_c);
                        stringBuilder.append("°\n");


                        return stringBuilder.toString();

                    }
                }
            }


        }

        else if (AmountOfKnownSides == 1){
            if(AmountOfKnownAngles ==  3){
                return FindAllSidesWithThreeAngles();
            }
            else if(AmountOfKnownAngles == 2){
                if (angle_b == 0) {
                    angle_b = 180 - angle_a - angle_c;
                    stringBuilder.append("∠β = 180° - ∠α - ∠γ = 180° - ");
                    stringBuilder.append(angle_a);
                    stringBuilder.append("° - ");
                    stringBuilder.append(angle_c);
                    stringBuilder.append("° = ");
                    stringBuilder.append(angle_b);
                    stringBuilder.append("°\n");

                    stringBuilder.append(FindAllSidesWithThreeAngles());
                }

                if (angle_a == 0) {
                    angle_a = 180 - angle_b - angle_c;
                    stringBuilder.append("∠α = 180° - ∠β - ∠γ = 180° - ");
                    stringBuilder.append(angle_b);
                    stringBuilder.append("° - ");
                    stringBuilder.append(angle_c);
                    stringBuilder.append("° = ");
                    stringBuilder.append(angle_a);
                    stringBuilder.append("°\n");

                    stringBuilder.append(FindAllSidesWithThreeAngles());
                }

                if (angle_c == 0) {
                    angle_c = 180 - angle_a - angle_b;
                    stringBuilder.append("∠γ = 180° - ∠α - ∠β = 180° - ");
                    stringBuilder.append(angle_a);
                    stringBuilder.append("° - ");
                    stringBuilder.append(angle_b);
                    stringBuilder.append("° = ");
                    stringBuilder.append(angle_c);
                    stringBuilder.append("°\n");

                    stringBuilder.append(FindAllSidesWithThreeAngles());
                }
                stringBuilder.append(findOneSideWithThreeAngles());
                return stringBuilder.toString();
            }
        }

        throw new Exception("CAN NOT SOLVE TRIANGLE ");
    }

    private String FindAllSidesWithThreeAngles(){
        StringBuilder stringBuilder1 = new StringBuilder();
        if(!side_a.fromNullString){
            side_b = side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(AngleFunctions.getSin(angle_a));
            stringBuilder1.append("b = (sinβ * a) / sinα = (");
            stringBuilder1.append(AngleFunctions.getSin(angle_b).toString());
            stringBuilder1.append(" * ");
            stringBuilder1.append(side_a);
            stringBuilder1.append(") / ");
            stringBuilder1.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder1.append(" = ");
            stringBuilder1.append(side_b);
            stringBuilder1.append("\n");

            side_c = side_a.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(AngleFunctions.getSin(angle_a));
            stringBuilder1.append("c = (sinγ * a) / sinα = (");
            stringBuilder1.append(AngleFunctions.getSin(angle_c).toString());
            stringBuilder1.append(" * ");
            stringBuilder1.append(side_a);
            stringBuilder1.append(") / ");
            stringBuilder1.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder1.append(" = ");
            stringBuilder1.append(side_c);

            return stringBuilder1.toString();
        }

        if(!side_b.fromNullString){
            side_a = side_b.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(AngleFunctions.getSin(angle_b));
            stringBuilder1.append("a = (sinα * b) / sinβ = (");
            stringBuilder1.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder1.append(" * ");
            stringBuilder1.append(side_b);
            stringBuilder1.append(") / ");
            stringBuilder1.append(AngleFunctions.getSin(angle_b).toString());
            stringBuilder1.append(" = ");
            stringBuilder1.append(side_a);
            stringBuilder1.append("\n");

            side_c = side_a.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(AngleFunctions.getSin(angle_a));
            stringBuilder1.append("c = (sinγ * a) / sinα = (");
            stringBuilder1.append(AngleFunctions.getSin(angle_c).toString());
            stringBuilder1.append(" * ");
            stringBuilder1.append(side_a);
            stringBuilder1.append(") / ");
            stringBuilder1.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder1.append(" = ");
            stringBuilder1.append(side_c);

            return stringBuilder1.toString();
        }

        if(!side_c.fromNullString){
            side_a = side_c.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(AngleFunctions.getSin(angle_c));
            stringBuilder1.append("a = (sinα * c) / sinγ = (");
            stringBuilder1.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder1.append(" * ");
            stringBuilder1.append(side_c);
            stringBuilder1.append(") / ");
            stringBuilder1.append(AngleFunctions.getSin(angle_c).toString());
            stringBuilder1.append(" = ");
            stringBuilder1.append(side_a);
            stringBuilder1.append("\n");

            side_b = side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(AngleFunctions.getSin(angle_a));
            stringBuilder1.append("b = (sinβ * a) / sinα = (");
            stringBuilder1.append(AngleFunctions.getSin(angle_b).toString());
            stringBuilder1.append(" * ");
            stringBuilder1.append(side_a);
            stringBuilder1.append(") / ");
            stringBuilder1.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder1.append(" = ");
            stringBuilder1.append(side_b);

            return stringBuilder1.toString();
        }
        return "";
    }

    private String findOneSideWithThreeAngles() {
        StringBuilder stringBuilder = new StringBuilder();
        if (side_a.fromNullString) {
            side_a = side_b.getMultiply(AngleFunctions.getSin(angle_a)).getDivide(AngleFunctions.getSin(angle_b));
            stringBuilder.append("a = (sinα * b) / sinβ = (");
            stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder.append(" * ");
            stringBuilder.append(side_b);
            stringBuilder.append(") / ");
            stringBuilder.append(AngleFunctions.getSin(angle_b).toString());
            stringBuilder.append(" = ");
            stringBuilder.append(side_a);

            return stringBuilder.toString();
        }

        if (side_b.fromNullString) {
            side_b = side_a.getMultiply(AngleFunctions.getSin(angle_b)).getDivide(AngleFunctions.getSin(angle_a));
            stringBuilder.append("b = (sinβ * a) / sinα = (");
            stringBuilder.append(AngleFunctions.getSin(angle_b).toString());
            stringBuilder.append(" * ");
            stringBuilder.append(side_a);
            stringBuilder.append(") / ");
            stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder.append(" = ");
            stringBuilder.append(side_b);

            return stringBuilder.toString();
        }

        if (side_c.fromNullString) {
            side_c = side_a.getMultiply(AngleFunctions.getSin(angle_c)).getDivide(AngleFunctions.getSin(angle_a));
            stringBuilder.append("c = (sinγ * a) / sinα = (");
            stringBuilder.append(AngleFunctions.getSin(angle_c).toString());
            stringBuilder.append(" * ");
            stringBuilder.append(side_a);
            stringBuilder.append(") / ");
            stringBuilder.append(AngleFunctions.getSin(angle_a).toString());
            stringBuilder.append(" = ");
            stringBuilder.append(side_c);

            return stringBuilder.toString();
        }
        return "";
    }

    private int getAmountOfKnownSides() {
        int amount = 0;
        if (!side_c.fromNullString)
            amount++;
        if (!side_b.fromNullString)
            amount++;
        if (!side_a.fromNullString)
            amount++;

        return amount;
    }

    private int getAmountOfKnownAngles() {
        int amount = 0;
        if (angle_c != 0)
            amount++;
        if (angle_b != 0)
            amount++;
        if (angle_a != 0)
            amount++;

        return amount;
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