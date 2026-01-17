package ma.supmti.grammify.utils;

import javax.swing.JOptionPane;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import ma.supmti.grammify.GrammifyApplication;
import ma.supmti.grammify.ui.MainFrame;


/**
 * Undo/Redo Manager
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2026-01-17 10:50
 */
public class UndoRedoManager {
    
    private static UndoManager undoManager;
    private static UndoableEditListener undoableEditListener;
    
    /**
     * Initialize the undo/redo system for the text area
     */
    public static void initialize() {
        if (MainFrame.textArea == null) {
            return;
        }
        
        // Create a new UndoManager
        undoManager = new UndoManager();
        
        // Set a reasonable limit for undo operations (default is 100)
        undoManager.setLimit(1000);
        
        // Create the listener
        undoableEditListener = e -> undoManager.addEdit(e.getEdit());
        
        // Add the listener to the document
        MainFrame.textArea.getDocument().addUndoableEditListener(undoableEditListener);
    }
    
    /**
     * Remove the undo/redo listener (useful when loading a new file)
     */
    public static void removeListener() {
        if (MainFrame.textArea != null && undoableEditListener != null) {
            MainFrame.textArea.getDocument().removeUndoableEditListener(undoableEditListener);
        }
    }
    
    /**
     * Re-add the undo/redo listener
     */
    public static void addListener() {
        if (MainFrame.textArea != null && undoableEditListener != null) {
            MainFrame.textArea.getDocument().addUndoableEditListener(undoableEditListener);
        }
    }
    
    /**
     * Perform an undo operation
     */
    public static void undo() {
        if (undoManager != null && undoManager.canUndo()) {
            try {
                undoManager.undo();
            } catch (CannotUndoException e) {
                JOptionPane.showMessageDialog(
                    GrammifyApplication.mainFrame,
                    "Cannot undo: " + e.getMessage(),
                    "Undo Error",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }
    
    /**
     * Perform a redo operation
     */
    public static void redo() {
        if (undoManager != null && undoManager.canRedo()) {
            try {
                undoManager.redo();
            } catch (CannotRedoException e) {
                JOptionPane.showMessageDialog(
                    GrammifyApplication.mainFrame,
                    "Cannot redo: " + e.getMessage(),
                    "Redo Error",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }
    
    /**
     * Check if undo is available
     */
    public static boolean canUndo() {
        return undoManager != null && undoManager.canUndo();
    }
    
    /**
     * Check if redo is available
     */
    public static boolean canRedo() {
        return undoManager != null && undoManager.canRedo();
    }
    
    /**
     * Get the name of the next undo action
     */
    public static String getUndoPresentationName() {
        if (undoManager != null && undoManager.canUndo()) {
            return undoManager.getUndoPresentationName();
        }
        return "Undo";
    }
    
    /**
     * Get the name of the next redo action
     */
    public static String getRedoPresentationName() {
        if (undoManager != null && undoManager.canRedo()) {
            return undoManager.getRedoPresentationName();
        }
        return "Redo";
    }
    
    /**
     * Clear all undo/redo history
     */
    public static void discardAllEdits() {
        if (undoManager != null) {
            undoManager.discardAllEdits();
        }
    }
    
    /**
     * Reset the undo manager (useful when opening a new file)
     */
    public static void reset() {
        if (undoManager != null) {
            undoManager.discardAllEdits();
        }
    }
}