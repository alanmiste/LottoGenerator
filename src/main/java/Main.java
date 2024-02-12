public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser();
        String[] parsedArgs = parser.parseArguments(args);

        LotteryGenerator generator;
        if (parsedArgs.length > 0 && parsedArgs[0].equalsIgnoreCase("Eurojackpot")) {
            generator = new EurojackpotGenerator();
        } else {
            generator = new LottoGenerator();
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
