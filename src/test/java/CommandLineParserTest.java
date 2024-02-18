import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandLineParserTest {

    @Test
    void testParseArguments_NormalCase() {
        CommandLineParser parser = new CommandLineParser();
        String[] args = {"-add", "10", "20", "-remove", "30"};
        String[] expected = {"-add", "10", "20", "-remove", "30"};

        String[] parsedArgs = parser.parseArguments(args);

        assertArrayEquals(expected, parsedArgs);
    }

    @Test
    void testParseArgumentsEmptyArray() {
        CommandLineParser parser = new CommandLineParser();
        String[] args = {};
        String[] result = parser.parseArguments(args);
        assertArrayEquals(new String[]{}, result);
    }

    @Test
    void testParseArgumentsSingleArgument() {
        CommandLineParser parser = new CommandLineParser();
        String[] args = {"-add"};
        String[] result = parser.parseArguments(args);
        assertArrayEquals(new String[]{"-add"}, result);
    }

    @Test
    void testParseArgumentsWithCommas() {
        CommandLineParser parser = new CommandLineParser();
        String[] args = {"-add", "10,20,30"};
        String[] result = parser.parseArguments(args);
        assertArrayEquals(new String[]{"-add", "10,20,30"}, result);
    }

    @Test
    void testParseArgumentsWithSpecialCharacters() {
        CommandLineParser parser = new CommandLineParser();
        String[] args = {"-add", "/path/to/file"};
        String[] result = parser.parseArguments(args);
        assertArrayEquals(new String[]{"-add", "/path/to/file"}, result);
    }

    @Test
    void testParseArgumentsWithMixedCase() {
        CommandLineParser parser = new CommandLineParser();
        String[] args = {"-Add", "10,20,30"};
        String[] result = parser.parseArguments(args);
        assertArrayEquals(new String[]{"-Add", "10,20,30"}, result);
    }

    @Test
    void testParseArgumentsWithInvalidInputs() {
        CommandLineParser parser = new CommandLineParser();
        String[] args = {"-add", "abc", "def"};
        String[] result = parser.parseArguments(args);
        assertArrayEquals(new String[]{"-add", "abc", "def"}, result);
    }
}
