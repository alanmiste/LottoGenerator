import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UnluckyNumbersManagerTest {

    @Test
    void testLoadUnluckyNumbersFromFile() throws IOException {
        // Create a temporary file with some unlucky numbers
        File file = File.createTempFile("unlucky_numbers", ".txt");
        Files.write(file.toPath(), "10\n20\n30".getBytes());

        UnluckyNumbersManager manager = new UnluckyNumbersManager();
        manager.loadUnluckyNumbersFromFile();

        Set<Integer> expectedNumbers = new HashSet<>();
        expectedNumbers.add(10);
        expectedNumbers.add(20);
        expectedNumbers.add(30);

        assertEquals(expectedNumbers, manager.getUnluckyNumbers());

        // Clean up temporary file
        file.delete();
    }

    @Test
    void testSaveUnluckyNumbersToFile() {
        // Arrange
        UnluckyNumbersManager manager = new UnluckyNumbersManager();
        manager.addUnluckyNumber(10);
        manager.addUnluckyNumber(20);
        manager.addUnluckyNumber(30);

        // Act
        manager.saveUnluckyNumbersToFile();

        // Assert
        Set<Integer> loadedNumbers = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(UnluckyNumbersManager.UNLUCKY_NUMBERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                loadedNumbers.add(number);
            }
        } catch (IOException e) {
            fail("Error reading from the file: " + e.getMessage());
        }

        assertTrue(loadedNumbers.contains(10));
        assertTrue(loadedNumbers.contains(20));
        assertTrue(loadedNumbers.contains(30));
    }

    @Test
    void testAddUnluckyNumber() {
        UnluckyNumbersManager manager = new UnluckyNumbersManager();
        manager.addUnluckyNumber(10);
        manager.addUnluckyNumber(20);
        manager.addUnluckyNumber(30);

        Set<Integer> expectedNumbers = new HashSet<>();
        expectedNumbers.add(10);
        expectedNumbers.add(20);
        expectedNumbers.add(30);

        assertEquals(expectedNumbers, manager.getUnluckyNumbers());
    }

    @Test
    void testAddUnluckyNumber_InvalidNumber() {
        UnluckyNumbersManager manager = new UnluckyNumbersManager();

        int invalidNumber = 50;

        manager.addUnluckyNumber(invalidNumber);

        assertFalse(manager.getUnluckyNumbers().contains(invalidNumber));
    }
    @Test
    void testRemoveUnluckyNumber() {
        UnluckyNumbersManager manager = new UnluckyNumbersManager();
        manager.addUnluckyNumber(10);
        manager.addUnluckyNumber(20);
        manager.addUnluckyNumber(30);

        manager.removeUnluckyNumber(20);

        Set<Integer> expectedNumbers = new HashSet<>();
        expectedNumbers.add(10);
        expectedNumbers.add(30);

        assertEquals(expectedNumbers, manager.getUnluckyNumbers());
    }

    @Test
    void testRemoveNonExistingUnluckyNumber() {
        // Arrange
        UnluckyNumbersManager manager = new UnluckyNumbersManager();
        manager.addUnluckyNumber(10);
        manager.addUnluckyNumber(20);

        // Act
        manager.removeUnluckyNumber(30);

        // Assert
        assertFalse(manager.getUnluckyNumbers().contains(30));
    }

    @Test
    void testIsValidNumberValid() {
        // Arrange
        UnluckyNumbersManager manager = new UnluckyNumbersManager();

        // Act and Assert
        assertTrue(manager.isValidNumber(10));
        assertTrue(manager.isValidNumber(49));
    }

    @Test
    void testIsValidNumberInvalid() {
        // Arrange
        UnluckyNumbersManager manager = new UnluckyNumbersManager();

        // Act and Assert
        assertFalse(manager.isValidNumber(0));
        assertFalse(manager.isValidNumber(50));
    }

    @Test
    void testGetUnluckyNumbers() {
        // Arrange
        UnluckyNumbersManager manager = new UnluckyNumbersManager();
        manager.addUnluckyNumber(10);
        manager.addUnluckyNumber(20);
        manager.addUnluckyNumber(30);

        // Act
        Set<Integer> unluckyNumbers = manager.getUnluckyNumbers();

        // Assert
        assertTrue(unluckyNumbers.contains(10));
        assertTrue(unluckyNumbers.contains(20));
        assertTrue(unluckyNumbers.contains(30));
    }
}
