package ma.supmti.grammify.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * jsj
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2025-11-16 22:49
 */
public class MenuBar extends JMenuBar{
	
	public MenuBar () {
		add(buildFileMenu());
		add(buildEditMenu());
		add(buildSettingsMenu());
		add(buildHelpMenu());
	}
	
	// Menu Items
	private JMenu buildFileMenu () {
		JMenu file = new JMenu("File");
		file.add(new JMenuItem("New"));
		file.add(new JMenuItem("Open"));
		file.add(new JMenuItem("Save"));
		file.addSeparator();
		file.add(new JMenuItem("Exit"));
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
