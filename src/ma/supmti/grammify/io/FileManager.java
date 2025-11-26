package ma.supmti.grammify.io;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ma.supmti.grammify.GrammifyApplication;

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
	private FileManager () {}
	
	public static String openFile() {
		StringBuilder text = new StringBuilder();
		try {
			FileDialog fileDialog = new FileDialog(GrammifyApplication.mainFrame, "Open File", FileDialog.LOAD);
			fileDialog.setVisible(true);
			
			String directory = fileDialog.getDirectory();
	        String fileName = fileDialog.getFile();
			
			File file;
			if ((directory != null && !directory.isEmpty()) && (fileName != null && !fileName.isEmpty())) {
				file = new File(directory, fileName);
			}else {
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
				OpenedFile.file = file;
				OpenedFile.name = file.getName();
				OpenedFile.path = file.getAbsolutePath();
				OpenedFile.initialText = text.toString();

				reader.close();

				return text.toString();
			} else {
				throw new IOException("Unable to open the file:\n\"" + OpenedFile.path + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveFile(String text) {
		try {
			File file = OpenedFile.file;
			if (file == null || !file.exists()) {
				// Creating the file if it does not exist
				FileDialog fileDialog = new FileDialog(GrammifyApplication.mainFrame, "Save File", FileDialog.SAVE);
				fileDialog.setFile(OpenedFile.name);
				fileDialog.setVisible(true);
				if (OpenedFile.path == null || OpenedFile.path.isEmpty()) {
					String directory = fileDialog.getDirectory();
			        String fileName = fileDialog.getFile();
			        
			        if ((directory != null && !directory.isEmpty()) && (fileName != null && !fileName.isEmpty())) {
						OpenedFile.file = new File(directory, fileName);
						OpenedFile.initialText = "";
						OpenedFile.name = fileName;
						OpenedFile.path = OpenedFile.file.getAbsolutePath();
						file = OpenedFile.file;
					}else {
						throw new IOException("Unable to save the file");
					}
			        
				}else {
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
