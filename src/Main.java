import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); // создаем поток
        System.out.println(calc(bufferedReader.readLine())); // вызов метода + вывод
        bufferedReader.close(); // закрываем поток
    }


    public static String calc(String input) throws Exception {
        try {
            String result = String.valueOf(0);
            if (!input.isEmpty()) {
                input = input.trim();

                String[] operands = input.split(" ", 3);
                if (operands[2].length() > 2) {
                    throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                }
                if (operands[0].chars().allMatch(Character::isDigit) && operands[2].chars().allMatch(Character::isDigit) &&      // чек на числа
                        ((operands[1].contains("*")) || (operands[1].contains("+")) || (operands[1].contains("/")) || operands[1].contains("-"))) {
                    return calcArabian(operands);
                } else if (operands[0].chars().allMatch(Character::isLetter) && operands[2].chars().allMatch(Character::isLetter) && // чек на римские
                        ((operands[1].contains("*")) || (operands[1].contains("+")) || (operands[1].contains("/")) || operands[1].contains("-"))) {
                    return calcRoman(operands);
                } else if (operands[0].chars().allMatch(Character::isLetter) && operands[2].chars().allMatch(Character::isDigit) || operands[0].chars().allMatch(Character::isDigit) && operands[2].chars().allMatch(Character::isLetter)) {
                    throw new Exception("используются одновременно разные системы счисления");
                }
                return result;
            } else throw new Exception("Строка не содержит данных");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("строка не является математической операцией");
        }
    }

    public static String calcArabian(String[] operands) throws Exception {
        String result = String.valueOf(0);

        int operandOne = Integer.parseInt(String.valueOf(operands[0]));
        int operandTwo = Integer.parseInt(String.valueOf(operands[2]));

        if ((operandOne > 10) || (operandOne < 1) || (operandTwo > 10) || (operandTwo < 1)) {
            throw new Exception("Число не может быть меньше 1 или больше 10");
        }
        String operator = operands[1];
        switch (operator) {
            case "/" -> result = String.valueOf(operandOne / operandTwo);
            case "*" -> result = String.valueOf(operandOne * operandTwo);
            case "+" -> result = String.valueOf(operandOne + operandTwo);
            case "-" -> result = String.valueOf(operandOne - operandTwo);
        }
        return result;

    }

    public static String calcRoman(String[] operands) throws Exception {
        String result = String.valueOf(0);
        int operandOne;
        int operandTwo;

        try {
            operandOne = RomanNumeral.valueOf(operands[0]).getScore();
            operandTwo = RomanNumeral.valueOf(operands[2]).getScore();
        } catch (IllegalArgumentException e) {
            throw new Exception("Число не может быть меньше 1 или больше 10");
        }

        if ((operandOne > 10) || (operandOne < 1) || (operandTwo > 10) || (operandTwo < 1)) {
            throw new Exception("Число не может быть меньше 1 или больше 10");
        }
        String operator = operands[1];

        switch (operator) {
            case "/" -> result = String.valueOf(operandOne / operandTwo);
            case "*" -> result = String.valueOf(operandOne * operandTwo);
            case "+" -> result = String.valueOf(operandOne + operandTwo);
            case "-" -> result = String.valueOf(operandOne - operandTwo);
        }

        if (Integer.parseInt(result) < 0) {
            throw new Exception(" в римской системе нет отрицательных чисел");
        }

        result = RomanNumeral.intToRoman(Integer.parseInt(result));
        return result;

    }

}