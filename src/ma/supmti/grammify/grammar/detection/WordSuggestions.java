package ma.supmti.grammify.grammar.detection;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;

import ma.supmti.grammify.grammar.Word;

/**
 * A class that suggest words from dictionary that are closer to the words that
 * are not found
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-12-14 21:51
 */
public class WordSuggestions {

	public static List<Word> suggest(String word) {
		String normalizedWord = normalize(word);

		return Word.words.stream()
				.sorted(
						Comparator.comparingInt(
								w->levenshtein(normalizedWord, normalize(w.getText())))
						)
				.limit(5)
				.toList();
	}

	private static String normalize(String word) {
		return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	private static int levenshtein(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		// Create a 2D array to store the dynamic programming results
		int[][] dp = new int[m + 1][n + 1];

		// Initialize the base cases
		for (int i = 0; i <= m; i++) {
			dp[i][0] = i;
		}

		for (int j = 0; j <= n; j++) {
			dp[0][j] = j;
		}

		// Fill in the DP array using the recurrence relation
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					// Characters match, no operation needed
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					// Characters don't match, consider the minimum of insert, remove, and replace
					dp[i][j] = 1 + Math.min(
							// Insert
							dp[i][j - 1], Math.min(
									// Remove
									dp[i - 1][j],
									// Replace
									dp[i - 1][j - 1]));
				}
			}
		}

		// Result is stored in the bottom-right cell of the DP array
		return dp[m][n];
	}

}
