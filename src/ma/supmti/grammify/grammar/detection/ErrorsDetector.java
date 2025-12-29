package ma.supmti.grammify.grammar.detection;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.Highlighter.HighlightPainter;

import ma.supmti.grammify.grammar.Auxiliary;
import ma.supmti.grammify.grammar.Noun;
import ma.supmti.grammify.grammar.PartOfSpeech;
import ma.supmti.grammify.grammar.Pronoun;
import ma.supmti.grammify.grammar.PronounTypes;
import ma.supmti.grammify.grammar.Punctuation;
import ma.supmti.grammify.grammar.Verb;
import ma.supmti.grammify.grammar.Word;
import ma.supmti.grammify.io.OpenedFile;
import ma.supmti.grammify.ui.MainFrame;
import ma.supmti.grammify.ui.SuggestionsPopup;
import ma.supmti.grammify.utils.Error;
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
	private static List<Error> ignoredWords = new ArrayList<>();
	private static ExecutorService executorService;
	private static HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);

	// Starts a thread that searches constantly for errors in user inputs
	public static void init() {

		List<WordMap> words;
		if (Parser.pureTokens.size() > 0) {
			words = Parser.parse();
			// System.err.println("Loop entered");
			OpenedFile.errors.clear();
			// Errors Methods
			OpenedFile.errors.addAll(doubleSpacesErrorsCheck(words));
			OpenedFile.errors.addAll(spaceAfterNewLineErrorsCheck(words));
			OpenedFile.errors.addAll(firstCharacterUpperCaseErrorsCheck(words));
			OpenedFile.errors.addAll(spellingErrorsCheck(words));
			OpenedFile.errors.addAll(conjugationErrorsCheck(words));

			displayErrors(words);

			// Debugging
			OpenedFile.errors.forEach(e -> {
				// System.out.println(e.getErrorMessage() + " at " + e.getWordMap().getIndex());
				// System.out.println("did you mean: " + e.getAlternatives().get(0).getText() +
				// " at " + e.getWordMap().getIndex());
				// e.getAlternatives().forEach(ee -> System.out.println(e.getWordMap().getText()
				// + " and " +ee.getText()+ " " +
				// WordSuggestions.levenshtein(e.getWordMap().getText(),
				// WordSuggestions.normalize(ee.getText()))));

			});
		}
		// System.err.println("test");

	}

	private static void displayErrors(List<WordMap> words) {
		Highlighter highlighter = MainFrame.textArea.getHighlighter();
		for (Highlight h : highlighter.getHighlights()) {
			if (h.getPainter().equals(painter)) {
				highlighter.removeHighlight(h);
			}
		}

		if (!OpenedFile.errors.isEmpty()) {
			for (Error err : OpenedFile.errors) {
				int start = 0;
				int end = 0;
				for (WordMap wm : words) {
					if (wm.equals(err.getWordMap())) {
						break;
					}
					if (wm.getText().codePointAt(0) != 13) {
						start += wm.getText().length();
					}
				}
				end = start + err.getWordMap().getText().length();
				err.setStart(start);
				err.setEnd(end);
				try {
					highlighter.addHighlight(start, end, painter);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				MainFrame.textArea.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							int pos = MainFrame.textArea.viewToModel2D(e.getPoint());

							if (pos >= err.getStart() && pos <= err.getEnd()) {
								if (OpenedFile.errors.contains(err)) // Only shows the suggestion popup if there is an
																		// error
									SuggestionsPopup.showSuggestionsPopup(err, e.getX(), e.getY());
							}
						}
					}
				});
			}
		}
	}

	public static void addToIgnoredList(Error e) {
		ignoredWords.add(e);
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
						Word w = new Word(words.get(i).getWords().get(0).getText(), null);
						w.setText(String.valueOf(w.getText().charAt(0)).toUpperCase() + w.getText().substring(1));
						errors.add(new Error(words.get(i), errorMessage, Arrays.asList(new Word[] { w }))); // Fixing
																											// the
																											// uppercase
					}
				}
			} else if (words.get(i).getText().equals(".") || words.get(i).getText().equals("?")
					|| words.get(i).getText().equals("!") || words.get(i).getText().equals("...")) { // If there was a
																										// point
				if ((i + 1) < words.size() && words.get(i + 1).getText().equals(" ")) {
					if ((i + 2) < words.size()
							&& Pattern.compile("[a-zA-ZÀ-ÿ]+").matcher(words.get(i + 2).getText()).find()
							&& Character.isLowerCase(words.get(i + 2).getText().charAt(0))) {
						Word w = new Word(words.get(i + 2).getWords().get(0).getText(), null);
						w.setText(String.valueOf(w.getText().charAt(0)).toUpperCase() + w.getText().substring(1));
						errors.add(new Error(words.get(i + 2), errorMessage, Arrays.asList(new Word[] { w })));
					}
				} else if ((i + 1) < words.size()
						&& Pattern.compile("[a-zA-ZÀ-ÿ]+").matcher(words.get(i + 1).getText()).find()
						&& Character.isLowerCase(words.get(i + 1).getText().charAt(0))) {
					Word w = new Word(words.get(i + 1).getWords().get(0).getText(), null);
					w.setText(String.valueOf(w.getText().charAt(0)).toUpperCase() + w.getText().substring(1));
					errors.add(new Error(words.get(i + 1), errorMessage, Arrays.asList(new Word[] { w })));
				}
			} else if (words.get(i).getText().equals("\n")) { // If there a new line
				if ((i + 1) < words.size() && Pattern.compile("[a-zA-ZÀ-ÿ]+").matcher(words.get(i + 1).getText()).find()
						&& Character.isLowerCase(words.get(i + 1).getText().charAt(0))) {
					Word w = new Word(words.get(i + 1).getWords().get(0).getText(), null);
					w.setText(String.valueOf(w.getText().charAt(0)).toUpperCase() + w.getText().substring(1));
					errors.add(new Error(words.get(i + 1), errorMessage, Arrays.asList(new Word[] { w })));
				}
			}
		}

		return errors;
	}

	// Checks for spelling errors withing the words
	private static List<Error> spellingErrorsCheck(List<WordMap> words) {
		List<Error> errors = new ArrayList<>();
		String errorMessage = "Unfound word detected";
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).getState().equals("not founded")) {
				errors.add(new Error(words.get(i), errorMessage, WordSuggestions.suggest(words.get(i).getText())));
			}
		}
		return errors;
	}

	// Checks for grammar errors (respecting the gender, respecting the number...)
	private static List<Error> grammarErrorsCheck(List<WordMap> words) {
		List<Error> errors = new ArrayList<>();
		String genderErrorMessage = "Wrong gender detected";
		String numberErrorMessage = "Wrong number detected";

		for (int i = 0; i < words.size(); i++) {

		}

		return errors;
	}

	// Checks for conjugation errors
	private static List<Error> conjugationErrorsCheck(List<WordMap> words) {
		List<Error> errors = new ArrayList<>();
		String errorMessage = "Wrong conjugation detected";
		String noVerbErrorMessage = "No verb detected";
		int[] personalPronounsIndexes = { 0, 1, 2, 2, 3, 4, 5, 5 };
		int indexUsed = -1;
		Word subjectUsed = null;
		int subjectIndex = -1;
		Auxiliary auxiliaryUsed = null;
		int auxiliaryIndex = -1;
		Verb verbUsed = null;
		int verbIndex = -1;
		for (int i = 0; i < words.size(); i++) {
			List<Word> currentWord = words.get(i).getWords();
			if (subjectUsed == null) {
				for (int j = 0; j < currentWord.size(); j++) {
					if (currentWord.get(j).getPartOfSpeech().equals(PartOfSpeech.NOUN)) {
						subjectUsed = currentWord.get(j);
						subjectIndex = i;
					}else if (currentWord.get(j).getPartOfSpeech().equals(PartOfSpeech.PRONOUN)) {
						if (((Pronoun)currentWord.get(j)).getPronounType().contains(PronounTypes.PERSONAL)) {
							subjectUsed = currentWord.get(j);
							subjectIndex = i;
						}
					}
				}
			}
			if (verbUsed == null) {
				for (int j = 0; j < currentWord.size(); j++) {

				}
			}
			if (currentWord.get(0).getPartOfSpeech().equals(PartOfSpeech.PUNCTUATION)) {
				if (subjectUsed != null && subjectUsed.getPartOfSpeech().equals(PartOfSpeech.PRONOUN)
						&& ((Pronoun)subjectUsed).getPronounType().contains(PronounTypes.PERSONAL) && verbUsed != null) {
					errors.add(new Error(words.get(subjectIndex), noVerbErrorMessage, List.of()));
				}
				subjectUsed = null;
				subjectIndex = -1;
				auxiliaryUsed = null;
				auxiliaryIndex = -1;
				verbUsed = null;
				verbIndex = -1;
			}
			
		}

		return errors;
	}

	// Checks for the right use of punctuations
	private static List<Error> punctuationErrorsCheck(List<WordMap> words) {
		List<Error> errors = new ArrayList<>();
		String errorMessage = "Wrong punctuation detected";

		for (int i = 0; i < words.size(); i++) {

		}

		return errors;
	}

}
