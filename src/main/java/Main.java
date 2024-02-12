import java.io.*;
import java.util.*;

public class Main {
    private static final String UNLUCKY_NUMBERS_FILE = "unlucky_numbers.txt";

    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser();
        String[] parsedArgs = parser.parseArguments(args);

        Set<Integer> unluckyNumbers = loadUnluckyNumbersFromFile();

        String gameType = "lotto"; // Default game type

        // Check if the first argument is a valid game type
        if (parsedArgs.length > 0) {
            String firstArg = parsedArgs[0].toLowerCase();
            if (firstArg.equals("lotto") || firstArg.equals("eurojackpot")) {
                gameType = firstArg;
                // Remove the first argument from parsedArgs
                parsedArgs = Arrays.copyOfRange(parsedArgs, 1, parsedArgs.length);
            } else {
                System.err.println("Invalid game type. Valid game types are: lotto, eurojackpot");
                return;
            }
        }

        // Check for additional arguments after game type
        if (parsedArgs.length > 0) {
            for (int i = 0; i < parsedArgs.length; i++) {
                String arg = parsedArgs[i];
                if (arg.equals("-add")) {
                    // Add unlucky numbers
                    if (i + 1 < parsedArgs.length) {
                        String[] numbersToAdd = parsedArgs[i + 1].split("\\s+");
                        for (String num : numbersToAdd) {
                            try {
                                int number = Integer.parseInt(num);
                                unluckyNumbers.add(number);
                            } catch (NumberFormatException e) {
                                System.err.println("Invalid number: " + num);
                            }
                        }
                    }
                    saveUnluckyNumbersToFile(unluckyNumbers);
                    i++; // Skip the next argument which contains the numbers to add
                } else if (arg.equals("-remove")) {
                    // Remove unlucky numbers
                    if (i + 1 < parsedArgs.length) {
                        String[] numbersToRemove = parsedArgs[i + 1].split("\\s+");
                        for (String num : numbersToRemove) {
                            try {
                                int number = Integer.parseInt(num);
                                unluckyNumbers.remove(number);
                            } catch (NumberFormatException e) {
                                System.err.println("Invalid number: " + num);
                            }
                        }
                    }
                    saveUnluckyNumbersToFile(unluckyNumbers);
                    i++; // Skip the next argument which contains the numbers to remove
                } else if (arg.equals("-display")) {
                    // Display unlucky numbers
                    System.out.println("Unlucky numbers:");
                    for (int number : unluckyNumbers) {
                        System.out.print(number + " ");
                    }
                    System.out.println();
                    return; // Exit after displaying unlucky numbers
                }
            }
        }

        // Create UnluckyNumbersManager instance
        UnluckyNumbersManager unluckyNumbersManager = new UnluckyNumbersManager();

        // Add unlucky numbers to manager
        for (int number : unluckyNumbers) {
            unluckyNumbersManager.addUnluckyNumber(number);
        }

        // Select the appropriate generator based on the game type
        LotteryGenerator generator;
        if (gameType.equals("eurojackpot")) {
            generator = new EurojackpotGenerator();
        } else {
            generator = new LottoGenerator();
        }

        // Generate numbers while excluding unlucky numbers
        String numbers = generator.generateNumbers(unluckyNumbersManager.getUnluckyNumbers());

        // Print generated numbers
        System.out.println("Generated numbers: " + numbers);
    }

    private static Set<Integer> loadUnluckyNumbersFromFile() {
        Set<Integer> unluckyNumbers = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(UNLUCKY_NUMBERS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+");
                for (String num : numbers) {
                    try {
                        int number = Integer.parseInt(num);
                        unluckyNumbers.add(number);
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, ignore
        }
        return unluckyNumbers;
    }

    private static void saveUnluckyNumbersToFile(Set<Integer> unluckyNumbers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(UNLUCKY_NUMBERS_FILE))) {
            for (int number : unluckyNumbers) {
                writer.print(number + " ");
            }
        } catch (IOException e) {
            System.err.println("Error saving unlucky numbers to file: " + e.getMessage());
        }
    }
}
