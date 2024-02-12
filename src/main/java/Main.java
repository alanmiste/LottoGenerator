public class Main {
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
        // Assuming unlucky numbers are retrieved from parsedArgs
        for (String arg : parsedArgs) {
            try {
                int number = Integer.parseInt(arg);
                unluckyNumbersManager.addUnluckyNumber(number);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number: " + arg);
            }
        }

        // Generate numbers while excluding unlucky numbers
        String numbers = generator.generateNumbers(unluckyNumbersManager.getUnluckyNumbers());

        // Print generated numbers
        System.out.println("Generated numbers: " + numbers);
    }
}
