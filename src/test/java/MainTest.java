import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testAddUnluckyNumbers() {
        UnluckyNumbersManager unluckyNumbersManager = new UnluckyNumbersManager();
        Main.addUnluckyNumbers(new String[]{"-add", "10", "20", "30"}, unluckyNumbersManager);

        assertTrue(unluckyNumbersManager.getUnluckyNumbers().contains(10));
        assertTrue(unluckyNumbersManager.getUnluckyNumbers().contains(20));
        assertTrue(unluckyNumbersManager.getUnluckyNumbers().contains(30));
    }

    @Test
    void testAddInvalidUnluckyNumbers() {
        UnluckyNumbersManager unluckyNumbersManager = new UnluckyNumbersManager();

        // Attempt to add invalid unlucky numbers
        Main.addUnluckyNumbers(new String[]{"-add", "abc", "-5", "1000", "3.14", "20.5"}, unluckyNumbersManager);

        // Check that the invalid numbers were not added
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains("abc"));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(-5));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(1000));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(3.14));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(20.5));
    }

    @Test
    void testRemoveUnluckyNumbers() {
        UnluckyNumbersManager unluckyNumbersManager = new UnluckyNumbersManager();
        unluckyNumbersManager.addUnluckyNumber(10);
        unluckyNumbersManager.addUnluckyNumber(20);
        unluckyNumbersManager.addUnluckyNumber(30);

        Main.removeUnluckyNumbers(new String[]{"-remove", "10", "20"}, unluckyNumbersManager);

        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(10));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(20));
        assertTrue(unluckyNumbersManager.getUnluckyNumbers().contains(30));
    }

    @Test
    void testRemoveInvalidUnluckyNumbers() {
        UnluckyNumbersManager unluckyNumbersManager = new UnluckyNumbersManager();
        unluckyNumbersManager.addUnluckyNumber(10);
        unluckyNumbersManager.addUnluckyNumber(20);

        // Attempt to remove invalid unlucky numbers
        Main.removeUnluckyNumbers(new String[]{"-remove", "abc", "-5", "1000", "3.14", "20.5"}, unluckyNumbersManager);

        // Check that the existing unlucky numbers were not removed
        assertTrue(unluckyNumbersManager.getUnluckyNumbers().contains(10));
        assertTrue(unluckyNumbersManager.getUnluckyNumbers().contains(20));

        // Check that the invalid numbers were not removed
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains("abc"));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(-5));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(1000));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(3.14));
        assertFalse(unluckyNumbersManager.getUnluckyNumbers().contains(20.5));
    }

    @Test
    void testDisplayUnluckyNumbers() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Prepare test data
        Set<Integer> unluckyNumbers = new HashSet<>();
        unluckyNumbers.add(10);
        unluckyNumbers.add(20);
        unluckyNumbers.add(30);

        // Call the method to display unlucky numbers
        Main.displayUnluckyNumbers(unluckyNumbers);

        // Get the printed output
        String printedOutput = outputStream.toString().trim(); // Remove trailing newline

        // Define the expected output
        String expectedOutput = "Unlucky numbers:20 10 30";

        // Replace line separators with empty strings
        expectedOutput = expectedOutput.replaceAll("\\r\\n|\\r|\\n", "");

        // Compare the actual and expected output
        assertEquals(expectedOutput, printedOutput.replaceAll("\\r\\n|\\r|\\n", ""));
    }


    @Test
    void testMainWithInvalidGameType() {
        // Redirect System.err to capture printed error output
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        // Define command line arguments with invalid game type
        String[] args = {"invalid"};

        // Call the main method
        Main.main(args);

        // Get the printed error output
        String printedErrorOutput = errorStream.toString().trim(); // Remove trailing newline

        // Define the expected error output
        String expectedErrorOutput = "Invalid game type. Valid game types are: lotto, eurojackpot";

        // Compare the actual and expected error output
        assertTrue(printedErrorOutput.contains(expectedErrorOutput));
    }


    @Test
    void testMainWithDefaultGameType() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the main method with default game type
        Main.main(new String[]{});

        // Get the printed output
        String printedOutput = outputStream.toString().trim(); // Remove trailing newline

        // Check if the expected message is present in the printed output
        assertTrue(printedOutput.contains("Generated numbers:"));
    }

    @Test
    void testMainWithEurojackpot() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the main method with Eurojackpot game type
        Main.main(new String[]{"eurojackpot"});

        // Get the printed output
        String printedOutput = outputStream.toString().trim(); // Remove trailing newline

        // Check if the expected message is present in the printed output
        assertTrue(printedOutput.contains("Generated numbers:"));
    }

}
