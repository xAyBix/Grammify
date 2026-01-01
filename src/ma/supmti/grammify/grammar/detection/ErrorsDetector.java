package ma.supmti.grammify.grammar.detection;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.Highlighter.HighlightPainter;

import ma.supmti.grammify.grammar.Auxiliary;
import ma.supmti.grammify.grammar.PartOfSpeech;
import ma.supmti.grammify.grammar.Pronoun;
import ma.supmti.grammify.grammar.PronounTypes;
import ma.supmti.grammify.grammar.Punctuation;
import ma.supmti.grammify.grammar.PunctuationTypes;
import ma.supmti.grammify.grammar.Verb;
import ma.supmti.grammify.grammar.Verb1;
import ma.supmti.grammify.grammar.Verb2;
import ma.supmti.grammify.grammar.Verb3;
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
		String wrongAuxiliaryErrorMessage = "Wrong auxiliary used";
		String pastParticipleErrorMessage = "Verb must be a past participale";
		int[] personalPronounsIndexes = { 0, 1, 2, 2, 3, 4, 5, 5 };
		int indexUsed = -1;
		Word subjectUsed = null;
		int subjectIndex = -1;
		Word auxiliaryUsed = null;
		int auxiliaryIndex = -1;
		Word verbUsed = null;
		int verbIndex = -1;
		// add reflexive pronoun later
		for (int i = 0; i < words.size(); i++) {
			List<Word> currentWord = words.get(i).getWords();
			if (subjectUsed == null) {
				for (int j = 0; j < currentWord.size(); j++) {
					if (currentWord.get(j).getPartOfSpeech() == PartOfSpeech.NOUN) {
						subjectUsed = currentWord.get(j);
						subjectIndex = i;
					}else if (currentWord.get(j).getPartOfSpeech() == PartOfSpeech.PRONOUN) {
						if (((Pronoun)currentWord.get(j)).getPronounType().contains(PronounTypes.PERSONAL)) {
							subjectUsed = currentWord.get(j);
							subjectIndex = i;
						}
					}
				}
			}
			if (auxiliaryUsed == null || verbUsed == null) {
				for (int j = 0; j < currentWord.size(); j++) {
					if (currentWord.get(j).getPartOfSpeech() == PartOfSpeech.VERB) {
						if (!((Verb)currentWord.get(j)).isInfinitive()) {
							String vrbTxt = ((Verb)currentWord.get(j)).getInfinitive().getText();
							if (auxiliaryUsed == null && (vrbTxt.equals("avoir") || vrbTxt.equals("être"))) {
								auxiliaryUsed = currentWord.get(j);
								auxiliaryIndex = i;
							} else{
								verbUsed = currentWord.get(j);
								verbIndex = i;
							}
						}
					}
				}
			}
			if (currentWord.get(0).getPartOfSpeech() == PartOfSpeech.PUNCTUATION) {
				if (((Punctuation)currentWord.get(0)).getPunctuationTypes() != PunctuationTypes.WHITE_SPACE && 
						((Punctuation)currentWord.get(0)).getPunctuationTypes() != PunctuationTypes.LEFT_PARENTHESE &&
						((Punctuation)currentWord.get(0)).getPunctuationTypes() != PunctuationTypes.RIGHT_PARENTHESE &&
						((Punctuation)currentWord.get(0)).getPunctuationTypes() != PunctuationTypes.APOSTROPHE) {
					// Checks for verb used with pronouns
					if (subjectUsed != null && subjectUsed.getPartOfSpeech() == PartOfSpeech.PRONOUN
							&& ((Pronoun)subjectUsed).getPronounType().contains(PronounTypes.PERSONAL) && (verbUsed == null && auxiliaryUsed == null)) {
						errors.add(new Error(words.get(subjectIndex), noVerbErrorMessage, List.of()));
					} else {
						if (subjectUsed != null && subjectUsed.getPartOfSpeech() == PartOfSpeech.PRONOUN
								&& ((Pronoun)subjectUsed).getPronounType().contains(PronounTypes.PERSONAL) && (verbUsed != null && auxiliaryUsed == null)) {
							Verb infVerb = ((Verb)verbUsed).getInfinitive();
							String subj = subjectUsed.getText();
							Verb[] conjugationTime;
							List<Word> suggestions = new ArrayList<>();
							if (infVerb.getGroup() == 1) {
								conjugationTime = ((Verb1)infVerb).getConjugationTime(verbUsed.getText());
							}else if (infVerb.getGroup() == 2) {
								conjugationTime = ((Verb2)infVerb).getConjugationTime(verbUsed.getText());
							}else {
								conjugationTime = ((Verb3)infVerb).getConjugationTime(verbUsed.getText());
							}
							switch (subj) {
								case "je": {
									if (!verbUsed.getText().equals(conjugationTime[0].getText())) {
										suggestions.add(conjugationTime[0]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								case "j": {
									if (!verbUsed.getText().equals(conjugationTime[0].getText())) {
										suggestions.add(conjugationTime[0]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								case "tu": {
									if (!verbUsed.getText().equals(conjugationTime[1].getText())) {
										suggestions.add(conjugationTime[1]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								case "il": {
									if (!verbUsed.getText().equals(conjugationTime[2].getText())) {
										suggestions.add(conjugationTime[2]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								case "elle": {
									if (!verbUsed.getText().equals(conjugationTime[2].getText())) {
										suggestions.add(conjugationTime[2]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								case "nous": {
									if (!verbUsed.getText().equals(conjugationTime[3].getText())) {
										suggestions.add(conjugationTime[3]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								case "vous": {
									if (!verbUsed.getText().equals(conjugationTime[4].getText())) {
										suggestions.add(conjugationTime[4]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								case "ils": {
									if (!verbUsed.getText().equals(conjugationTime[5].getText())) {
										suggestions.add(conjugationTime[5]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								case "elles": {
									if (!verbUsed.getText().equals(conjugationTime[5].getText())) {
										suggestions.add(conjugationTime[5]);
										errors.add(new Error(words.get(verbIndex), errorMessage, suggestions));
									}
									break;
								}
								
							}
							
						}else if (subjectUsed != null && subjectUsed.getPartOfSpeech() == PartOfSpeech.PRONOUN
								&& ((Pronoun)subjectUsed).getPronounType().contains(PronounTypes.PERSONAL) && (verbUsed == null && auxiliaryUsed != null)) {
							Verb infVerb = ((Verb)auxiliaryUsed).getInfinitive();
							String subj = subjectUsed.getText();
							Verb[] conjugationTime;
							List<Word> suggestions = new ArrayList<>();
							conjugationTime = ((Auxiliary)infVerb).getConjugationTime(auxiliaryUsed.getText());
							switch (subj) {
								case "je": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[0].getText())) {
										suggestions.add(conjugationTime[0]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								case "j": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[0].getText())) {
										suggestions.add(conjugationTime[0]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								case "tu": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[1].getText())) {
										suggestions.add(conjugationTime[1]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								case "il": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[2].getText())) {
										suggestions.add(conjugationTime[2]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								case "elle": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[2].getText())) {
										suggestions.add(conjugationTime[2]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								case "nous": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[3].getText())) {
										suggestions.add(conjugationTime[3]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								case "vous": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[4].getText())) {
										suggestions.add(conjugationTime[4]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								case "ils": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[5].getText())) {
										suggestions.add(conjugationTime[5]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								case "elles": {
									if (!auxiliaryUsed.getText().equals(conjugationTime[5].getText())) {
										suggestions.add(conjugationTime[5]);
										errors.add(new Error(words.get(auxiliaryIndex), errorMessage, suggestions));
									}
									break;
								}
								
							}
						}else if (subjectUsed != null && subjectUsed.getPartOfSpeech() == PartOfSpeech.PRONOUN
								&& ((Pronoun)subjectUsed).getPronounType().contains(PronounTypes.PERSONAL) && (verbUsed != null && auxiliaryUsed != null)) {
							Verb infAux = ((Verb)auxiliaryUsed).getInfinitive();
							Verb infVerb = ((Verb)verbUsed).getInfinitive();
							String subj = subjectUsed.getText();
							Verb[] conjugationTime = ((Auxiliary)infAux).getConjugationTime(auxiliaryUsed.getText());
							Verb correctAuxiliary = null;
							
							
							switch (subj) {
							case "je": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[0]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[0]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[0]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[0]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[0]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								break;
							}
							case "j": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[0]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[0]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[0]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[0]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[0]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[0]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[0]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[0]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[0]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								
								break;
							}
							case "tu": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[1]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[1]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[1]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[1]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[1]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[1]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[1]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[1]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[1]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[1]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[1]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[1]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[1]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[1]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[1]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[1]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[1]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[1]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[1]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[1]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[1]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[1]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[1]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[1]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								
								break;
							}
							case "il": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[2]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[2]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[2]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[2]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[2]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								break;
							}
							case "elle": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[2]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[2]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[2]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[2]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[2]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[2]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[2]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[2]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[2]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								break;
							}
							case "nous": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[3]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[3]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[3]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[3]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[3]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[3]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[3]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[3]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[3]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[3]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[3]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[3]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[3]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[3]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[3]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[3]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[3]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[3]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[3]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[3]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[3]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[3]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[3]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[3]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								
								break;
							}
							case "vous": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[4]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[4]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[4]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[4]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[4]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[4]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[4]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[4]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[4]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[4]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[4]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[4]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[4]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[4]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[4]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[4]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[4]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[4]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[4]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[4]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[4]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[4]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[4]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[4]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								break;
							}
							case "ils": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[5]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[5]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[5]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[5]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[5]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								break;
							}
							case "elles": {
								if (infVerb.getGroup() == 0) {
									if (!infAux.getText().equals("avoir")) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[5]; 
											break;
										}
										case "étais": {
											correctAuxiliary = Auxiliary.getAvoir().getImparfait()[5]; 
											break;
										}
										case "serai": {
											correctAuxiliary = Auxiliary.getAvoir().getFutur()[5]; 
											break;
										}
										case "fus": {
											correctAuxiliary = Auxiliary.getAvoir().getSimplePast()[5]; 
											break;
										}
										default:
											correctAuxiliary = Auxiliary.getAvoir().getSimplePresent()[5]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Auxiliary)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Auxiliary)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 1) {
									int index = 0;
									for (Auxiliary a : ((Verb1)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										// Error use the right auxiliary
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb1)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb1)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb1)infVerb).getPastParticiples()[0]})));
									}
								}else if (infVerb.getGroup() == 2) {
									int index = 0;
									for (Auxiliary a : ((Verb2)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb2)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
										
									}else if (!verbUsed.getText().equals(((Verb2)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb2)infVerb).getPastParticiples()[0]})));
									}
									
								}else if (infVerb.getGroup() == 3) {
									int index = 0;
									for (Auxiliary a : ((Verb3)infVerb).getAuxiliaries()) {
										if (infAux.getText().equals(a.getText())) {
											index=1;
											break;
										}
									}
									if (index == 0 ) {
										switch (conjugationTime[0].getText()) {
										case "suis":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "ai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										case "étais": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "avais":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getImparfait()[5]; 
											break;
										}
										case "serai": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "aurai":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getFutur()[5]; 
											break;
										}
										case "fus": {
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										case "eus":{
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePast()[5]; 
											break;
										}
										default:
											correctAuxiliary = ((Verb3)infVerb).getAuxiliaries().get(0).getSimplePresent()[5]; 
											break;
										}
										errors.add(new Error(words.get(auxiliaryIndex), wrongAuxiliaryErrorMessage, Arrays.asList(new Verb[] {correctAuxiliary})));
									}else if (!verbUsed.getText().equals(((Verb3)infVerb).getPastParticiples()[0].getText())) {
										errors.add(new Error(words.get(verbIndex), pastParticipleErrorMessage, Arrays.asList(new Verb[] {((Verb3)infVerb).getPastParticiples()[0]})));
									}
								}
								break;
							}

							}
							
						}
					}
					subjectUsed = null;
					subjectIndex = -1;
					auxiliaryUsed = null;
					auxiliaryIndex = -1;
					verbUsed = null;
					verbIndex = -1;
				}
				
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
