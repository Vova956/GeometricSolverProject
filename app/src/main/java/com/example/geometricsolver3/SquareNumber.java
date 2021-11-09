package com.example.geometricsolver3;
import java.math.BigDecimal;

public class SquareNumber {
    private double intNumber;
    private double squareNumber;
    public boolean fromNullString = false;

    private final double[] squares = {
            4, 9,
            16, 25, 36, 49,
            64, 81, 100, 121,
            144, 169, 196, 225,
            256, 289, 324, 361,
            400, 441, 484, 529,
            576, 625, 676, 729,
            784, 841, 900, 961,
            1024, 1089, 1156, 1225,
            1296, 1369, 1444, 1521,
            1600, 1681, 1764, 1849,
            1936, 2025, 2116, 2209,
            2304, 2401, 2500, 2601,
            2704, 2809, 2916, 3025,
            3136, 3249, 3364, 3481,
            3600, 3721, 3844, 3969,
            4096, 4225, 4356, 4489,
            4624, 4761, 4900, 5041,
            5184, 5329, 5476, 5625,
            5776, 5929, 6084, 6241,
            6400, 6561, 6724, 6889,
            7056, 7225, 7396, 7569,
            7744, 7921, 8100, 8281,
            8464, 8649, 8836, 9025,
            9216, 9409, 9604, 9801,
            10000, 10201, 10404, 10609,
            10816, 11025, 11236, 11449,
            11664, 11881, 12100, 12321,
            12544, 12769, 12996, 13225,
            13456, 13689, 13924, 14161,
            14400, 14641, 14884, 15129,
            15376, 15625, 15876, 16129,
            16384, 16641, 16900, 17161,
            17424, 17689, 17956, 18225,
            18496, 18769, 19044, 19321,
            19600, 19881, 20164, 20449,
            20736, 21025, 21316, 21609,
            21904, 22201, 22500, 22801,
            23104, 23409, 23716, 24025,
            24336, 24649, 24964, 25281,
            25600, 25921, 26244, 26569,
            26896, 27225, 27556, 27889,
            28224, 28561, 28900, 29241,
            29584, 29929, 30276, 30625,
            30976, 31329, 31684, 32041,
            32400, 32761, 33124, 33489,
            33856, 34225, 34596, 34969,
            35344, 35721, 36100, 36481,
            36864, 37249, 37636, 38025,
            38416, 38809, 39204, 39601,
            40000, 40401, 40804, 41209,
            41616, 42025, 42436, 42849,
            43264, 43681, 44100, 44521,
            44944, 45369, 45796, 46225,
            46656, 47089, 47524, 47961,
            48400, 48841, 49284, 49729,
            50176, 50625, 51076, 51529,
            51984, 52441, 52900, 53361,
            53824, 54289, 54756, 55225,
            55696, 56169, 56644, 57121,
            57600, 58081, 58564, 59049,
            59536, 60025, 60516, 61009,
            61504, 62001, 62500, 63001,
            63504, 64009, 64516, 65025
    };

    public SquareNumber() {
        intNumber = 0;
        squareNumber = 0;
    }

    public SquareNumber(String str) {
        if (str.length() == 0) {
            intNumber = 1;
            squareNumber = 1;
            fromNullString = true;
        } else if (str.charAt(0) == '√') {
            intNumber = 1;
            squareNumber = Double.parseDouble(str.substring(2, str.length() - 1));

        } else if (!str.contains("√")) {
            intNumber = Double.parseDouble(str);
            squareNumber = 1;
        } else {
            String[] split = str.split("√", 2);
            intNumber = Double.parseDouble(split[0]);

            String buff = split[1];
            squareNumber = Double.parseDouble(buff.substring(1, buff.length() - 1));
        }

        if (intNumber == 0 || squareNumber == 0 || squareNumber < 0) {
            intNumber = 0;
            squareNumber = 0;
        } else {
            SquareNumber a = Sqrt(squareNumber);

            intNumber *= a.getIntNumber();
            squareNumber = a.getSquareNumber();
        }
    }

    public SquareNumber(double intNumber, double squareNumber1) {
        this.intNumber = intNumber;
        this.squareNumber = ((int) (squareNumber1 * 10000.0)) / 10000.0;


    }

