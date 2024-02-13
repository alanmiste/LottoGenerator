import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser();
        String[] parsedArgs = parser.parseArguments(args);

        LotteryGenerator generator;

        if (parsedArgs.length > 0) {
            String gameType = parsedArgs[0].toLowerCase();
            if (gameType.equals("eurojackpot")) {
                generator = new EurojackpotGenerator();
            } else if (gameType.equals("lotto")) {
                generator = new LottoGenerator();
            } else {
                System.err.println("Invalid game type. Valid game types are: lotto, eurojackpot");
                return;
            }
        } else {
            generator = new LottoGenerator(); // Default game type
        }

        UnluckyNumbersManager unluckyNumbersManager = new UnluckyNumbersManager();
        unluckyNumbersManager.loadUnluckyNumbersFromFile(); // Load unlucky numbers from file

        // Process add, remove, or display commands
        for (String arg : parsedArgs) {
            if (arg.equals("-add")) {
                addUnluckyNumbers(parsedArgs, unluckyNumbersManager);
            } else if (arg.equals("-remove")) {
                removeUnluckyNumbers(parsedArgs, unluckyNumbersManager);
            } else if (arg.equals("-display")) {
                displayUnluckyNumbers(unluckyNumbersManager.getUnluckyNumbers());
            }
        }

        // Generate numbers while excluding unlucky numbers
        generateNumbers(generator, unluckyNumbersManager.getUnluckyNumbers());

        // Prompt user for generating additional sets of numbers
        while (true) {
            System.out.println("Do you want to generate another set of numbers? (yes|y / no|n)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")|| input.equalsIgnoreCase("y")) {
                generateNumbers(generator, unluckyNumbersManager.getUnluckyNumbers());
            } else if (input.equalsIgnoreCase("no")||input.equalsIgnoreCase("n")) {
                System.out.println("Thank you for using our lottery number generator.");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes / y' or 'no / n'.");
            }
        }
    }

    private static void addUnluckyNumbers(String[] args, UnluckyNumbersManager unluckyNumbersManager) {
        // Add unlucky numbers from command line arguments
        for (int i = 1; i < args.length; i++) {
            try {
                int number = Integer.parseInt(args[i]);
                unluckyNumbersManager.addUnluckyNumber(number);
            } catch (NumberFormatException e) {
                if(args[i].length()<2) {
                    System.err.println("Invalid number: " + args[i]);
                }
            }
        }
        unluckyNumbersManager.saveUnluckyNumbersToFile(); // Save unlucky numbers to file
    }

    private static void removeUnluckyNumbers(String[] args, UnluckyNumbersManager unluckyNumbersManager) {
        // Remove unlucky numbers from command line arguments
        for (int i = 1; i < args.length; i++) {
            try {
                int number = Integer.parseInt(args[i]);
                unluckyNumbersManager.removeUnluckyNumber(number);
            } catch (NumberFormatException e) {
                if(args[i].length()<2) {
                    System.err.println("Invalid number: " + args[i]);
                }
            }
        }
        unluckyNumbersManager.saveUnluckyNumbersToFile(); // Save unlucky numbers to file
    }

    private static void displayUnluckyNumbers(Set<Integer> unluckyNumbers) {
        // Display unlucky numbers
        System.out.println("Unlucky numbers:");
        for (int number : unluckyNumbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    private static void generateNumbers(LotteryGenerator generator, Set<Integer> excludedNumbers) {
        String numbers = generator.generateNumbers(excludedNumbers);
        System.out.println("Generated numbers: " + numbers);
    }
}
