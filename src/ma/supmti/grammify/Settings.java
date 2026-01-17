package ma.supmti.grammify;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import ma.supmti.grammify.ui.MainFrame;

/**
 * The main frame that contains the GUI components
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2026-01-17 10:50
 */
public class Settings {
    
    private static final String SETTINGS_FILE = "settings.properties";
    private static Properties properties = new Properties();
    
    // Default values
    private static final String DEFAULT_THEME = "Dark";
    private static final String DEFAULT_FONT_FAMILY = "Monospaced";
    private static final int DEFAULT_FONT_SIZE = 20;
    private static final boolean DEFAULT_LINE_NUMBERS = true;
    private static final boolean DEFAULT_WORD_WRAP = false;
    private static final boolean DEFAULT_AUTO_SAVE = false;
    private static final int DEFAULT_AUTO_SAVE_INTERVAL = 5;
    private static final boolean DEFAULT_GRAMMAR_CHECK = true;
    private static final boolean DEFAULT_SPELL_CHECK = true;
    
    // Current settings (initialized with defaults)
    public static String theme = DEFAULT_THEME;
    public static String fontFamily = DEFAULT_FONT_FAMILY;
    public static int fontSize = DEFAULT_FONT_SIZE;
    public static int secondaryFontSize = 16;
    public static boolean showLineNumbers = DEFAULT_LINE_NUMBERS;
    public static boolean wordWrap = DEFAULT_WORD_WRAP;
    public static boolean autoSave = DEFAULT_AUTO_SAVE;
    public static int autoSaveInterval = DEFAULT_AUTO_SAVE_INTERVAL;
    public static boolean grammarCheck = DEFAULT_GRAMMAR_CHECK;
    public static boolean spellCheck = DEFAULT_SPELL_CHECK;
    
    // Colors (will be updated based on theme)
    public static Color mainColor = new Color(0x303030);
    public static Color secondaryColor = new Color(0x1F2022);
    public static Color thirdColor = new Color(0x656565);
    public static Color backgroundColor = new Color(0x303030);
    public static Color textColor = Color.WHITE;
    
    static {
        loadSettings();
        applyTheme();
    }
    
    /**
     * Load settings from file
     */
    public static void loadSettings() {
        File settingsFile = new File(SETTINGS_FILE);
        
        if (settingsFile.exists()) {
            try (FileInputStream fis = new FileInputStream(settingsFile)) {
                properties.load(fis);
                
                // Load settings from properties
                theme = properties.getProperty("theme", DEFAULT_THEME);
                fontFamily = properties.getProperty("fontFamily", DEFAULT_FONT_FAMILY);
                fontSize = Integer.parseInt(properties.getProperty("fontSize", String.valueOf(DEFAULT_FONT_SIZE)));
                secondaryFontSize = Integer.parseInt(properties.getProperty("secondaryFontSize", "16"));
                showLineNumbers = Boolean.parseBoolean(properties.getProperty("showLineNumbers", String.valueOf(DEFAULT_LINE_NUMBERS)));
                wordWrap = Boolean.parseBoolean(properties.getProperty("wordWrap", String.valueOf(DEFAULT_WORD_WRAP)));
                autoSave = Boolean.parseBoolean(properties.getProperty("autoSave", String.valueOf(DEFAULT_AUTO_SAVE)));
                autoSaveInterval = Integer.parseInt(properties.getProperty("autoSaveInterval", String.valueOf(DEFAULT_AUTO_SAVE_INTERVAL)));
                grammarCheck = Boolean.parseBoolean(properties.getProperty("grammarCheck", String.valueOf(DEFAULT_GRAMMAR_CHECK)));
                spellCheck = Boolean.parseBoolean(properties.getProperty("spellCheck", String.valueOf(DEFAULT_SPELL_CHECK)));
                
            } catch (IOException e) {
                System.err.println("Error loading settings: " + e.getMessage());
                useDefaults();
            }
        } else {
            useDefaults();
        }
    }
    
