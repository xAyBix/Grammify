package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains punctuation used in french
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-22 19:00
 */
public class Punctuation extends Word{
	public static List<Punctuation> punctuations = new ArrayList<>();
	
	private PunctuationTypes punctuationTypes;
	
	public Punctuation(String text, PunctuationTypes punctuationTypes) {
		super(text, PartOfSpeech.PUNCTUATION);
		this.punctuationTypes = punctuationTypes;
		punctuations.add(this);
	}

	public PunctuationTypes getPunctuationTypes() {
		return punctuationTypes;
	}
	public void setPunctuationTypes(PunctuationTypes punctuationTypes) {
		this.punctuationTypes = punctuationTypes;
	}
	
	
	
}
