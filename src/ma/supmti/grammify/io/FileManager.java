package ma.supmti.grammify.io;

import java.awt.FileDialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ma.supmti.grammify.Constants;
import ma.supmti.grammify.GrammifyApplication;
import ma.supmti.grammify.ui.CustomTextArea;
import ma.supmti.grammify.ui.MainFrame;

/**
 * Contains all the methods that help manipulating files
 * 
 * 
 * 
 * @author Rihab AMEUR
 * 
 * @since 2025-11-19 16:33
 */
public final class FileManager {
	// A private constructor to avoid instantiation
	private FileManager() {
	}
	
	// 
	public static void newFile() {
		// Setting the close operation
		openConfirmationDialog ();
		
		// Check if a file is already opened
		if (openedFileAlreadyCheck()) {
			int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame, "You have unsaved changes. Do you want to save?",
					"Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				saveFile();
			}else if (result == JOptionPane.NO_OPTION) {
				// Don't save
			}else {
				return;
			}
		}
		
		String fileName = "untitled.txt";
		MainFrame.textArea.setEditable(true);
		MainFrame.textArea.setText("");
		MainFrame.showCaret();

		// Changing the window title
		GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - " + fileName);

		// Saving false infos for later
		OpenedFile.file = new File("");
		OpenedFile.name = fileName;
		OpenedFile.path = "";
		OpenedFile.initialText = "";

		// Initialize Text Area functionalities (Check for unsaved, etc...)
		CustomTextArea.init();
		
	}

	// Method that opens a file
	public static String openFile() {
		// A StringBuilder used to store the file content
		StringBuilder text = new StringBuilder();
		try {
			// File Dialog to choose the file from the user's disk
			FileDialog fileDialog = new FileDialog(GrammifyApplication.mainFrame, "Open File", FileDialog.LOAD);
			fileDialog.setVisible(true);

			String directory = fileDialog.getDirectory();
			String fileName = fileDialog.getFile();

			File file;
			// Check if the File Dialog got the full file path
			if ((directory != null && !directory.isEmpty()) && (fileName != null && !fileName.isEmpty())) {
				file = new File(directory, fileName);
			} else {
				throw new IOException("Unable to open the file");
			}

			// Check if {file} points to an existing readable file instead of null
			if (file != null && file.exists() && file.isFile() && file.canRead()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				// Reading the first line
				if ((line = reader.readLine()) != null) {
					text.append(line);
				}
				
				// Reading the rest of the file
				while ((line = reader.readLine()) != null) {
					text.append("\n");
					text.append(line);
				}
				
				reader.close();
				
				// Check if a file is already opened
				if (openedFileAlreadyCheck()) {
					int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame, "You have unsaved changes. Do you want to save?",
							"Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						saveFile();
					}else if (result == JOptionPane.NO_OPTION) {
						// Don't save
					}else {
						return null;
					}
				}

				// Setting up the Text Area
				MainFrame.textArea.setEditable(true);
				MainFrame.textArea.setText(text.toString());
				MainFrame.showCaret();

				// Changing the window title
				GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - " + file.getName());

				// Saving false infos for later
				OpenedFile.file = file;
				OpenedFile.name = file.getName();
				OpenedFile.path = file.getAbsolutePath();
				OpenedFile.initialText = text.toString();

				// Initialize Text Area functionalities (Check for unsaved, etc...)
				CustomTextArea.init();

				return text.toString();
			} else {
				throw new IOException("Unable to open the file:\n\"" + OpenedFile.path + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method that saves a file
	public static void saveFile() {
		if (MainFrame.textArea.isEditable()) {
			// Getting the text written in the text Area
			String text = MainFrame.textArea.getText();
			if ((OpenedFile.initialText != null) && !OpenedFile.initialText.isEmpty()) {
				if (!text.equals(OpenedFile.initialText)) {
					try {
						File file = OpenedFile.file;
						if (file == null || !file.exists()) {
							// Creating the file if it does not exist
							FileDialog fileDialog = new FileDialog(GrammifyApplication.mainFrame, "Save File",
									FileDialog.SAVE);
							fileDialog.setFile(OpenedFile.name);
							fileDialog.setVisible(true);
							if (OpenedFile.path == null || OpenedFile.path.isEmpty()) {
								String directory = fileDialog.getDirectory();
								String fileName = fileDialog.getFile();

								if ((directory != null && !directory.isEmpty())
										&& (fileName != null && !fileName.isEmpty())) {
									OpenedFile.file = new File(directory, fileName);
									OpenedFile.initialText = "";
									OpenedFile.name = fileName;
									OpenedFile.path = OpenedFile.file.getAbsolutePath();
									file = OpenedFile.file;
								} else {
									throw new IOException("Unable to save the file");
								}

							} else {
								throw new IOException("Unable to save the file");
							}

							if (!file.exists())
								file.createNewFile();
						}
						if (file != null) {
							// Writing into the file
							if (file.exists() && file.canWrite()) {
								if (!OpenedFile.initialText.equals(text)) {
									BufferedWriter writer = new BufferedWriter(new FileWriter(file));
									writer.write(text);
									OpenedFile.initialText = text;
									writer.close();
								} else { // Debugging
									System.out.println("already saved !!");
								}
							} else {
								throw new IOException("Unable to save the file:\n\"" + OpenedFile.path + "\"");
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}
		}
	}
	
	// Method that checks if a file is already opened
	public static boolean openedFileAlreadyCheck () {
		return OpenedFile.file !=null;
	}
	
	// Method that opens a confirmation dialog
	public static void openConfirmationDialog () {
		// Setting the close operation
		GrammifyApplication.mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Open the confirmation dialog
		GrammifyApplication.mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame, "You have unsaved changes. Do you want to save?",
						"Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					saveFile();
					GrammifyApplication.mainFrame.dispose();
					System.exit(0);
				}else if (result == JOptionPane.NO_OPTION) {
					GrammifyApplication.mainFrame.dispose();
					System.exit(0);
				}
			}
		});
	}
	
	
}
