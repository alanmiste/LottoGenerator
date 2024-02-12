import java.util.HashSet;
import java.util.Set;

public class UnluckyNumbersManager {
    private Set<Integer> unluckyNumbers;

    public UnluckyNumbersManager() {
        unluckyNumbers = new HashSet<>();
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
