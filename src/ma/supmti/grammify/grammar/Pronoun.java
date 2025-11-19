package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains the logic of pronouns
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
	public static List<Pronoun> pronouns = new ArrayList<Pronoun>();
	private GrammaticalNumber grammaticalNumber;
	private GrammaticalGender grammaticalGender;
	private List<PronounsTypes> pronounType;
	// Elided version. ex: je -> j'
	private Pronoun elision;
	// Reference to full version if elided
	private Pronoun full;
	
	// Constructor for full versions
	public Pronoun(String text, GrammaticalNumber grammaticalNumber, GrammaticalGender grammaticalGender, PronounsTypes pronounType, String elision) {
		super(text, PartOfSpeech.PRONOUN);
		this.grammaticalNumber = grammaticalNumber;
		this.grammaticalGender = grammaticalGender;
		this.pronounType = new ArrayList<PronounsTypes>(Arrays.asList(new PronounsTypes[] {pronounType}));
		if (elision != null) {
			this.elision = new Pronoun (elision, grammaticalNumber, grammaticalGender, pronounType, null, this);
			words.add(this.elision);
		}
		this.full = null;
		pronouns.add(this);
		
	}
	// Constructor for elided versions
	public Pronoun(String text, GrammaticalNumber grammaticalNumber, GrammaticalGender grammaticalGender, PronounsTypes pronounType, String elision, Pronoun full) {
		super(text, PartOfSpeech.PRONOUN);
		this.grammaticalNumber = grammaticalNumber;
		this.grammaticalGender = grammaticalGender;
		this.pronounType = new ArrayList<PronounsTypes>(Arrays.asList(new PronounsTypes[] {pronounType}));
		this.elision = null;
		this.full = full;
		words.add(this.full);
		pronouns.add(this);
	}
	// Constructor for full versions
		public Pronoun(String text, GrammaticalNumber grammaticalNumber, GrammaticalGender grammaticalGender, List<PronounsTypes> pronounType, String elision) {
			super(text, PartOfSpeech.PRONOUN);
			this.grammaticalNumber = grammaticalNumber;
			this.grammaticalGender = grammaticalGender;
			this.pronounType = new ArrayList<PronounsTypes>(pronounType);
			if (elision != null) {
				this.elision = new Pronoun (elision, grammaticalNumber, grammaticalGender, pronounType, null, this);
				words.add(this.elision);
			}
			this.full = null;
			pronouns.add(this);
			
		}
		// Constructor for elided versions
		public Pronoun(String text, GrammaticalNumber grammaticalNumber, GrammaticalGender grammaticalGender, List<PronounsTypes> pronounType, String elision, Pronoun full) {
			super(text, PartOfSpeech.PRONOUN);
			this.grammaticalNumber = grammaticalNumber;
			this.grammaticalGender = grammaticalGender;
			this.pronounType = new ArrayList<PronounsTypes>(pronounType);
			this.elision = null;
			this.full = full;
			words.add(this.full);
			pronouns.add(this);
		}
	
	/**
	 * @return 
	 * (int) 0 -> if the pronoun is full and can't be elided; <br>
	 * (int) 1 -> if the pronoun is full and can be elided; <br>
	 * (int)-1 -> if the pronoun is elided and can be full;
	 * 
	 */
	public int canBeElidedOrFull () {
		if (full == null && elision == null) // The pronoun is full and can't be elided
			return 0;
		else if (full == null && elision != null) // The pronoun is full and can be elided
			return 1;
		else if (full != null && elision == null) // the pronoun is elided and can be full
			return -1;
		return 0;
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


	public Pronoun getElision() {
		return elision;
	}


	public void setElision(Pronoun elision) {
		this.elision = elision;
	}


	public Pronoun getFull() {
		return full;
	}

	public void setFull(Pronoun full) {
		this.full = full;
	}
	
	public List<PronounsTypes> getPronounType() {
		return pronounType;
	}
	
	public void setPronounType(List<PronounsTypes> pronounType) {
		this.pronounType = pronounType;
	}
	
	

}
