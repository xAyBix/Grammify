package ma.supmti.grammify.ui;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ma.supmti.grammify.Constants;
import ma.supmti.grammify.GrammifyApplication;
import ma.supmti.grammify.io.FileManager;

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
		
		setBackground(Constants.mainColor);
		setForeground(Color.WHITE);
		
	}
	
	// Menu Items
	private JMenu buildFileMenu () {
		JMenu file = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		file.setBackground(Constants.mainColor);
		file.setForeground(Color.WHITE);
		file.setFont(Constants.secondaryFont);
		
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
		file.addSeparator();
		file.add(exitItem);
		return file;
	}
	private JMenu buildEditMenu () {
		JMenu edit = new JMenu("Edit");
		edit.add(new JMenuItem("Undo"));
		edit.add(new JMenuItem("Redo"));
		edit.addSeparator();
		edit.add(new JMenuItem("Cut"));
		edit.add(new JMenuItem("Copy"));
		edit.add(new JMenuItem("Paste"));
		
		edit.setBackground(Constants.mainColor);
		edit.setForeground(Color.WHITE);
		edit.setFont(Constants.secondaryFont);
		return edit;
	}
	private JMenuItem buildSettingsMenu () {
		JMenuItem settings = new JMenuItem("Settings");
		
		settings.setBackground(Constants.mainColor);
		settings.setForeground(Color.WHITE);
		settings.setFont(Constants.secondaryFont);
		return settings;
	}
	private JMenu buildHelpMenu () {
		JMenu help = new JMenu("Help");
		help.add(new JMenuItem("About"));
		
		help.setBackground(Constants.mainColor);
		help.setForeground(Color.WHITE);
		help.setFont(Constants.secondaryFont);
		
		return help;
	}

}
