import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class LottoGeneratorTest {

    @Test
    void testGenerateNumbers() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        Set<Integer> excludedNumbers = new HashSet<>();
        String generatedNumbers = lottoGenerator.generateNumbers(excludedNumbers);

        // Split the generated numbers into an array
        String[] numbersArray = generatedNumbers.split("\\s+");

        // Check if the correct number of numbers is generated
        assertEquals(6, numbersArray.length);

        // Check if the generated numbers are within the valid range (1-49)
        for (String num : numbersArray) {
            int number = Integer.parseInt(num);
            assertTrue(number >= 1 && number <= 49);
        }

        // Check if the generated numbers are unique
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (String num : numbersArray) {
            int number = Integer.parseInt(num);
            assertTrue(uniqueNumbers.add(number));
        }

        // Check if the generated numbers do not contain excluded numbers
        for (String num : numbersArray) {
            int number = Integer.parseInt(num);
            assertFalse(excludedNumbers.contains(number));
        }
    }

    @Test
    void testGeneratedNumbersInRange() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        Set<Integer> excludedNumbers = new HashSet<>();
        String generatedNumbers = lottoGenerator.generateNumbers(excludedNumbers);

        // Split the generated numbers into an array
        String[] numbersArray = generatedNumbers.split("\\s+");

        // Check if the generated numbers are within the valid range (1-49)
        for (String num : numbersArray) {
            int number = Integer.parseInt(num);
            assertTrue(number >= 1 && number <= 49);
        }
    }

    @Test
    void testGeneratedNumbersUnique() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        Set<Integer> excludedNumbers = new HashSet<>();
        String generatedNumbers = lottoGenerator.generateNumbers(excludedNumbers);

        // Split the generated numbers into an array
        String[] numbersArray = generatedNumbers.split("\\s+");

        // Check if the generated numbers are unique
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (String num : numbersArray) {
            int number = Integer.parseInt(num);
            assertTrue(uniqueNumbers.add(number));
        }
    }

    @Test
    void testExcludedNumbersNotGenerated() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        Set<Integer> excludedNumbers = new HashSet<>();
        excludedNumbers.add(10);
        excludedNumbers.add(20);
        excludedNumbers.add(30);
        String generatedNumbers = lottoGenerator.generateNumbers(excludedNumbers);

        // Split the generated numbers into an array
        String[] numbersArray = generatedNumbers.split("\\s+");

        // Check if the generated numbers do not contain excluded numbers
        for (String num : numbersArray) {
            int number = Integer.parseInt(num);
            assertFalse(excludedNumbers.contains(number));
        }
    }


}
