package fr.hetic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FileProcessorTest {

    @Test
    public void testAddition() {
        double result = OperationFactory.createOperation("+").perform(2, 3);
        assertEquals(5.0, result, 0); // Use double for the expected value
    }

    @Test
    public void testSubtraction() {
        double result = OperationFactory.createOperation("-").perform(3, 2);
        assertEquals(1.0, result, 0); // Use double for the expected value
    }
}
