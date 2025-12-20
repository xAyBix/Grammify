package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the logic of Determiners
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-12-20 01:39
 */
public class Determiner extends Word{
	public static List<Determiner> determiners = new ArrayList<>();
	private GrammaticalGender grammaticalGender;
	private GrammaticalNumber grammaticalNumber;
	private DeterminerTypes determinerType;
	public Determiner(String text, GrammaticalNumber grammaticalNumber, GrammaticalGender grammaticalGender, DeterminerTypes determinerType) {
		super(text, PartOfSpeech.DETERMINER);
		this.determinerType = determinerType;
		this.grammaticalGender = grammaticalGender;
		this.grammaticalNumber = grammaticalNumber;
	}
	public GrammaticalGender getGrammaticalGender() {
		return grammaticalGender;
	}
	public void setGrammaticalGender(GrammaticalGender grammaticalGender) {
		this.grammaticalGender = grammaticalGender;
	}
	public GrammaticalNumber getGrammaticalNumber() {
		return grammaticalNumber;
	}
	public void setGrammaticalNumber(GrammaticalNumber grammaticalNumber) {
		this.grammaticalNumber = grammaticalNumber;
	}
	public DeterminerTypes getDeterminerType() {
		return determinerType;
	}
	public void setDeterminerType(DeterminerTypes determinerType) {
		this.determinerType = determinerType;
	}
	

}
