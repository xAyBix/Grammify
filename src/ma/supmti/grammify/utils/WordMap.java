package ma.supmti.grammify.utils;

import java.util.List;

import ma.supmti.grammify.grammar.Word;
import ma.supmti.grammify.grammar.detection.Parser;

/**
 * Contains words parsed from {@link Parser}
 * 
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-26 00:32
 */
public final class WordMap {
	
	private int index;
	private String text;
	private String state; // founded | not founded | ignored
	private List<Word> words;
	
	public WordMap (int index, String text, String state, List<Word> words) {
		this.index = index;
		this.text = text;
		this.state = state;
		this.words = words;
	}
	
	// Used to debug
	public String toString () {
		String w = "(";
		for (Word word : words) {
			w += word.getText() + "/" + word.getPartOfSpeech() + "\t";
		}
		w = w.substring(0, w.length()-1);
		w += ")";
		return index + "\t" + state + "\t" + w + "\n";
	} 

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
