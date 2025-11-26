package ma.supmti.grammify.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all currently opened file infos needed
 * 
 * 
 * 
 * @author Rihab AMEUR
 * 
 * @since 2025-11-21 11:27
 */
public final class OpenedFile {
	// A private constructor to avoid instantiation
	private OpenedFile () {}
	
	public static File file;
	public static String name;
	public static String path;
	public static String initialText;
	
	public static List<ma.supmti.grammify.grammar.detection.Error> errors = new ArrayList<>();
}
