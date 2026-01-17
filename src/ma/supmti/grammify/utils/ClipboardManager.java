package ma.supmti.grammify.utils;

import ma.supmti.grammify.GrammifyApplication;
import ma.supmti.grammify.ui.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;

/**
 * Copy cut past
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2026-01-17 10:50
 */
public class ClipboardManager {
    
    /**
     * Cut selected text to clipboard
     */
    public static void cut() {
        if (MainFrame.textArea != null && MainFrame.textArea.isEditable()) {
            String selectedText = MainFrame.textArea.getSelectedText();
            
            if (selectedText != null && !selectedText.isEmpty()) {
                // Copy to clipboard
                copyToClipboard(selectedText);
                
                // Remove selected text
                MainFrame.textArea.replaceSelection("");
            }
        }
    }
    
    /**
     * Copy selected text to clipboard
     */
    public static void copy() {
        if (MainFrame.textArea != null) {
            String selectedText = MainFrame.textArea.getSelectedText();
            
            if (selectedText != null && !selectedText.isEmpty()) {
                copyToClipboard(selectedText);
            }
        }
    }
    
    /**
     * Paste text from clipboard
     */
    public static void paste() {
        if (MainFrame.textArea != null && MainFrame.textArea.isEditable()) {
            String clipboardText = getFromClipboard();
            
            if (clipboardText != null && !clipboardText.isEmpty()) {
                MainFrame.textArea.replaceSelection(clipboardText);
            }
        }
    }
    
    /**
     * Select all text in the text area
     */
    public static void selectAll() {
        if (MainFrame.textArea != null) {
            MainFrame.textArea.selectAll();
        }
    }
    
    /**
     * Delete selected text (without copying to clipboard)
     */
    public static void delete() {
        if (MainFrame.textArea != null && MainFrame.textArea.isEditable()) {
            String selectedText = MainFrame.textArea.getSelectedText();
            
            if (selectedText != null && !selectedText.isEmpty()) {
                MainFrame.textArea.replaceSelection("");
            }
        }
    }
    
    /**
     * Check if there is selected text
     */
    public static boolean hasSelection() {
        if (MainFrame.textArea != null) {
            String selectedText = MainFrame.textArea.getSelectedText();
            return selectedText != null && !selectedText.isEmpty();
        }
        return false;
    }
    
    /**
     * Check if clipboard has text content
     */
    public static boolean hasClipboardContent() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            return clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Copy text to system clipboard
     */
    private static void copyToClipboard(String text) {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(text);
            clipboard.setContents(selection, selection);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                GrammifyApplication.mainFrame,
                "Error copying to clipboard: " + e.getMessage(),
                "Clipboard Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    /**
     * Get text from system clipboard
     */
    private static String getFromClipboard() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            
            if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                return (String) clipboard.getData(DataFlavor.stringFlavor);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                GrammifyApplication.mainFrame,
                "Error reading from clipboard: " + e.getMessage(),
                "Clipboard Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
    
    /**
     * Get selected text
     */
    public static String getSelectedText() {
        if (MainFrame.textArea != null) {
            return MainFrame.textArea.getSelectedText();
        }
        return null;
    }
    
    /**
     * Get selection start position
     */
    public static int getSelectionStart() {
        if (MainFrame.textArea != null) {
            return MainFrame.textArea.getSelectionStart();
        }
        return -1;
    }
    
    /**
     * Get selection end position
     */
    public static int getSelectionEnd() {
        if (MainFrame.textArea != null) {
            return MainFrame.textArea.getSelectionEnd();
        }
        return -1;
    }
}