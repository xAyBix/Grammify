package ma.supmti.grammify.utils;

import java.util.List;

import ma.supmti.grammify.grammar.Word;

/**
 * 
 * 
 * 
 * 
 * 
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-26 18:08
 */
public final class Error {
	private WordMap wordMap;
	private String errorMessage;
	private List<Word> alternatives;
	private boolean ignored;
	private int start;
	private int end;
	
	public Error(WordMap wordMap, String errorMessage, List<Word> alternatives) {
		this.wordMap = wordMap;
		this.errorMessage = errorMessage;
		this.alternatives = alternatives;
		this.start = -1;
		this.end = -1;
		this.ignored = false;
	}

	public WordMap getWordMap() {
		return wordMap;
	}

	public void setWordMap(WordMap wordMap) {
		this.wordMap = wordMap;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Word> getAlternatives() {
		return alternatives;
	}

	public void setAlternatives(List<Word> alternatives) {
		this.alternatives = alternatives;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isIgnored() {
		return ignored;
	}

	public void setIgnored(boolean ignored) {
		this.ignored = ignored;
	}
	
	
	
	

}
