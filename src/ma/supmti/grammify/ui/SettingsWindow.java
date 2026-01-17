package ma.supmti.grammify.ui;



import javax.swing.*;
import javax.swing.border.*;

import ma.supmti.grammify.Constants;
import ma.supmti.grammify.Settings;

import java.awt.*;
import java.awt.event.*;

/**
 * The main frame that contains the GUI components
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2026-01-17 10:50
 */
public class SettingsWindow extends JDialog {
    
    private JPanel mainPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    // Setting components
    private JComboBox<String> themeComboBox;
    private JSpinner fontSizeSpinner;
    private JComboBox<String> fontFamilyComboBox;
    private JCheckBox lineNumbersCheckBox;
    private JCheckBox wordWrapCheckBox;
    private JCheckBox autoSaveCheckBox;
    private JSpinner autoSaveIntervalSpinner;
    private JCheckBox grammarCheckCheckBox;
    private JCheckBox spellCheckCheckBox;
    
    public SettingsWindow(JFrame parent) {
        super(parent, "Settings", true);
        
        setSize(800, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        initComponents();
        loadCurrentSettings();
        
        setVisible(true);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Create sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);
        
        // Create content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);
        
        // Add different settings panels
        contentPanel.add(createAppearancePanel(), "Appearance");
        contentPanel.add(createEditorPanel(), "Editor");
        contentPanel.add(createGrammarPanel(), "Grammar");
        contentPanel.add(createAboutPanel(), "About");
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Create bottom button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(45, 45, 48));
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        // Category buttons
        addCategoryButton(sidebar, "Appearance", "Appearance");
        addCategoryButton(sidebar, "Editor", "Editor");
        addCategoryButton(sidebar, "Grammar", "Grammar");
        
        // Add spacer
        sidebar.add(Box.createVerticalGlue());
        
        addCategoryButton(sidebar, "About", "About");
        
