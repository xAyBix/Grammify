package ma.supmti.grammify.grammar.detection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	public static void init (List<WordMap> words) {
		executorService = Executors.newFixedThreadPool(1);
		executorService.submit(() -> {
			while (true) {
				
				// Errors Methods
				OpenedFile.errors.addAll(doubleSpacesErrorsCheck(words));
				
				// Debugging
//				OpenedFile.errors.forEach(e -> {
//					System.out.println(e.getErrorMessage());
//				});
//				System.out.println("test");
				
				try {
					Thread.sleep(1500);
				}catch (InterruptedException e) {
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
		
		for (int i = 0; i<words.size(); i++) {
			if (i == words.size()-1) {
				break;
			}
			Word currentWord = words.get(i).getWords().get(0);
			Word nextWord = words.get(i+1).getWords().get(0);
			if (currentWord.getText().equals(" ") && nextWord.getText().equals(" ")) {
				errors.add(new Error(words.get(i), errorMessage, Arrays.asList(new Word[] {new Word("", null)})));
				errors.add(new Error(words.get(i+1), errorMessage, Arrays.asList(new Word[] {new Word("", null)})));
			}
		}
		return errors;
	}
}
