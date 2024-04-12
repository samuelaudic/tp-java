package fr.hetic;

public class Exponentiation implements Operation {

    @Override
    public double perform(double operand1, double operand2) {
        return Math.pow(operand1, operand2);
    }
}
