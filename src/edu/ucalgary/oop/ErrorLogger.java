package edu.ucalgary.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogger {

    private static final String LOG_FILE_PATH = "data/errorlog.txt";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logError(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
            writer.write("[" + timestamp + "] " + message + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Failed to write to error log: " + e.getMessage());
        }
    }
}