    /**
     * Save settings to file
     */
    public static void saveSettings() {
        properties.setProperty("theme", theme);
        properties.setProperty("fontFamily", fontFamily);
        properties.setProperty("fontSize", String.valueOf(fontSize));
        properties.setProperty("secondaryFontSize", String.valueOf(secondaryFontSize));
        properties.setProperty("showLineNumbers", String.valueOf(showLineNumbers));
        properties.setProperty("wordWrap", String.valueOf(wordWrap));
        properties.setProperty("autoSave", String.valueOf(autoSave));
        properties.setProperty("autoSaveInterval", String.valueOf(autoSaveInterval));
        properties.setProperty("grammarCheck", String.valueOf(grammarCheck));
        properties.setProperty("spellCheck", String.valueOf(spellCheck));
        
        try (FileOutputStream fos = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(fos, "Grammify Settings");
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }
    
    /**
     * Reset to default settings
     */
    public static void useDefaults() {
        theme = DEFAULT_THEME;
        fontFamily = DEFAULT_FONT_FAMILY;
        fontSize = DEFAULT_FONT_SIZE;
        secondaryFontSize = 16;
        showLineNumbers = DEFAULT_LINE_NUMBERS;
        wordWrap = DEFAULT_WORD_WRAP;
        autoSave = DEFAULT_AUTO_SAVE;
        autoSaveInterval = DEFAULT_AUTO_SAVE_INTERVAL;
        grammarCheck = DEFAULT_GRAMMAR_CHECK;
        spellCheck = DEFAULT_SPELL_CHECK;
    }
    
    /**
     * Apply theme colors based on selected theme
     */
    public static void applyTheme() {
        switch (theme) {
            case "Light":
                mainColor = new Color(0xF5F5F5);
                secondaryColor = new Color(0xE0E0E0);
                thirdColor = new Color(0xBDBDBD);
                backgroundColor = Color.WHITE;
                textColor = Color.BLACK;
                break;
                
            case "Dark":
                mainColor = new Color(0x303030);
                secondaryColor = new Color(0x1F2022);
                thirdColor = new Color(0x656565);
                backgroundColor = new Color(0x303030);
                textColor = Color.WHITE;
                break;
                
            case "High Contrast":
                mainColor = Color.BLACK;
                secondaryColor = new Color(0x1A1A1A);
                thirdColor = new Color(0x404040);
                backgroundColor = Color.BLACK;
                textColor = Color.WHITE;
                break;
                
            default:
                // Use dark theme as default
                mainColor = new Color(0x303030);
                secondaryColor = new Color(0x1F2022);
                thirdColor = new Color(0x656565);
                backgroundColor = new Color(0x303030);
                textColor = Color.WHITE;
        }
    }
    
    /**
     * Get main font with current settings
     */
    public static Font getMainFont() {
        return new Font(fontFamily, Font.BOLD, fontSize);
    }
    
    /**
     * Get secondary font with current settings
     */
    public static Font getSecondaryFont() {
        return new Font(fontFamily, Font.BOLD, secondaryFontSize);
    }
    
    /**
     * Get plain font with current settings
     */
    public static Font getPlainFont() {
        return new Font(fontFamily, Font.PLAIN, fontSize);
    }
    
    /**
     * Apply all settings to the application UI
     */
    public static void applyToUI() {
        applyTheme();
        
        if (MainFrame.textArea != null) {
            // Apply font
            MainFrame.textArea.setFont(getPlainFont());
            
            
            // Apply colors
            MainFrame.textArea.setBackground(backgroundColor);
            MainFrame.textArea.setForeground(textColor);
            MainFrame.textArea.setCaretColor(textColor);
        }
        
        if (MainFrame.mainScrollPane != null && MainFrame.lineNumber != null) {
            // Toggle line numbers visibility
            if (showLineNumbers) {
                // Show line numbers
                MainFrame.mainScrollPane.setRowHeaderView(MainFrame.lineNumber);
                MainFrame.mainScrollPane.getRowHeader().setOpaque(true);
                MainFrame.mainScrollPane.getRowHeader().setBackground(secondaryColor);
                MainFrame.lineNumber.setBackground(secondaryColor);
                MainFrame.lineNumber.setFont(getSecondaryFont());
            } else {
                // Hide line numbers
                MainFrame.mainScrollPane.setRowHeaderView(null);
            }
        }
        
        if (MainFrame.statusBar != null) {
            MainFrame.statusBar.setBackground(secondaryColor);
            MainFrame.statusBar.setForeground(textColor);
        }
        
        // Refresh the main frame
        if (GrammifyApplication.mainFrame != null) {
            GrammifyApplication.mainFrame.repaint();
            GrammifyApplication.mainFrame.revalidate();
        }
    }
}