    private SquareNumber Sqrt(double x) {
        if (x == (int) x) {
            return SqrtInt((int) x);
        } else {
            return SqrtDouble(x);
        }
    }

    private SquareNumber SqrtDouble(double x) {
        int amountAfterDot = BigDecimal.valueOf(x).scale();
        int result = (int) (x * Math.pow(10, amountAfterDot));


        SquareNumber squareNumber = SqrtInt(result);


        if (amountAfterDot % 2 == 0) {
            return new SquareNumber((int) (squareNumber.intNumber * Math.pow(0.1, amountAfterDot / 2) * 10000) / 10000.0,
                    (int) (squareNumber.squareNumber * 10000) / 10000.0);
        } else {
            return new SquareNumber(((int) (squareNumber.intNumber * Math.pow(0.1, (amountAfterDot - 1) / 2) * 10000) / 10000.0),
                    ((int) (squareNumber.squareNumber * 0.1 * 10000)) / 10000.0);
        }

    }

    private SquareNumber SqrtInt(int x) {
        int answer = 1;

        for (int i = 0; i < squares.length; i++) {

            if (x % squares[i] == 0) {

                answer *= Math.sqrt(squares[i]);
                x /= squares[i];
                i = -1;

            }
        }

        return new SquareNumber(answer, x);
    }

    public double getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(double intNumber) {
        this.intNumber = intNumber;
    }

    public double getSquareNumber() {
        return squareNumber;
    }

    public void setSquareNumber(double squareNumber) {
        this.squareNumber = squareNumber;
    }

    private void appendTo(double d, StringBuilder stringBuilder) {
        if (d == (int) d)
            stringBuilder.append((int) d);
        else
            stringBuilder.append(d);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (intNumber != 1)
            appendTo(intNumber, stringBuilder);

        if (intNumber == 1 && squareNumber == 1)
            appendTo(intNumber, stringBuilder);


        if (squareNumber != 1) {
            stringBuilder.append("√");
            appendTo(squareNumber, stringBuilder);
        }

        return stringBuilder.toString();
    }

    public void multiply(SquareNumber squareNumber) {
        this.intNumber *= squareNumber.intNumber;
        this.squareNumber *= squareNumber.squareNumber;


        SquareNumber s = Sqrt(this.squareNumber);
        this.intNumber *= s.intNumber;
        this.squareNumber = s.squareNumber;


    }

    public void multiply(int a) {
        this.intNumber *= a;
    }

    public SquareNumber getMultiply(SquareNumber squareNumber) {
        SquareNumber squareNumber1 = new SquareNumber(this.intNumber * squareNumber.intNumber,
                this.squareNumber * squareNumber.squareNumber);

        SquareNumber a = Sqrt(squareNumber1.squareNumber);

        squareNumber1.intNumber *= a.getIntNumber();
        squareNumber1.squareNumber = a.getSquareNumber();

        return squareNumber1;
    }

    public SquareNumber getMultiply(int a) {
        return new SquareNumber(intNumber * a, squareNumber);
    }

    public double toDouble() {
        return intNumber * Math.sqrt(squareNumber);
    }

    public boolean equals(SquareNumber squareNumber) {
        return this.intNumber == squareNumber.intNumber && this.squareNumber == squareNumber.squareNumber;
    }

    public SquareNumber getDivide(SquareNumber squareNumber1) {
        SquareNumber buff = new SquareNumber(this.intNumber / squareNumber1.intNumber,
                this.squareNumber / squareNumber1.squareNumber);

        return buff;
    }

    public SquareNumber getDivide(double a) {
        return new SquareNumber(intNumber / a, squareNumber);
    }

    public void divide(double a) {
        intNumber /= a;
    }

    public void setFromNullString(boolean fromNullString) {
        this.fromNullString = fromNullString;
    }

    public SquareNumber getSquareOf() {
        return getMultiply(this);
    }

    public void addToInt(double a) {
        intNumber += a;
    }

    public String getAbsStr() {
        if (intNumber > 0)
            return toString();
        else {
            intNumber *= -1;
            String buff = toString();
            intNumber *= -1;
            return buff;
        }

    }

}
