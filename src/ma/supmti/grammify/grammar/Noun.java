package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the logic of nouns
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-12-17 11:45
 */
public class Noun extends Word{
	public static List<Noun> Nouns = new ArrayList<>();
	private Noun plural;
	private Noun singular;
	private GrammaticalNumber grammaticalNumber;
	private GrammaticalGender grammaticalGender;
	
	public Noun (String text, GrammaticalGender grammaticalGender, String pluralForm) {
		super(text, PartOfSpeech.NOUN);
		this.grammaticalGender = grammaticalGender;
		this.grammaticalNumber = GrammaticalNumber.SINGULAR;
		this.singular = null;
		if(!pluralForm.isEmpty()) {
			this.plural = new Noun(pluralForm, grammaticalGender, this);
		}else {
			this.plural = null;
		}
		
	}
	public Noun (String text, GrammaticalGender grammaticalGender, Noun singular) {
		super(text, PartOfSpeech.NOUN);
		this.grammaticalGender = grammaticalGender;
		this.grammaticalNumber = GrammaticalNumber.PLURAL;
		this.singular = singular;
		this.plural = null;
	}
	
	public boolean isPlural () {
		if (plural == null && singular != null) {
			return true;
		}
		return false;
	}
	
	public boolean canBePlural () {
		if (singular == null && plural != null) {
			return true;
		}else if (singular != null && plural == null) {
			return true;
		}
		return false;
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
	public Noun getPlural() {
		return plural;
	}
	public void setPlural(Noun plural) {
		this.plural = plural;
	}
	public Noun getSingular() {
		return singular;
	}
	public void setSingular(Noun singular) {
		this.singular = singular;
	}
	
	

}
