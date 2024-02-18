import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class EurojackpotGeneratorTest {

    @Test
    void testGenerateNumbersExcludedNumbers() {
        EurojackpotGenerator eurojackpotGenerator = new EurojackpotGenerator();
        Set<Integer> excludedNumbers = new HashSet<>();
        excludedNumbers.add(10);
        excludedNumbers.add(20);
        excludedNumbers.add(30);
        String result = eurojackpotGenerator.generateNumbers(excludedNumbers);

        // Assert that generated numbers do not contain excluded numbers
        for (Integer excludedNumber : excludedNumbers) {
            assertFalse(result.contains(excludedNumber.toString()));
        }
    }

    @Test
    void testGenerateNumbersCorrectFormat() {
        EurojackpotGenerator eurojackpotGenerator = new EurojackpotGenerator();
        String result = eurojackpotGenerator.generateNumbers(new HashSet<>());

        // Assert that generated numbers have correct format
        String[] numbers = result.split(" ");
        assertEquals(7, numbers.length); // Eurojackpot has 7 numbers
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            assertTrue(num >= 1 && num <= 50 || num >= 1 && num <= 10); // Check ranges
        }
    }

    @Test
    void testGenerateNumbersNoExclusions() {
        EurojackpotGenerator generator = new EurojackpotGenerator();
        String numbers = generator.generateNumbers(new HashSet<>());
        String[] numbersArray = numbers.split(" ");

        // Test for correct number of generated numbers
        assertEquals(7, numbersArray.length);

        // Test for numbers within the correct range
        for (String numStr : numbersArray) {
            int num = Integer.parseInt(numStr);
            assertTrue(num >= 1 && num <= 50);
        }

        // Test for no duplicate numbers
        Set<String> uniqueNumbers = new HashSet<>();
        for (String numStr : numbersArray) {
            assertTrue(uniqueNumbers.add(numStr));
        }
    }

    @Test
    void testGenerateNumbersWithExclusions() {
        EurojackpotGenerator generator = new EurojackpotGenerator();
        Set<Integer> exclusions = new HashSet<>();
        exclusions.add(5);
        exclusions.add(10);
        exclusions.add(15);

        String numbers = generator.generateNumbers(exclusions);
        String[] numbersArray = numbers.split(" ");

        // Test for correct number of generated numbers
        assertEquals(7, numbersArray.length);

        // Test for numbers within the correct range
        for (String numStr : numbersArray) {
            int num = Integer.parseInt(numStr);
            assertTrue(num >= 1 && num <= 50);
        }

        // Test for no duplicate numbers
        Set<String> uniqueNumbers = new HashSet<>();
        for (String numStr : numbersArray) {
            assertTrue(uniqueNumbers.add(numStr));
        }

        // Test for excluded numbers not present in generated numbers
        for (String numStr : numbersArray) {
            int num = Integer.parseInt(numStr);
            assertFalse(exclusions.contains(num));
        }
    }

    @Test
    void testGenerateNumbersCorrectSize() {
        EurojackpotGenerator generator = new EurojackpotGenerator();
        Set<Integer> exclusions = new HashSet<>();
        String numbers = generator.generateNumbers(exclusions);
        String[] numbersArray = numbers.split(" ");
        assertEquals(7, numbersArray.length);
    }

    @Test
    void testGenerateNumbersOrdered() {
        EurojackpotGenerator generator = new EurojackpotGenerator();
        Set<Integer> exclusions = new HashSet<>();
        String numbers = generator.generateNumbers(exclusions);
        String[] numbersArray = numbers.split(" ");

        // Test for correct order of generated numbers
        int prevNum = Integer.parseInt(numbersArray[0]);
        for (int i = 1; i < numbersArray.length; i++) {
            int currNum = Integer.parseInt(numbersArray[i]);
            assertTrue(prevNum < currNum);
            prevNum = currNum;
        }
    }

    @Test
    void testGenerateNumbersWithInvalidExclusions() {
        EurojackpotGenerator generator = new EurojackpotGenerator();
        Set<Integer> exclusions = new HashSet<>();
        exclusions.add(-1);
        exclusions.add(0);
        exclusions.add(51);
        exclusions.add(1000);

        String numbers = generator.generateNumbers(exclusions);
        String[] numbersArray = numbers.split(" ");

        // Test for correct number of generated numbers
        assertEquals(7, numbersArray.length);

        // Test for numbers within the correct range
        for (String numStr : numbersArray) {
            int num = Integer.parseInt(numStr);
            assertTrue(num >= 1 && num <= 50);
        }

        // Test for no duplicate numbers
        Set<String> uniqueNumbers = new HashSet<>();
        for (String numStr : numbersArray) {
            assertTrue(uniqueNumbers.add(numStr));
        }

        // Test for excluded numbers not present in generated numbers
        for (String numStr : numbersArray) {
            int num = Integer.parseInt(numStr);
            assertFalse(exclusions.contains(num));
        }
    }
}
