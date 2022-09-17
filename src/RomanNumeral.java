enum RomanNumeral {
    C(100),
    XC(90),
    L(50),
    XL(40),
    X(10),
    IX(9),
    VIII(8),
    VII(7),
    VI(6),
    V(5),
    IV(4),
    III(3),
    II(2),
    I(1);
    private final int score;

    RomanNumeral(int score) {
        this.score = score;
    }

    static String intToRoman(int num) {
        StringBuilder romanVal = new StringBuilder();

        for (RomanNumeral rn : RomanNumeral.values()) {

            while (num >= rn.getScore()) {
                romanVal.append(rn.toString());
                num = num - rn.getScore();
            }
        }
        return romanVal.toString();
    }

    int getScore() {
        return score;
    }
}