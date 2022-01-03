package com.example.geometricsolver3;
public class Calculation4SolveClass {
    private SquareNumber P;
    private SquareNumber S;
    private SquareNumber a;
    private  SquareNumber d;

    public Calculation4SolveClass(SquareNumber p, SquareNumber s, SquareNumber a, SquareNumber d) {
        P = p;
        S = s;
        this.a = a;
        this.d = d;
    }

    public String solve() throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        SquareNumber two = new SquareNumber(1,2);

        if(!a.fromNullString ){
            if(!P.fromNullString && a.toDouble() != P.getDivide(4).toDouble())
                throw new Exception("a ≠ P/4");
            if(!S.fromNullString && S.getRoot() != a.toDouble())
                throw new Exception("a ≠ √S ");
            if(!d.fromNullString && a.toDouble() != d.getDivide(two).toDouble())
                throw new Exception("a√2 ≠ d");
        }

        if(!S.fromNullString){
            if(!P.fromNullString && P.getDivide(4).toDouble() != S.getRoot())
                throw new Exception("√S ≠ P/4");
            if(!d.fromNullString && d.getDivide(two).toDouble() != S.getRoot())
                throw new Exception("√S ≠ d/√2");
        }

        if(!P.fromNullString && !d.fromNullString && P.getDivide(4).toDouble() != d.getDivide(two).toDouble())
            throw new Exception("P/4 ≠ d/√2");

        if(!a.fromNullString){
            if(d.fromNullString) {
                d = a.getMultiply(two);
                stringBuilder.append("d = a * √2 = ");
                stringBuilder.append(a);
                stringBuilder.append(" * √2 = ");
                stringBuilder.append(d);
                stringBuilder.append("\n");
            }
            if(S.fromNullString) {
                S = a.getMultiply(a);
                stringBuilder.append("S =  a * a = ");
                stringBuilder.append(a);
                stringBuilder.append(" * ");
                stringBuilder.append(a);
                stringBuilder.append(" = ");
                stringBuilder.append(S);
                stringBuilder.append("\n");

            }
            if(P.fromNullString){
                P = a.getMultiply(4);
                stringBuilder.append("P = a * 4 = ");
                stringBuilder.append(a);
                stringBuilder.append(" * 4");
                stringBuilder.append(" = ");
                stringBuilder.append(P);
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }

        else if(!d.fromNullString){
            a = d.getDivide(two);
            stringBuilder.append("a = d/√2 = ");
            stringBuilder.append(d);
            stringBuilder.append("/√2 = ");
            stringBuilder.append(a);
            stringBuilder.append("\n");

            if(S.fromNullString) {
                S = a.getMultiply(a);
                stringBuilder.append("d =  a * a = ");
                stringBuilder.append(a);
                stringBuilder.append(" * ");
                stringBuilder.append(a);
                stringBuilder.append(" = ");
                stringBuilder.append(S);
                stringBuilder.append("\n");

            }

            if(P.fromNullString){
                P = a.getMultiply(4);
                stringBuilder.append("P = a * 4 = ");
                stringBuilder.append(a);
                stringBuilder.append(" * 4");
                stringBuilder.append(" = ");
                stringBuilder.append(P);
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }
        else if(!P.fromNullString){
            a = P.getDivide(4);
            stringBuilder.append("a = P/4 = ");
            stringBuilder.append(P);
            stringBuilder.append("/4 = ");
            stringBuilder.append(a);
            stringBuilder.append("\n");

            d = a.getMultiply(two);
            stringBuilder.append("d =  a * √2 = ");
            stringBuilder.append(a);
            stringBuilder.append(" * √2 = ");
            stringBuilder.append(d);
            stringBuilder.append("\n");

            if(S.fromNullString){
                S = a.getMultiply(a);
                stringBuilder.append("S =  a * a = ");
                stringBuilder.append(a);
                stringBuilder.append(" * ");
                stringBuilder.append(a);
                stringBuilder.append(" = ");
                stringBuilder.append(S);
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }
        else if(!S.fromNullString){
            a = new SquareNumber(1,S.toDouble());
            stringBuilder.append("a = √S = √");
            stringBuilder.append(S);
            stringBuilder.append(" = ");
            stringBuilder.append(a);
            stringBuilder.append("\n");

            d = a.getMultiply(two);
            stringBuilder.append("d =  a * √2 = ");
            stringBuilder.append(a);
            stringBuilder.append(" * √2 = ");
            stringBuilder.append(d);
            stringBuilder.append("\n");

            P = a.getMultiply(4);
            stringBuilder.append("P =  a * 4 = ");
            stringBuilder.append(a);
            stringBuilder.append(" *  4");
            stringBuilder.append(" = ");
            stringBuilder.append(P);
            stringBuilder.append("\n");

            return stringBuilder.toString();
        }

        throw new Exception("CANNOT SOLVE");
    }
}
