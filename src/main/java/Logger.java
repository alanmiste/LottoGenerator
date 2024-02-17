import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Logger {
    private static final String LOG_FILE_NAME = "error_log.txt";
    private static final String LOG_DIRECTORY = "src/main/resources/";
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
    static LocalDateTime now = LocalDateTime.now();



    public static void logEvent(String event) {
        try {
            createLogFileIfNotExists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_DIRECTORY + LOG_FILE_NAME, true));
            writer.write(event+ " "+ dateTimeFormatter.format(now));
            writer.newLine();
            if(Objects.equals(event, "Application terminated."))
            {
                writer.write("--------------------");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logError(String error) {
        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_NAME, true));
            createLogFileIfNotExists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_DIRECTORY + LOG_FILE_NAME, true));
            writer.write("--> ERROR: " + error);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createLogFileIfNotExists() {
        Path path = Paths.get(LOG_DIRECTORY + LOG_FILE_NAME);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
