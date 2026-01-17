package ma.supmti.grammify.ui;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import ma.supmti.grammify.Constants;
import ma.supmti.grammify.GrammifyApplication;
import ma.supmti.grammify.Settings;
import ma.supmti.grammify.io.FileManager;
import ma.supmti.grammify.utils.ClipboardManager;
import ma.supmti.grammify.utils.UndoRedoManager;

/**
 * A useful menu bar at the top contains file management items,
 * text editing items, and more...
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2025-11-16 22:49
 */
public class MenuBar extends JMenuBar{
	
	private static final long serialVersionUID = 981151431268931065L;
	
	public MenuBar () {
		add(buildFileMenu());
		add(buildEditMenu());
		add(buildSettingsMenu());
		add(buildHelpMenu());
		
		setBackground(Constants.mainColor());
		setForeground(Color.WHITE);
		
		setBorder(BorderFactory.createEmptyBorder());
		
	}
	
	// Menu Items
	private JMenu buildFileMenu () {
		JMenu file = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		file.setBackground(Constants.mainColor());
		file.setForeground(Color.WHITE);
		file.setFont(Constants.secondaryFont());
		file.getPopupMenu().setBorder(BorderFactory.createEmptyBorder());
		
		newItem.setBackground(Constants.mainColor());
		newItem.setForeground(Color.WHITE);
		newItem.setFont(Constants.secondaryFont());
		newItem.setBorder(BorderFactory.createEmptyBorder());
		openItem.setBackground(Constants.mainColor());
		openItem.setForeground(Color.WHITE);
		openItem.setFont(Constants.secondaryFont());
		openItem.setBorder(BorderFactory.createEmptyBorder());
		saveItem.setBackground(Constants.mainColor());
		saveItem.setForeground(Color.WHITE);
		saveItem.setFont(Constants.secondaryFont());
		saveItem.setBorder(BorderFactory.createEmptyBorder());
		exitItem.setBackground(Constants.mainColor());
		exitItem.setForeground(Color.WHITE);
		exitItem.setFont(Constants.secondaryFont());
		exitItem.setBorder(BorderFactory.createEmptyBorder());
		
		newItem.addActionListener(e -> {
			FileManager.newFile();
		});
		openItem.addActionListener(e -> {
			FileManager.openFile();
		});
		saveItem.addActionListener(e -> {
			FileManager.saveFile();
		});
		exitItem.addActionListener(e -> {
			if (FileManager.openedFileAlreadyCheck()) {
				int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame, "You have unsaved changes. Do you want to save?",
						"Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					FileManager.saveFile();
				}else if (result == JOptionPane.NO_OPTION) {
					// Don't save
				}else {
					return;
				}
			}
			GrammifyApplication.mainFrame.dispose();
			System.exit(0);
		});
		
		file.add(newItem);
		file.add(openItem);
		file.add(saveItem);
		file.add(exitItem);
		
//		file.addMenuListener(new javax.swing.event.MenuListener() {
//		    @Override
//		    public void menuSelected(javax.swing.event.MenuEvent e) {
//		        boolean hasSelection = ClipboardManager.hasSelection();
//		        boolean hasClipboard = ClipboardManager.hasClipboardContent();
//		        boolean isEditable = MainFrame.textArea != null && MainFrame.textArea.isEditable();
//		        saveItem.setEnabled(UndoRedoManager.canUndo());
//		    }
//		    
//		    @Override
//		    public void menuDeselected(javax.swing.event.MenuEvent e) {}
//		    
//		    @Override
//		    public void menuCanceled(javax.swing.event.MenuEvent e) {}
//		});

