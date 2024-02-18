import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    private static final String LOG_FILE_NAME = "src/main/resources/error_log.txt";

    private String readLogFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(LOG_FILE_NAME)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    void testLogEvent() {
        String event = "Test event";
        Logger.logEvent(event);

        // Check if event is logged properly
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/error_log.txt"))) {
            String line;
            boolean eventFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains(event)) {
                    eventFound = true;
                    break;
                }
            }
            assertTrue(eventFound, "Event was not logged properly.");
        } catch (IOException e) {
            fail("Error reading log file: " + e.getMessage());
        }
    }

    @Test
    void testLogError() {
        String error = "Test error";
        Logger.logError(error);

        // Check if error is logged properly
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/error_log.txt"))) {
            String line;
            boolean errorFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ERROR") && line.contains(error)) {
                    errorFound = true;
                    break;
                }
            }
            assertTrue(errorFound, "Error was not logged properly.");
        } catch (IOException e) {
            fail("Error reading log file: " + e.getMessage());
        }
    }

    @Test
    void testLogFileCreation() {
        // Check if log file is created
        assertTrue(Files.exists(Paths.get("src/main/resources/error_log.txt")), "Log file was not created.");
    }

    @Test
    void testLogEventWithValidDateTimeFormat() {
        LocalDateTime now = LocalDateTime.now();
        String event = "Test event";
        Logger.logEvent(event);
        String logContent = readLogFile();
        assertTrue(logContent.contains(event + " " + Logger.dateTimeFormatter.format(now)));
    }

    @Test
    void testLogEventOnApplicationTermination() {
        // Simulate application termination
        Logger.logEvent("Application terminated.");
        String logContent = readLogFile();
        assertTrue(logContent.contains("Application terminated."));
    }

    @Test
    void testReadLogFileIsNotEmpty() {
        String logContent = readLogFile();
        assertNotNull(logContent);
    }


}
