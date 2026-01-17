package ma.supmti.grammify.ui;


import javax.swing.*;
import javax.swing.border.*;

import ma.supmti.grammify.Constants;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
/**
 * The main frame that contains the GUI components
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2026-01-17 10:50
 */
public class AboutWindow extends JDialog {
    
    private static final String VERSION = "1.0.0";
    private static final String BUILD_DATE = "January 2026";
    private static final String DEVELOPER = "Your Name";
    private static final String WEBSITE = "https://github.com/yourusername/grammify";
    private static final String EMAIL = "support@grammify.com";
    
    public AboutWindow(JFrame parent) {
        super(parent, "About " + Constants.APP_NAME, true);
        
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        initComponents();
        
        setVisible(true);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        
        // App Icon
        if (Constants.IMG != null) {
            JLabel iconLabel = new JLabel(new ImageIcon(
                Constants.IMG.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(iconLabel);
            contentPanel.add(Box.createVerticalStrut(20));
        }
        
        // App Name
        JLabel appNameLabel = new JLabel(Constants.APP_NAME);
        appNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(appNameLabel);
        contentPanel.add(Box.createVerticalStrut(10));
        
        // Tagline
        JLabel taglineLabel = new JLabel("Your Modern Text Editor with Grammar Checking");
        taglineLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        taglineLabel.setForeground(new Color(100, 100, 100));
        taglineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(taglineLabel);
        contentPanel.add(Box.createVerticalStrut(30));
        
        // Version Info Panel
        JPanel versionPanel = createInfoPanel();
        versionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(versionPanel);
        contentPanel.add(Box.createVerticalStrut(30));
        
        // Description
        JTextArea descriptionArea = new JTextArea(
            Constants.APP_NAME + " is a powerful and intuitive text editor designed to help you write " +
            "with confidence. With built-in grammar checking, spell checking, and a clean modern " +
            "interface, " + Constants.APP_NAME + " makes writing a pleasure."
        );
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setMaximumSize(new Dimension(400, 100));
        contentPanel.add(descriptionArea);
        contentPanel.add(Box.createVerticalStrut(30));
        
        // Links Panel
        JPanel linksPanel = createLinksPanel();
        linksPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(linksPanel);
        contentPanel.add(Box.createVerticalStrut(30));
        
        // Copyright
        JLabel copyrightLabel = new JLabel("© 2026 " + DEVELOPER + ". All rights reserved.");
        copyrightLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        copyrightLabel.setForeground(new Color(120, 120, 120));
        copyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(copyrightLabel);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Bottom button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 20, 10));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));
        panel.setMaximumSize(new Dimension(400, 120));
        
        // Version
        addInfoRow(panel, "Version:", VERSION);
        
        // Build Date
        addInfoRow(panel, "Build Date:", BUILD_DATE);
        
        // Developer
        addInfoRow(panel, "Developer:", DEVELOPER);
        
        return panel;
    }
    
    private void addInfoRow(JPanel panel, String label, String value) {
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelComponent.setForeground(new Color(80, 80, 80));
        
        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        valueComponent.setForeground(new Color(50, 50, 50));
        
        panel.add(labelComponent);
        panel.add(valueComponent);
    }
    
    private JPanel createLinksPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(400, 40));
        
        // Website button
        JButton websiteBtn = createLinkButton("🌐 Website", WEBSITE);
        panel.add(websiteBtn);
        
        // Email button
        JButton emailBtn = createLinkButton("✉ Email Support", "mailto:" + EMAIL);
        panel.add(emailBtn);
        
        // License button
        JButton licenseBtn = createLinkButton("📄 License", null);
        licenseBtn.addActionListener(e -> showLicense());
        panel.add(licenseBtn);
        
        return panel;
    }
    
    private JButton createLinkButton(String text, String url) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setForeground(new Color(0, 102, 204));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setText("<html><u>" + text + "</u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setText(text);
            }
        });
        
        if (url != null) {
            button.addActionListener(e -> openURL(url));
        }
        
        return button;
    }
    
    private void openURL(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please visit: " + url,
                    "Link",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Could not open link: " + url,
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showLicense() {
        String licenseText = 
            "MIT License\n\n" +
            "Copyright (c) 2026 " + DEVELOPER + "\n\n" +
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
            "of this software and associated documentation files (the \"Software\"), to deal\n" +
            "in the Software without restriction, including without limitation the rights\n" +
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
            "copies of the Software, and to permit persons to whom the Software is\n" +
            "furnished to do so, subject to the following conditions:\n\n" +
            "The above copyright notice and this permission notice shall be included in all\n" +
            "copies or substantial portions of the Software.\n\n" +
            "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
            "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
            "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
            "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
            "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
            "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
            "SOFTWARE.";
        
        JTextArea textArea = new JTextArea(licenseText);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        textArea.setCaretPosition(0);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        JOptionPane.showMessageDialog(this,
            scrollPane,
            "License",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(245, 245, 245));
        
        JButton systemInfoBtn = new JButton("System Information");
        systemInfoBtn.setPreferredSize(new Dimension(150, 35));
        systemInfoBtn.addActionListener(e -> showSystemInfo());
        
        JButton closeBtn = new JButton("Close");
        closeBtn.setPreferredSize(new Dimension(100, 35));
        closeBtn.addActionListener(e -> dispose());
        
        panel.add(systemInfoBtn);
        panel.add(closeBtn);
        
        return panel;
    }
    
    private void showSystemInfo() {
        String systemInfo = String.format(
            "System Information\n\n" +
            "Operating System: %s\n" +
            "OS Version: %s\n" +
            "OS Architecture: %s\n\n" +
            "Java Version: %s\n" +
            "Java Vendor: %s\n" +
            "Java Home: %s\n\n" +
            "User Name: %s\n" +
            "User Home: %s\n" +
            "Working Directory: %s\n\n" +
            "Available Processors: %d\n" +
            "Total Memory: %d MB\n" +
            "Free Memory: %d MB\n" +
            "Max Memory: %d MB",
            System.getProperty("os.name"),
            System.getProperty("os.version"),
            System.getProperty("os.arch"),
            System.getProperty("java.version"),
            System.getProperty("java.vendor"),
            System.getProperty("java.home"),
            System.getProperty("user.name"),
            System.getProperty("user.home"),
            System.getProperty("user.dir"),
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().totalMemory() / (1024 * 1024),
            Runtime.getRuntime().freeMemory() / (1024 * 1024),
            Runtime.getRuntime().maxMemory() / (1024 * 1024)
        );
        
        JTextArea textArea = new JTextArea(systemInfo);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setCaretPosition(0);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 350));
        
        JOptionPane.showMessageDialog(this,
            scrollPane,
            "System Information",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Static method to show the About window
     */
    public static void show(JFrame parent) {
        new AboutWindow(parent);
    }
}
