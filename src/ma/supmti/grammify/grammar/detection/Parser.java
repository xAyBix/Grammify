package ma.supmti.grammify.grammar.detection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import ma.supmti.grammify.grammar.Word;
import ma.supmti.grammify.utils.WordMap;

/**
 * A token parser that converts strings into words
 * 
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-21 22:39
 */
public final class Parser {
	// A private constructor to avoid instantiation
	private Parser() {
	}

	// Contains pure tokenized text
	public static List<String> pureTokens = new ArrayList<>();
	private static int currentIndex = 0;

	// Helper methods to itterate {pureTokens}
	private static String peek() {
		return pureTokens.get(currentIndex);
	}

	private static void advance() {
		currentIndex++;
	}

	/**
	 * <p>
	 * A method that check converts each String to Word if it was in the dictionary
	 * {Word.words}
	 * 
	 * @return Map that contains word, state (founded | not founded | ignored), index.
	 * 
	 * 
	 * 
	 * 
	 */
	public static List<WordMap> parse() {
		List<WordMap> parsedTokens = new ArrayList<>();
		String currentToken;

		if (pureTokens.isEmpty())
			return parsedTokens; // null earlier
		while (true) {
			currentToken = peek();
			if ((Pattern.compile("[a-zA-ZÀ-ÿ]+").matcher(currentToken).find() || Pattern.compile("[0-9]+").matcher(currentToken).find()) &&
					currentToken.equals(currentToken.toUpperCase()) && currentToken.length() > 1) { // Ignored token because it's written entirely in upper case 
				parsedTokens.add(new WordMap(currentIndex, currentToken, "ignored", Arrays.asList(new Word[] {new Word(currentToken, null)})));
			} else {
				List<Word> wordsFounded = Word.findByText(currentToken);
				if (wordsFounded.isEmpty()) { // No Equivalent found in the dictionary
					parsedTokens.add(new WordMap(currentIndex, currentToken, "not founded", Arrays.asList(new Word[] {new Word(currentToken, null)})));
				} else { // Token exists in dictionary
					parsedTokens.add(new WordMap(currentIndex, currentToken, "founded", wordsFounded));
				}
			}

			advance();
			if (currentIndex == pureTokens.size()) {
				currentIndex = 0;
				break;
			}
		}
		return parsedTokens;
	}

}
