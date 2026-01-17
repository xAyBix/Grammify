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
	    // Check if a file is already opened FIRST
	    if (openedFileAlreadyCheck() && MainFrame.textArea != null) {
	        if (OpenedFile.initialText == null && !MainFrame.textArea.getText().isEmpty()) {
	            int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame,
	                    "You have unsaved changes. Do you want to save?", "Unsaved Changes",
	                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	            if (result == JOptionPane.YES_OPTION) {
	                saveFile();
	            } else if (result == JOptionPane.NO_OPTION) {
	                // Don't save
	            } else {
	                return;
	            }
	        } else if (OpenedFile.initialText != null && !OpenedFile.initialText.equals(MainFrame.textArea.getText())) {
	            int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame,
	                    "You have unsaved changes. Do you want to save?", "Unsaved Changes",
	                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	            if (result == JOptionPane.YES_OPTION) {
	                saveFile();
	            } else if (result == JOptionPane.NO_OPTION) {
	                // Don't save
	            } else {
	                return;
	            }
	        }
	    }

	    // Setting the close operation - MOVE THIS HERE
	    openConfirmationDialog();

	    String fileName = "untitled.txt";
	    MainFrame.textArea.setEditable(true);
	    MainFrame.textArea.setText("");
	    MainFrame.showCaret();

	    // Changing the window title
	    GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - *" + fileName);

	    // Saving false infos for later
	    OpenedFile.file = null;
	    OpenedFile.name = fileName;
	    OpenedFile.path = null;
	    OpenedFile.initialText = "";

	    // Initialize Text Area functionalities (Check for unsaved, etc...)
	    CustomTextArea.init();
	}

	// Method that opens a file
	public static String openFile() {
	    try {
	        // Show file dialog and get selected file
	        File file = selectFileFromDialog();
	        if (file == null) {
	            return null; // User cancelled
	        }

	        // Check for unsaved changes before opening new file
	        if (!handleUnsavedChanges()) {
	            return null; // User cancelled
	        }

	        // Read file content
	        String fileContent = readFileContent(file);
	        // Show confirmation dialog
	        openConfirmationDialog();

	        // Update UI and state
	        updateUIWithOpenedFile(file, fileContent);

	        // Initialize Text Area functionalities
	        CustomTextArea.init();

	        return fileContent;

	    } catch (IOException e) {
	        showErrorDialog("Error Opening File", "Unable to open the file:\n" + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * Shows a file dialog and returns the selected file
	 * @return Selected file or null if cancelled
	 * @throws IOException if file selection fails
	 */
	private static File selectFileFromDialog() throws IOException {
	    FileDialog fileDialog = new FileDialog(GrammifyApplication.mainFrame, "Open File", FileDialog.LOAD);
	    fileDialog.setVisible(true);

	    String directory = fileDialog.getDirectory();
	    String fileName = fileDialog.getFile();

	    // User cancelled the dialog
	    if (directory == null || fileName == null) {
	        return null;
	    }

	    File file = new File(directory, fileName);

	    // Validate file
	    if (!file.exists()) {
	        throw new IOException("File does not exist: " + file.getAbsolutePath());
	    }
	    if (!file.isFile()) {
	        throw new IOException("Not a file: " + file.getAbsolutePath());
	    }
	    if (!file.canRead()) {
	        throw new IOException("Cannot read file: " + file.getAbsolutePath());
	    }

	    return file;
	}

	/**
	 * Checks for unsaved changes and prompts user to save if needed
	 * @return true if we should continue, false if user cancelled
	 */
	private static boolean handleUnsavedChanges() {
	    if (!openedFileAlreadyCheck() || MainFrame.textArea == null) {
	        return true; // No file open, continue
	    }

	    String currentText = MainFrame.textArea.getText();
	    String savedText = OpenedFile.initialText != null ? OpenedFile.initialText : "";

	    // No changes, continue
	    if (savedText.equals(currentText)) {
	        return true;
	    }

	    // Prompt user about unsaved changes
	    int result = JOptionPane.showConfirmDialog(
	            GrammifyApplication.mainFrame,
	            "You have unsaved changes. Do you want to save?",
	            "Unsaved Changes",
	            JOptionPane.YES_NO_CANCEL_OPTION,
	            JOptionPane.WARNING_MESSAGE
	    );

	    if (result == JOptionPane.YES_OPTION) {
	        saveFile();
	        return true;
	    } else if (result == JOptionPane.NO_OPTION) {
	        return true; // Don't save, continue
	    } else {
	        return false; // Cancel
	    }
	}

	/**
	 * Reads the entire content of a file
	 * @param file File to read
	 * @return File content as String
	 * @throws IOException if reading fails
	 */
	private static String readFileContent(File file) throws IOException {
	    StringBuilder content = new StringBuilder();
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line = reader.readLine();
	        
	        // Read first line without newline
	        if (line != null) {
	            content.append(line);
	        }
	        
	        // Read remaining lines with newlines
	        while ((line = reader.readLine()) != null) {
	            content.append("\n").append(line);
	        }
	    }
	    
	    return content.toString();
	}

	/**
	 * Updates the UI and OpenedFile state with the newly opened file
	 * @param file Opened file
	 * @param content File content
	 */
	private static void updateUIWithOpenedFile(File file, String content) {
	    OpenedFile.file = file;
	    OpenedFile.name = file.getName();
	    OpenedFile.path = file.getAbsolutePath();
	    OpenedFile.initialText = content;

	    GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - " + file.getName());

	    // Set loading flag
	    CustomTextArea.isLoadingFile = true;
	    
	    try {
	        MainFrame.textArea.setEditable(true);
	        MainFrame.textArea.setText(content);
	        MainFrame.showCaret();
	    } finally {
	        CustomTextArea.isLoadingFile = false;
	    }
	}

	/**
	 * Shows an error dialog to the user
	 * @param title Dialog title
	 * @param message Error message
	 */
	private static void showErrorDialog(String title, String message) {
	    JOptionPane.showMessageDialog(
	            GrammifyApplication.mainFrame,
	            message,
	            title,
	            JOptionPane.ERROR_MESSAGE
	    );
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

			} else {
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

	// Method that checks if a file is already opened
	public static boolean openedFileAlreadyCheck() {
		return OpenedFile.name != null;
	}

	// Method that opens a confirmation dialog
	public static void openConfirmationDialog() {
		// Setting the close operation
		GrammifyApplication.mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Open the confirmation dialog
		GrammifyApplication.mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (openedFileAlreadyCheck() && MainFrame.textArea != null) {
					if (OpenedFile.initialText == null) {
						int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame,
								"You have unsaved changes. Do you want to save?", "Unsaved Changes",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							saveFile();
							GrammifyApplication.mainFrame.dispose();
							System.exit(0);
						} else if (result == JOptionPane.NO_OPTION) {
							GrammifyApplication.mainFrame.dispose();
							System.exit(0);
						}
					}else if (OpenedFile.file == null) {
						int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame,
								"You have unsaved changes. Do you want to save?", "Unsaved Changes",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							saveFile();
							GrammifyApplication.mainFrame.dispose();
							System.exit(0);
						} else if (result == JOptionPane.NO_OPTION) {
							GrammifyApplication.mainFrame.dispose();
							System.exit(0);
						}
					}else if (!(MainFrame.textArea.getText().equals(OpenedFile.initialText))) {
						int result = JOptionPane.showConfirmDialog(GrammifyApplication.mainFrame,
								"You have unsaved changes. Do you want to save?", "Unsaved Changes",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							saveFile();
							GrammifyApplication.mainFrame.dispose();
							System.exit(0);
						} else if (result == JOptionPane.NO_OPTION) {
							GrammifyApplication.mainFrame.dispose();
							System.exit(0);
						}
					}
				}
			}
		});
	}

}
