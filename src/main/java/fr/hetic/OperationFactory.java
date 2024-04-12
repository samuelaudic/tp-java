package fr.hetic;

public class OperationFactory {

    /**
     * Creates an instance of an arithmetic operation based on the specified operator.
     * @param operator the operator of the operation (among "+", "-", "*")
     * @return an instance of the corresponding operation
     * @throws IllegalArgumentException if the operator is not supported
     */
    public static Operation createOperation(String operator) {
        return switch (operator) {
            case "+" -> new Addition();
            case "-" -> new Subtraction();
            case "*" -> new Multiplication();
            case "/" -> new Division();
            case "^" -> new Exponentiation();
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }
}
