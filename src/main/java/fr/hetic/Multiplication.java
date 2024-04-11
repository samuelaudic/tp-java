package fr.hetic;

public class Multiplication implements Operation {

    @Override
    public double perform(double operand1, double operand2) {
        return operand1 * operand2;
    }
}
