import java.io.*;
import java.util.*;

public class UnluckyNumbersManager {
    private static final String UNLUCKY_NUMBERS_FILE = "unlucky_numbers.txt";
    private Set<Integer> unluckyNumbers;

    public UnluckyNumbersManager() {
        unluckyNumbers = new HashSet<>();
    }

    public void loadUnluckyNumbersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(UNLUCKY_NUMBERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                unluckyNumbers.add(number);
            }
        } catch (IOException e) {
            System.err.println("Error loading unlucky numbers: " + e.getMessage());
        }
    }

    public void saveUnluckyNumbersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(UNLUCKY_NUMBERS_FILE))) {
            for (int number : unluckyNumbers) {
                writer.println(number);
            }
        } catch (IOException e) {
            System.err.println("Error saving unlucky numbers: " + e.getMessage());
        }
    }

    public void addUnluckyNumber(int number) {
        if (isValidNumber(number)) {
            unluckyNumbers.add(number);
            System.out.println("Unlucky number " + number + " added successfully.");
        } else {
            System.err.println("Invalid unlucky number. Please enter a number within the valid range.");
        }
    }

    public void removeUnluckyNumber(int number) {
        if (unluckyNumbers.contains(number)) {
            unluckyNumbers.remove(number);
            System.out.println("Unlucky number " + number + " removed successfully.");
        } else {
            System.err.println("Unlucky number " + number + " not found.");
        }
    }

    private boolean isValidNumber(int number) {
        return number >= 1 && number <= 49; // Assuming Lotto 6aus49 range
    }

    public Set<Integer> getUnluckyNumbers() {
        return unluckyNumbers;
    }
}
