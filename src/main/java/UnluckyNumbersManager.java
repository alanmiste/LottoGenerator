import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class UnluckyNumbersManager {
    private Set<Integer> unluckyNumbers;
    private static final String FILE_NAME = "unlucky_numbers.ser";

    public UnluckyNumbersManager() {
        unluckyNumbers = new HashSet<>();
        loadUnluckyNumbers();
    }

    public void addUnluckyNumber(int number) {
        if (isValidNumber(number)) {
            unluckyNumbers.add(number);
            saveUnluckyNumbers(); // Save the unlucky numbers after adding
            System.out.println("Unlucky number " + number + " added successfully.");
        } else {
            System.err.println("Invalid unlucky number. Please enter a number within the valid range.");
        }
    }

    public void removeUnluckyNumber(int number) {
        if (unluckyNumbers.contains(number)) {
            unluckyNumbers.remove(number);
            saveUnluckyNumbers(); // Save the unlucky numbers after removing
            System.out.println("Unlucky number " + number + " removed successfully.");
        } else {
            System.err.println("Unlucky number " + number + " not found.");
        }
    }

    private boolean isValidNumber(int number) {
        return number >= 1 && number <= 49; // Assuming Lotto 6aus49 range
    }

    private void saveUnluckyNumbers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(unluckyNumbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadUnluckyNumbers() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                unluckyNumbers = (Set<Integer>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<Integer> getUnluckyNumbers() {
        return unluckyNumbers;
    }
}
