import java.util.Set;
import java.util.TreeSet;
public class LottoGenerator implements LotteryGenerator {
    @Override
    public String generateNumbers(Set<Integer> excludedNumbers) {
        Logger.logEvent("Starting Lotto numbers generation.");
        Set<Integer> lottoNumbers = new TreeSet<>();

        // Generate Lotto numbers logic while excluding excludedNumbers
        while (lottoNumbers.size() < 6) {
            int randomNumber = (int) (Math.random() * 49) + 1; // Assuming 6aus49 range
            if (!excludedNumbers.contains(randomNumber)) {
                lottoNumbers.add(randomNumber);
            }
        }
        Logger.logEvent("Generated Lotto numbers: "+ lottoNumbers);
        Logger.logEvent("Lotto numbers generation completed.");
        // Convert Set<Integer> to String
        StringBuilder result = new StringBuilder();
        for (int number : lottoNumbers) {
            result.append(number).append(" ");
        }

        return result.toString().trim();
    }
}
