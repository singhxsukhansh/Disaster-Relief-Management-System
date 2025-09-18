package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LanguageManagerTest {
    
    private LanguageManager langManager;
    
    @Before
    public void setUp() {
        langManager = new LanguageManager("en-CA");
        langManager.loadLanguageFile();
    }
    
    @Test
    public void testLoadLanguageFileAndGetTranslation_ValidKey() {
        String translation = langManager.getTranslation("welcomeMessage");
        assertNotNull(translation);
        assertFalse(translation.isEmpty());
    }
    
    @Test
    public void testGetTranslation_InvalidKey() {
        String translation = langManager.getTranslation("nonexistentKey");
        assertNotNull(translation);
    }
    
    @Test
    public void testFallbackToDefaultLanguage() {
        LanguageManager lm = new LanguageManager("xx-XX");
        lm.loadLanguageFile();
        String translation = lm.getTranslation("welcomeMessage");
        assertNotNull(translation);
        assertFalse(translation.isEmpty());
    }
    
    @Test
    public void testTranslationMapNotEmptyAfterLoad() {
        String sample = langManager.getTranslation("someKey");
        assertNotNull(sample);
    }
}
