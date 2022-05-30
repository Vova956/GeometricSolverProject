package solver.geom.geometricsolver3;

public class MistakeChecker {
    public static boolean checkForMistakes(String str) {
        int OpenBraces = 0;
        int CloseBraces = 0;
        int squareRoots = 0;

        if (str.length() == 0)
            return true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                OpenBraces++;

            else if (str.charAt(i) == ')')
                CloseBraces++;

            else if (str.charAt(i) == '√')
                squareRoots++;

        }

        if(squareRoots != 0 && str.charAt(0) == '0'){
            return false;
        }

        return OpenBraces == CloseBraces && OpenBraces == squareRoots;
    }

    public static boolean checkForAngleMistakes(String str) {
        int OpenBraces = 0;
        int CloseBraces = 0;
        int squareRoots = 0;

        if (str.length() == 0)
            return true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                OpenBraces++;

            else if (str.charAt(i) == ')')
                CloseBraces++;

            else if (str.charAt(i) == '√')
                squareRoots++;

        }

        return OpenBraces == 0 && squareRoots == 0 && CloseBraces == 0 && (str.charAt(0) != '0');
    }

    public static boolean checkForIntMistakes(String str){
        if(str.length() == 0)
            return true;
        try{
            double a = Double.parseDouble(str);

            if(a != (int)a)
                return false;

        }catch (Exception e){
            return false;
        }
        return true;
    }


}
