package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.Iterator;
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
	private Tokenizer() {
	}

	public static List<String> tokenize(String text) {
		List<String> tokens = new ArrayList<>();
		// Tokenize using regex
		Pattern pattern = Pattern.compile(getEllisionsAsRegex() + "|" + // Contractions
				"[a-zA-ZÀ-ÿ]+|" + // Words
				"[0-9]+(?:\\.[0-9]+)?|" + // Numbers
				"[-.,!?;:\"\']|" + // Punctuation
				"\\s+|" + // SPACES as tokens
				"\\n+"
		);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String token = new String(matcher.group());

			if (token.endsWith("\'") && !token.equals("\'")) {
				// Split the word with the punctuation
				tokens.add(token.substring(0, token.length() - 1));
				tokens.add("\'");
				continue;
			}
			if (token.endsWith("\"") && !token.equals("\"")) {
				// Split the word with the punctuation
				tokens.add(token.substring(0, token.length() - 1));
				tokens.add("\"");
				continue;
			}
			if (token.endsWith(")") && !token.equals(")")) {
				// Split the word with the punctuation
				tokens.add(token.substring(0, token.length() - 1));
				tokens.add(")");
				continue;
			}
			if (token.startsWith("(") && !token.equals("(")) {
				// Split the word with the punctuation
				tokens.add("(");
				tokens.add(token.substring(1, token.length()));
				continue;
			}
			if (token.startsWith("\"") && !token.equals("\"")) {
				// Split the word with the punctuation
				tokens.add("\"");
				tokens.add(token.substring(1, token.length()));
				continue;
			}
			if (token.startsWith("-") && !token.equals("-")) {
				// Split the word with the punctuation
				tokens.add("-");
				tokens.add(token.substring(0, token.length() - 1));
				continue;
			}

			tokens.add(token);

		}
		
		// Removing empty string
		Iterator<String> i = tokens.iterator();
		while (i.hasNext()) {
			String tmp = i.next();
			if (tmp.isEmpty())
				i.remove();
		}

		return tokens;
	}

	// A helper method to write ellisions as a regex
	private static String getEllisionsAsRegex() {
		StringBuilder ellisions = new StringBuilder("(?:");
		for (Pronoun pronoun : Pronoun.pronouns) {
			if (pronoun.canBeElidedOrFull() == -1) {
				ellisions.append(pronoun.getText());
				ellisions.append("'|");
			}
		}
		ellisions.deleteCharAt(ellisions.length() - 1);
		ellisions.append(")");

		return ellisions.toString();
	}
}
