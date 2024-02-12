import java.util.Arrays;

public class CommandLineParser {
    public String[] parseArguments(String[] args) {

        if (args.length > 0) {
            return Arrays.copyOfRange(args, 1, args.length);
        } else {
            return new String[0];
        }
    }
}
