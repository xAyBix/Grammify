package ma.supmti.grammify.grammar;

/**
 * 
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-17 02:23
 */
public class Pronoun extends Word{
	private GrammaticalNumber grammaticalNumber;
	private GrammaticalGender grammaticalGender;
	public Pronoun(String text, GrammaticalNumber grammaticalNumber, GrammaticalGender grammaticalGender) {
		super(text, PartOfSpeech.PRONOUN);
		this.grammaticalNumber = grammaticalNumber;
		this.grammaticalGender = grammaticalGender;
	}
	
	
	public GrammaticalNumber getGrammaticalNumber() {
		return grammaticalNumber;
	}
	
	
	public void setGrammaticalNumber(GrammaticalNumber grammaticalNumber) {
		this.grammaticalNumber = grammaticalNumber;
	}


	public GrammaticalGender getGrammaticalGender() {
		return grammaticalGender;
	}


	public void setGrammaticalGender(GrammaticalGender grammaticalGender) {
		this.grammaticalGender = grammaticalGender;
	}
	

}
