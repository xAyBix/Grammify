package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @param pureTokens is a list of Strings from a text that were tokenized
	 * 
	 * 
	 * 
	 * 
	 */
	public static Map<List<Word>, Map<String, Integer>> parse() {
		Map<List<Word>, Map<String, Integer>> parsedTokens = new HashMap<>();
		String currentToken;

		if (pureTokens.isEmpty())
			return null;
		while (true) {
			currentToken = peek();
			if (currentToken.equals(currentToken.toUpperCase())) { // Ignored token because it's written entirely in upper case 
				parsedTokens.put(Arrays.asList(new Word[] {new Word(currentToken, null)}),
						new HashMap<String, Integer>(Map.of(
								"ignored", currentIndex
								)));
			} else {
				List<Word> wordsFounded = Word.findByText(currentToken);
				if (wordsFounded.isEmpty()) { // No Equivalent found in the dictionary
					parsedTokens.put(Arrays.asList(new Word[] {new Word(currentToken, null)}),
							new HashMap<String, Integer>(Map.of(
									"not founded", currentIndex
									)));
				} else { // Token exists in dictionary
					parsedTokens.put(wordsFounded,
							new HashMap<String, Integer>(Map.of(
									"founded", currentIndex
									)));
				}
			}

			advance();
			if (currentIndex == pureTokens.size())
				break;
		}
		return parsedTokens;
	}

}
