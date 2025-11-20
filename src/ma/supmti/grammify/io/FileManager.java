package ma.supmti.grammify.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

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

    public static String open(String path) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
            reader.close();
            return text.toString();
        } catch (Exception e) {
        }
        return null;
    }

    public static void save(String text, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(text);
            writer.close();
        } catch (Exception e) {
        }

    }
}
