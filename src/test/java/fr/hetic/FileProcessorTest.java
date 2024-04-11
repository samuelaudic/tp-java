// src/test/java/fr/hetic/FileProcessorTest.java

package fr.hetic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileProcessorTest {

    @Test
    public void testAddition() {
        double result = OperationFactory.createOperation("+").perform(2, 3);
        assertEquals(5, result);
    }

    // Ajoutez d'autres tests selon vos besoins
}
