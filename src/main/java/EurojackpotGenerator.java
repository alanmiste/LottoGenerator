import java.util.HashSet;
import java.util.Set;

public class EurojackpotGenerator implements LotteryGenerator {
    @Override
    public String generateNumbers(Set<Integer> excludedNumbers) {
        Set<Integer> eurojackpotNumbers = new HashSet<>();

        // Generate Eurojackpot numbers logic while excluding excludedNumbers
        while (eurojackpotNumbers.size() < 5) {
            int randomNumber = (int) (Math.random() * 50) + 1; // Assuming 5 out of 50 range
            if (!excludedNumbers.contains(randomNumber)) {
                eurojackpotNumbers.add(randomNumber);
            }
        }

        while (eurojackpotNumbers.size() < 7) {
            int randomNumber = (int) (Math.random() * 10) + 1; // Assuming 2 out of 10 range
            if (!excludedNumbers.contains(randomNumber)) {
                eurojackpotNumbers.add(randomNumber);
            }
        }

        // Convert Set<Integer> to String
        StringBuilder result = new StringBuilder();
        for (int number : eurojackpotNumbers) {
            result.append(number).append(" ");
        }

        return result.toString().trim();
    }
}
