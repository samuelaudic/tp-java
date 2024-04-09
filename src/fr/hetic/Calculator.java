package fr.hetic;

public class Calculator {
    public static void main(String[] args) {
        try {
            if (args.length < 3) {
                System.out.println("Usage: Calculator <value1> <value2> <operator>");
                return;
            }

            int result = 0;
            int value1 = Integer.parseInt(args[0]);
            int value2 = Integer.parseInt(args[1]);
            String operator = args[2];

            //System.out.println(value1 + operator + value2);

            if (!isValidOperator(operator)) {
                System.out.println("Invalid operator: " + operator);
                return;
            }

            result = switch (operator) {
                case "+" -> value1 + value2;
                case "-" -> value1 - value2;
                case "*" -> value1 * value2;
                default -> {
                    System.out.println("Invalid operator: " + operator);
                    yield 0;
                }
            };

            System.out.println("Result: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format. Please enter integer values for <value1> and <value2>.");
        }
    }

    private static boolean isValidOperator(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("*");
    }
}
