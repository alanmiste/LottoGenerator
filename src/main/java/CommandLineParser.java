import java.util.ArrayList;
public class CommandLineParser {
    public String[] parseArguments(String[] args) {
        ArrayList<String> argumentsList = new ArrayList<>();

        // Add all command line arguments to ArrayList
        for (String arg : args) {
            argumentsList.add(arg);
        }

        // Convert ArrayList to String array
        String[] parsedArgs = new String[argumentsList.size()];
        parsedArgs = argumentsList.toArray(parsedArgs);

        // Log the parsed arguments
        Logger.logEvent("Parsed command line arguments: "+ argumentsList);

        return parsedArgs;
    }
}