		return file;
	}
	private JMenu buildEditMenu () {
		JMenu edit = new JMenu("Edit");
		JMenuItem undoItem = new JMenuItem("Undo");
		JMenuItem redoItem = new JMenuItem("Redo");
		JMenuItem cutItem = new JMenuItem("Cut");
		JMenuItem copyItem = new JMenuItem("Copy");
		JMenuItem pasteItem = new JMenuItem("Paste");
		
		edit.setBackground(Constants.mainColor());
		edit.setForeground(Color.WHITE);
		edit.setFont(Constants.secondaryFont());
		edit.getPopupMenu().setBorder(BorderFactory.createEmptyBorder());
		
		undoItem.setBackground(Constants.mainColor());
		undoItem.setForeground(Color.WHITE);
		undoItem.setFont(Constants.secondaryFont());
		undoItem.setBorder(BorderFactory.createEmptyBorder());
		redoItem.setBackground(Constants.mainColor());
		redoItem.setForeground(Color.WHITE);
		redoItem.setFont(Constants.secondaryFont());
		redoItem.setBorder(BorderFactory.createEmptyBorder());
		cutItem.setBackground(Constants.mainColor());
		cutItem.setForeground(Color.WHITE);
		cutItem.setFont(Constants.secondaryFont());
		cutItem.setBorder(BorderFactory.createEmptyBorder());
		copyItem.setBackground(Constants.mainColor());
		copyItem.setForeground(Color.WHITE);
		copyItem.setFont(Constants.secondaryFont());
		copyItem.setBorder(BorderFactory.createEmptyBorder());
		pasteItem.setBackground(Constants.mainColor());
		pasteItem.setForeground(Color.WHITE);
		pasteItem.setFont(Constants.secondaryFont());
		pasteItem.setBorder(BorderFactory.createEmptyBorder());
		cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		cutItem.addActionListener(e -> ClipboardManager.cut());
		copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		copyItem.addActionListener(e -> ClipboardManager.copy());
		pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		pasteItem.addActionListener(e -> ClipboardManager.paste());
		
		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		undoItem.addActionListener(e -> UndoRedoManager.undo());
		
		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
		redoItem.addActionListener(e -> UndoRedoManager.redo());
		edit.add(undoItem);
		edit.add(redoItem);
		edit.add(cutItem);
		edit.add(copyItem);
		edit.add(pasteItem);
		
//		edit.addMenuListener(new javax.swing.event.MenuListener() {
//		    @Override
//		    public void menuSelected(javax.swing.event.MenuEvent e) {
//		        boolean hasSelection = ClipboardManager.hasSelection();
//		        boolean hasClipboard = ClipboardManager.hasClipboardContent();
//		        boolean isEditable = MainFrame.textArea != null && MainFrame.textArea.isEditable();
//		        
//		        // Update undo/redo
//		        undoItem.setEnabled(UndoRedoManager.canUndo());
//		        redoItem.setEnabled(UndoRedoManager.canRedo());
//		        
//		        // Update clipboard operations
//		        cutItem.setEnabled(hasSelection && isEditable);
//		        copyItem.setEnabled(hasSelection);
//		        pasteItem.setEnabled(hasClipboard && isEditable);
//		    }
//		    
//		    @Override
//		    public void menuDeselected(javax.swing.event.MenuEvent e) {}
//		    
//		    @Override
//		    public void menuCanceled(javax.swing.event.MenuEvent e) {}
//		});
		
		
		return edit;
	}
	private JMenuItem buildSettingsMenu () {
		JMenuItem settings = new JMenuItem("Settings");
		
		settings.setBackground(Constants.mainColor());
		settings.setForeground(Color.WHITE);
		settings.setFont(Constants.secondaryFont());
		settings.setBorder(BorderFactory.createEmptyBorder());
		
		settings.addActionListener(e -> {
			SettingsWindow.show(GrammifyApplication.mainFrame);
		});
		return settings;
	}
	private JMenu buildHelpMenu () {
		JMenu help = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setBackground(Constants.mainColor());
		aboutItem.setForeground(Color.WHITE);
		aboutItem.setFont(Constants.secondaryFont());
		aboutItem.setBorder(BorderFactory.createEmptyBorder());
		
		aboutItem.addActionListener(e -> AboutWindow.show(GrammifyApplication.mainFrame));
		
		help.add(aboutItem);
		
		help.setBackground(Constants.mainColor());
		help.setForeground(Color.WHITE);
		help.setFont(Constants.secondaryFont());
		help.getPopupMenu().setBorder(BorderFactory.createEmptyBorder());
		
		return help;
	}

}
