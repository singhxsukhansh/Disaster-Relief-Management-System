package edu.ucalgary.oop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LanguageManager {
    private String languageCode;
    private Map<String, String> translations;
    private static final String DEFAULT_LANGUAGE = "en-CA";
    private static final String DATA_PATH = "data/";

    public LanguageManager(String languageCode) {
        this.languageCode = languageCode;
        this.translations = new HashMap<>();
    }

    public void loadLanguageFile() {
        String filePath = DATA_PATH + languageCode + ".xml";
        File file = new File(filePath);

        if (!file.exists()) {
            // Fallback to default
            filePath = DATA_PATH + DEFAULT_LANGUAGE + ".xml";
        }

        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("<") && line.endsWith(">") && !line.startsWith("<?")) {
                    int startTagStart = line.indexOf('<') + 1;
                    int startTagEnd = line.indexOf('>', startTagStart);
                    int endTagStart = line.lastIndexOf("</");

                    if (startTagEnd > startTagStart && endTagStart > startTagEnd) {
                        String key = line.substring(startTagStart, startTagEnd).trim();
                        String value = line.substring(startTagEnd + 1, endTagStart).trim();
                        translations.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            ErrorLogger.logError("Error loading language file: " + e.getMessage());
        }
    }

    public String getTranslation(String key) {
        return translations.getOrDefault(key, "[" + key + "]");
    }
}