        return sidebar;
    }
    
    private void addCategoryButton(JPanel sidebar, String text, String cardName) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setBackground(new Color(45, 45, 48));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(60, 60, 65));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(45, 45, 48));
            }
        });
        
        button.addActionListener(e -> cardLayout.show(contentPanel, cardName));
        
        sidebar.add(button);
    }
    
    private JPanel createAppearancePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Title
        JLabel titleLabel = new JLabel("Appearance");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        
        // Theme
        panel.add(createSettingRow("Theme:", 
            themeComboBox = new JComboBox<>(new String[]{"Light", "Dark", "High Contrast"})));
        
        // Font Family
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        panel.add(createSettingRow("Font Family:", 
            fontFamilyComboBox = new JComboBox<>(fonts)));
        
        // Font Size
        SpinnerNumberModel fontSizeModel = new SpinnerNumberModel(14, 8, 72, 1);
        fontSizeSpinner = new JSpinner(fontSizeModel);
        panel.add(createSettingRow("Font Size:", fontSizeSpinner));
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    private JPanel createEditorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Title
        JLabel titleLabel = new JLabel("Editor");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        
        // Line Numbers
        lineNumbersCheckBox = new JCheckBox("Show line numbers");
        panel.add(createCheckBoxRow(lineNumbersCheckBox));
        
        // Word Wrap
        wordWrapCheckBox = new JCheckBox("Enable word wrap");
        panel.add(createCheckBoxRow(wordWrapCheckBox));
        
        // Auto Save
        autoSaveCheckBox = new JCheckBox("Enable auto-save");
        panel.add(createCheckBoxRow(autoSaveCheckBox));
        
        // Auto Save Interval
        SpinnerNumberModel intervalModel = new SpinnerNumberModel(5, 1, 60, 1);
        autoSaveIntervalSpinner = new JSpinner(intervalModel);
        JPanel intervalPanel = createSettingRow("Auto-save interval (minutes):", autoSaveIntervalSpinner);
        panel.add(intervalPanel);
        
        // Enable/disable interval based on auto-save checkbox
        autoSaveCheckBox.addActionListener(e -> 
            autoSaveIntervalSpinner.setEnabled(autoSaveCheckBox.isSelected()));
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    private JPanel createGrammarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Title
        JLabel titleLabel = new JLabel("Grammar & Spelling");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        
        // Grammar Check
        grammarCheckCheckBox = new JCheckBox("Enable grammar checking");
        panel.add(createCheckBoxRow(grammarCheckCheckBox));
        
        // Spell Check
        spellCheckCheckBox = new JCheckBox("Enable spell checking");
        panel.add(createCheckBoxRow(spellCheckCheckBox));
        
        panel.add(Box.createVerticalStrut(20));
        
        // Info text
        JLabel infoLabel = new JLabel("<html><i>Grammar and spelling checks will be performed in real-time as you type.</i></html>");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoLabel.setForeground(Color.GRAY);
        infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(infoLabel);
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    private JPanel createAboutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Title
        JLabel titleLabel = new JLabel("About");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(30));
        
        // App Icon
        if (Constants.IMG != null) {
            JLabel iconLabel = new JLabel(new ImageIcon(
                Constants.IMG.getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(iconLabel);
            panel.add(Box.createVerticalStrut(20));
        }
        
        // App Name
        JLabel appNameLabel = new JLabel(Constants.APP_NAME);
        appNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(appNameLabel);
        panel.add(Box.createVerticalStrut(10));
        
        // Version
        JLabel versionLabel = new JLabel("Version 1.0.0");
        versionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        versionLabel.setForeground(Color.GRAY);
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(versionLabel);
        panel.add(Box.createVerticalStrut(20));
        
        // Description
        JLabel descLabel = new JLabel("<html><center>A modern text editor with grammar checking capabilities.</center></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(descLabel);
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    private JPanel createSettingRow(String label, JComponent component) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setBackground(Color.WHITE);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        row.setBorder(new EmptyBorder(5, 0, 5, 0));
        
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelComponent.setPreferredSize(new Dimension(200, 30));
        
        component.setPreferredSize(new Dimension(200, 30));
        
        row.add(labelComponent, BorderLayout.WEST);
        row.add(component, BorderLayout.EAST);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        return row;
    }
    
    private JPanel createCheckBoxRow(JCheckBox checkBox) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(Color.WHITE);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
        checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        checkBox.setBackground(Color.WHITE);
        
        row.add(checkBox);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        return row;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 35));
        cancelButton.addActionListener(e -> dispose());
        
        JButton applyButton = new JButton("Apply");
        applyButton.setPreferredSize(new Dimension(100, 35));
        applyButton.addActionListener(e -> applySettings());
        
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 35));
        okButton.addActionListener(e -> {
            applySettings();
            dispose();
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(applyButton);
        buttonPanel.add(okButton);
        
        return buttonPanel;
    }
    
    private void loadCurrentSettings() {
        // Load current settings from Settings class
        themeComboBox.setSelectedItem(Settings.theme);
        fontFamilyComboBox.setSelectedItem(Settings.fontFamily);
        fontSizeSpinner.setValue(Settings.fontSize);
        lineNumbersCheckBox.setSelected(Settings.showLineNumbers);
        wordWrapCheckBox.setSelected(Settings.wordWrap);
        autoSaveCheckBox.setSelected(Settings.autoSave);
        autoSaveIntervalSpinner.setValue(Settings.autoSaveInterval);
        autoSaveIntervalSpinner.setEnabled(Settings.autoSave);
        grammarCheckCheckBox.setSelected(Settings.grammarCheck);
        spellCheckCheckBox.setSelected(Settings.spellCheck);
    }
    
    private void applySettings() {
        // Update Settings class with new values
        Settings.theme = (String) themeComboBox.getSelectedItem();
        Settings.fontFamily = (String) fontFamilyComboBox.getSelectedItem();
        Settings.fontSize = (Integer) fontSizeSpinner.getValue();
        Settings.secondaryFontSize = Math.max(12, Settings.fontSize - 4);
        Settings.showLineNumbers = lineNumbersCheckBox.isSelected();
        Settings.wordWrap = wordWrapCheckBox.isSelected();
        Settings.autoSave = autoSaveCheckBox.isSelected();
        Settings.autoSaveInterval = (Integer) autoSaveIntervalSpinner.getValue();
        Settings.grammarCheck = grammarCheckCheckBox.isSelected();
        Settings.spellCheck = spellCheckCheckBox.isSelected();
        
        // Apply settings to UI
        Settings.applyToUI();
        
        // Save settings to file
        Settings.saveSettings();
        
        JOptionPane.showMessageDialog(this, 
            "Settings applied successfully!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void saveSettingsToFile() {
        // This is now handled by Settings.saveSettings()
    }
    
    // Static method to show the settings window
    public static void show(JFrame parent) {
        new SettingsWindow(parent);
    }
}