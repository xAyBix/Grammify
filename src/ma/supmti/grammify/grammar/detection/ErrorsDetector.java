package ma.supmti.grammify.grammar.detection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import ma.supmti.grammify.grammar.Word;
import ma.supmti.grammify.io.OpenedFile;
import ma.supmti.grammify.utils.WordMap;

/**
 * Contains methods that identify various types of errors
 * 
 * 
 * 
 * 
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-26 17:17
 */
public final class ErrorsDetector {
	// A private constructor to avoid instantiation
	private ErrorsDetector() {
	}

	// A list of words ignored by the user
	private static List<String> ignoredWords = new ArrayList<>();
	private static ExecutorService executorService;

	// Starts a thread that searches constantly for errors in user inputs
	public static void init() {
		executorService = Executors.newFixedThreadPool(1);
		executorService.submit(() -> {
			while (true) {
				List<WordMap> words;
				if (Parser.pureTokens.size() > 0) {
					words = Parser.parse();
					// System.err.println("Loop entered");
					OpenedFile.errors.clear();
					// Errors Methods
					OpenedFile.errors.addAll(doubleSpacesErrorsCheck(words));
					OpenedFile.errors.addAll(spaceAfterNewLineErrorsCheck(words));
					OpenedFile.errors.addAll(firstCharacterUpperCaseErrorsCheck(words));

					// Debugging
					OpenedFile.errors.forEach(e -> {
						System.out.println(e.getErrorMessage() + " at " + e.getWordMap().getIndex());
					});
				}
				// System.err.println("test");

				try {
					Thread.sleep(2500); // Set to 1500 when debugging and 500 when not
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void addToIgnoredList(String text) {
		ignoredWords.add(text);
	}

	// Shutdown the executor
	public static void shutdown() {
		executorService.shutdown();
	}

	// Checks for double spaces
	private static List<Error> doubleSpacesErrorsCheck(List<WordMap> words) {
		List<Error> errors = new ArrayList<>();
		String errorMessage = "Double spaces detected";

		for (int i = 0; i < words.size(); i++) {
			if (i == words.size() - 1) {
				break;
			}
			String currentWord = words.get(i).getText();
			String nextWord = words.get(i + 1).getText();
			if (currentWord.equals(" ") && nextWord.equals(" ")) {
				errors.add(new Error(words.get(i), errorMessage, Arrays.asList(new Word[] { new Word("", null) })));
				errors.add(new Error(words.get(i + 1), errorMessage, Arrays.asList(new Word[] { new Word("", null) })));
			}
		}
		return errors;
	}

	// Checks for space after a new line
	private static List<Error> spaceAfterNewLineErrorsCheck(List<WordMap> words) {
		List<Error> errors = new ArrayList<>();
		String errorMessage = "Space after new line detected";

		for (int i = 0; i < words.size(); i++) {
			if (i == words.size() - 1) {
				break;
			}
			String currentWord = words.get(i).getText();
			String nextWord = words.get(i + 1).getText();
			if (currentWord.equals("\n") && nextWord.equals(" ")) {
				errors.add(new Error(words.get(i), errorMessage, Arrays.asList(new Word[] { new Word("", null) })));
				errors.add(new Error(words.get(i + 1), errorMessage, Arrays.asList(new Word[] { new Word("", null) })));
			}
		}
		return errors;
	}

	// Checks for charactercase errors
	private static List<Error> firstCharacterUpperCaseErrorsCheck(List<WordMap> words) {
		List<Error> errors = new ArrayList<>();
		String errorMessage = "Should start with Uppercase";

		for (int i = 0; i < words.size(); i++) {

			if (i == 0) { // Checking if the first word isn't a number and does start with Uppercase
				if (Pattern.compile("[a-zA-ZÀ-ÿ]+").matcher(words.get(i).getText()).find()) {
					if (Character.isLowerCase(words.get(i).getText().charAt(0))) {
						errors.add(new Error(words.get(i), errorMessage,
								Arrays.asList(new Word[] { new Word(
										String.valueOf(words.get(i).getWords().get(0).getText().charAt(0)).toUpperCase()
												+ words.get(i).getWords().get(0).getText().substring(1),
										null) }))); // Fixing the uppercase
					}
				}
			} else if (words.get(i).getText().equals(".")) { // If there was a point
				if ((i + 1) < words.size() && words.get(i + 1).getText().equals(" ")) {
					if ((i + 2) < words.size()
							&& Pattern.compile("[a-zA-ZÀ-ÿ]+").matcher(words.get(i + 2).getText()).find()
							&& Character.isLowerCase(words.get(i + 2).getText().charAt(0))) {
						// System.err.println(words.get(i + 2).getText().charAt(0)+" "+Character.isLowerCase(words.get(i + 2).getText().charAt(0))); 
						errors.add(new Error(words.get(i + 2), errorMessage, Arrays.asList(new Word[] { new Word(
								String.valueOf(words.get(i + 2).getWords().get(0).getText().charAt(0)).toUpperCase()
										+ words.get(i + 2).getWords().get(0).getText().substring(1),
								null) })));
					}
				} else if ((i + 1) < words.size()
						&& Pattern.compile("[a-zA-ZÀ-ÿ]+").matcher(words.get(i + 1).getText()).find()
						&& Character.isLowerCase(words.get(i + 1).getText().charAt(0))) {
					errors.add(new Error(words.get(i + 1), errorMessage,
							Arrays.asList(new Word[] { new Word(
									String.valueOf(words.get(i + 1).getWords().get(0).getText().charAt(0)).toUpperCase()
											+ words.get(i + 1).getWords().get(0).getText().substring(1),
									null) })));
				}
			} else if (words.get(i).getText().equals("\n")) { // If there a new line
				if ((i + 1) < words.size()
						&& Pattern.compile("[a-zA-ZÀ-ÿ]+").matcher(words.get(i + 1).getText()).find()
						&& Character.isLowerCase(words.get(i + 1).getText().charAt(0))) {
					errors.add(new Error(words.get(i + 1), errorMessage,
							Arrays.asList(new Word[] { new Word(
									String.valueOf(words.get(i + 1).getWords().get(0).getText().charAt(0)).toUpperCase()
											+ words.get(i + 1).getWords().get(0).getText().substring(1),
									null) })));
				}
			}
		}

		return errors;
	}
}
