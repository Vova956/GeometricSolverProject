package com.example.geometricsolver3;

public class Calculation3SolveClass {
    private SquareNumber a;
    private SquareNumber b;
    private SquareNumber c;
    private SquareNumber a1;
    private SquareNumber b1;
    private SquareNumber c1;
    private SquareNumber k;

    public Calculation3SolveClass(SquareNumber a,SquareNumber b,SquareNumber c,SquareNumber a1,SquareNumber b1,SquareNumber c1,SquareNumber k){
        this.a = a;
        this.b = b;
        this.c = c;
        this.a1 = a1;
        this.b1 = b1;
        this.c1 = c1;
        this.k = k;
    }

    public String solve() throws Exception{
        StringBuilder stringBuilder = new StringBuilder();

        if(!canSolve()){
            throw new Exception("CANNOT SOLVE");
        }

        if(!checkTriangles()){
            throw  new Exception("TRIANGLES CANNOT EXIST");
        }

        if(!k.fromNullString){
            if(!a1.fromNullString && !a.fromNullString){
                if(a.toDouble() != k.getMultiply(a1).toDouble())
                    throw new Exception("k ≠ a / a1");
            }

            if(!b1.fromNullString && !b.fromNullString){
                if(b.toDouble() != k.getMultiply(b1).toDouble())
                    throw new Exception("k ≠ b / b1");
            }

            if(!c1.fromNullString && !c.fromNullString) {
                if (c.toDouble() != k.getMultiply(c1).toDouble())
                    throw new Exception("k ≠ c / c1");
            }
        }

        if(!a1.fromNullString && !a.fromNullString){
            if(k.fromNullString) {
                k = a.getDivide(a1);
                stringBuilder.append("k = a/a1 = ");
                stringBuilder.append(a.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(a1.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(k.toString());
                stringBuilder.append("\n");
            }

            if(!b.fromNullString && !b1.fromNullString){
                if(a.getMultiply(b1).toDouble() != b.getMultiply(a1).toDouble())
                    throw new Exception("a / a1 ≠ b / b1");
            }

            if(!c.fromNullString && !c1.fromNullString){
                if(a.getMultiply(c1).toDouble() != c.getMultiply(a1).toDouble())
                    throw new Exception("a / a1 ≠ c / c1");
            }

            if(b1.fromNullString){
                b1 = b.getDivide(k);
                stringBuilder.append("b1 = b / k = ");
                stringBuilder.append(b.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(b1.toString());
                stringBuilder.append("\n");
            }

            if(b.fromNullString){
                b = b1.getMultiply(k);
                stringBuilder.append("b = b1 * k = ");
                stringBuilder.append(b1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(b.toString());
                stringBuilder.append("\n");
            }

            if(c1.fromNullString){
                c1 = c.getDivide(k);
                stringBuilder.append("c1 = c / k = ");
                stringBuilder.append(c.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(c1.toString());
                stringBuilder.append("\n");
            }

            if(c.fromNullString){
                c = c1.getMultiply(k);
                stringBuilder.append("c = c1 * k = ");
                stringBuilder.append(c1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(c.toString());
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }

        else if(!b.fromNullString && !b1.fromNullString){
            if(k.fromNullString) {
                k = b.getDivide(b1);
                stringBuilder.append("k = b/b1 = ");
                stringBuilder.append(b.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(b1.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(k.toString());
                stringBuilder.append("\n");
            }

            if(!c.fromNullString && !c1.fromNullString){
                if(b.getMultiply(c1).toDouble() != b1.getMultiply(c).toDouble()){
                    throw new Exception("b / b1 ≠ c / c1");
                }
            }

            if(a.fromNullString){
                a = a1.getMultiply(k);
                stringBuilder.append("a = a1 * k = ");
                stringBuilder.append(a1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(a.toString());
                stringBuilder.append("\n");
            }

            if(a1.fromNullString){
                a1 = a.getDivide(k);
                stringBuilder.append("a1 = a / k = ");
                stringBuilder.append(a.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(a1.toString());
                stringBuilder.append("\n");
            }

            if(b1.fromNullString){
                b1 = b.getDivide(k);
                stringBuilder.append("b1 = b / k = ");
                stringBuilder.append(b.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(b1.toString());
                stringBuilder.append("\n");
            }

            if(b.fromNullString){
                b = b1.getMultiply(k);
                stringBuilder.append("b = b1 * k = ");
                stringBuilder.append(b1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(b.toString());
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        else if(!c.fromNullString && !c1.fromNullString){
            if(k.fromNullString) {
                k = c.getDivide(c1);
                stringBuilder.append("k = c/c1 = ");
                stringBuilder.append(c.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(c1.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(k.toString());
                stringBuilder.append("\n");
            }

            if(a.fromNullString){
                a = a1.getMultiply(k);
                stringBuilder.append("a = a1 * k = ");
                stringBuilder.append(a1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(a.toString());
                stringBuilder.append("\n");
            }

            if(a1.fromNullString){
                a1 = a.getDivide(k);
                stringBuilder.append("a1 = a / k = ");
                stringBuilder.append(a.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(a1.toString());
                stringBuilder.append("\n");
            }

            if(b1.fromNullString){
                b1 = b.getDivide(k);
                stringBuilder.append("b1 = b / k = ");
                stringBuilder.append(b.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(b1.toString());
                stringBuilder.append("\n");
            }

            if(b.fromNullString){
                b = b1.getMultiply(k);
                stringBuilder.append("b = b1 * k = ");
                stringBuilder.append(b1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(b.toString());
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        else if(!k.fromNullString){
            if(a.fromNullString){
                a = a1.getMultiply(k);
                stringBuilder.append("a = a1 * k = ");
                stringBuilder.append(a1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(a.toString());
                stringBuilder.append("\n");
            }

            if(a1.fromNullString){
                a1 = a.getDivide(k);
                stringBuilder.append("a1 = a / k = ");
                stringBuilder.append(a.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(a1.toString());
                stringBuilder.append("\n");
            }

            if(b1.fromNullString){
                b1 = b.getDivide(k);
                stringBuilder.append("b1 = b / k = ");
                stringBuilder.append(b.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(b1.toString());
                stringBuilder.append("\n");
            }

            if(b.fromNullString){
                b = b1.getMultiply(k);
                stringBuilder.append("b = b1 * k = ");
                stringBuilder.append(b1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(b.toString());
                stringBuilder.append("\n");
            }

            if(c1.fromNullString){
                c1 = c.getDivide(k);
                stringBuilder.append("c1 = c / k = ");
                stringBuilder.append(c.toString());
                stringBuilder.append(" / ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(c1.toString());
                stringBuilder.append("\n");
            }

            if(c.fromNullString){
                c = c1.getMultiply(k);
                stringBuilder.append("c = c1 * k = ");
                stringBuilder.append(c1.toString());
                stringBuilder.append(" * ");
                stringBuilder.append(k.toString());
                stringBuilder.append(" = ");
                stringBuilder.append(c.toString());
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }


        return stringBuilder.toString();
    }

    private boolean canSolve(){
        if(!k.fromNullString){
            int buff = 0;
            if(!a.fromNullString || !a1.fromNullString)
                buff++;
            if(!b.fromNullString || !b1.fromNullString)
                buff++;
            if(!c.fromNullString || !c1.fromNullString)
                buff++;
            return buff == 3;
        }
        else{
            boolean isOne = false;
            if(!a.fromNullString && !a1.fromNullString)
                isOne = true;
            else if(!b.fromNullString && !b1.fromNullString)
                isOne = true;
            else if(!c.fromNullString && !c1.fromNullString)
                isOne = true;

            return isOne && (!a.fromNullString || !a1.fromNullString) && (!b.fromNullString || !b1.fromNullString)
                    && (!c.fromNullString || !c1.fromNullString);

        }
    }

    private boolean checkTriangles(){
        if(!a.fromNullString && !b.fromNullString && !c.fromNullString && (a.toDouble() >= b.toDouble() + c.toDouble() || b.toDouble() >= a.toDouble() + c.toDouble() || c.toDouble() >= a.toDouble() + b.toDouble()))
            return false;

        if(!a1.fromNullString && !b1.fromNullString && !c1.fromNullString && (a1.toDouble() >= b1.toDouble() + c1.toDouble() || b1.toDouble() >= a1.toDouble() + c1.toDouble() || c1.toDouble() >= a1.toDouble() + b1.toDouble()))
            return false;

        return true;
    }



}