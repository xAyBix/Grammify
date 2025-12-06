package ma.supmti.grammify.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	}
	
	// Menu Items
	private JMenu buildFileMenu () {
		JMenu file = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		newItem.addActionListener(e -> {
			// TODO Open an empty text area
		});
		openItem.addActionListener(e -> {
			FileManager.openFile();
			// TODO Put the text to Text Area
			
		});
		saveItem.addActionListener(e -> {
			String text = ""; // TODO Extract the text from the text area
			FileManager.saveFile(text);
		});
		exitItem.addActionListener(e -> {
			// TODO Open a dialog window that ask user to save if not saved
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
		return edit;
	}
	private JMenuItem buildSettingsMenu () {
		JMenuItem settings = new JMenuItem("Settings");
		return settings;
	}
	private JMenu buildHelpMenu () {
		JMenu help = new JMenu("Help");
		help.add(new JMenuItem("About"));
		return help;
	}

}
