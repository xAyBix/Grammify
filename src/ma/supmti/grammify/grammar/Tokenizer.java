package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains methods that convert a text to tokens
 * 
 * 
 * 
 * 
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-24 09:40
 */
public final class Tokenizer {
	// A private constructor to avoid instantiation
	private Tokenizer () {}
	
	public static List<String> tokenize (String text) {
		List<String> tokens = new ArrayList<>();
		// Tokenize using regex
		Pattern pattern = Pattern.compile(
				getEllisionsAsRegex()+"[]+" // Regex for Ellisions
				+ "|"
				);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			
		}
		
		return tokens;
	}
	
	private static String getEllisionsAsRegex () {
		StringBuilder ellisions = new StringBuilder("(");
		for (Pronoun pronoun : Pronoun.pronouns) {
			if (pronoun.canBeElidedOrFull()==-1) {
				ellisions.append(pronoun.getText());
				ellisions.append("|");
			}
		}
		ellisions.deleteCharAt(ellisions.length()-1);
		ellisions.append(")");
		
		return ellisions.toString();
	}
}
