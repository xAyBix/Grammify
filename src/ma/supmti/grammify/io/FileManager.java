package ma.supmti.grammify.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

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
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
			File file;
			int returnValue = fileChooser.showOpenDialog(GrammifyApplication.mainFrame);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				fileChooser.setDialogTitle("Open File");
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				file = fileChooser.getSelectedFile();
				
			}else {
				throw new IOException("Unable to open the file");
			}
			file = fileChooser.getSelectedFile();
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
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
				if (OpenedFile.path == null || OpenedFile.path.isEmpty()) {
					int returnValue = fileChooser.showSaveDialog(GrammifyApplication.mainFrame);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						fileChooser.setDialogTitle("Select File");
						fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
						file = fileChooser.getSelectedFile();
						
					}
				}else {
					throw new IOException("Unable to save the file");
				}
				file.createNewFile();
			}
			if (file != null) {
				// Writing into the file
				if (file.exists() && file.canWrite()) {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write(text);
					OpenedFile.initialText = text;
					writer.close();
				} else {
					throw new IOException("Unable to save the file:\n\"" + OpenedFile.path + "\"");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
