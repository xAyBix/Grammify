package ma.supmti.grammify.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public static String openFile(String path) {
        StringBuilder text = new StringBuilder();
        try {
        	File file = new File (path);
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

        		return text.toString();
        	}else {
        		throw new IOException("Unable to open the file:\n\"" + path + "\n");
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return null;
    }

    public static void saveFile(String text, String path) {
        try {
        	File file = new File (path);
        	if (file != null) {
        		// Creating the file if it does not exist
        		if (!file.exists()) {
        			file.createNewFile();
        		}
        		// Writing into the file
        		if (file.exists() && file.canWrite()) {
        			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        			writer.write(text);
        			writer.close();
        		}else {
        			throw new IOException("Unable to save the file:\n\"" + path + "\"");
        		}
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
