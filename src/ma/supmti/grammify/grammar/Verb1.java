package ma.supmti.grammify.grammar;

import java.util.List;
import java.util.stream.Stream;

/**
 * First Group verbs that end with "er" and have a simple
 * conjugation. For example: "manger", "parler"...
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-17 03:17
 */
public class Verb1 extends Verb{
	public static final String[] SIMPLE_PRESENT_SUFFIXES = {"e", "es", "e", "ons", "ez", "ent"};
	public static final String[] PAST_PARTICIPAL_SUFFIXES = {"é", "és", "ée", "ées"};
	public static final String PRESENT_PARTICIPAL_SUFFIX = "ant";
	
	// Conjugations
	private Verb[] simplePresent;
	private Verb[] pastParticipals;
	private Verb presentParticipal;
	private String radical;
	
	// Indicates which auxiliaries the verb uses when conjugates
	private List<Auxiliary> auxiliaries;
	
	// Indicates if the verb can be written as "Je me suis <verb>"
	private boolean pronominal;
	
	// Constructor
	public Verb1(String text, List<Auxiliary> auxiliaries, boolean pronominal) {
		super(text, null);
		
		this.auxiliaries = auxiliaries;
		this.pronominal = pronominal;
		simplePresent = new Verb[SIMPLE_PRESENT_SUFFIXES.length];
		pastParticipals = new Verb[PAST_PARTICIPAL_SUFFIXES.length];
		
		if (text.endsWith("er")) {
			this.radical = text.substring(0, text.length()-2);
		}
		// Initializing simple present
		if (radical != null && radical.endsWith("g")) {
			for (int i = 0 ; i < SIMPLE_PRESENT_SUFFIXES.length ; i++) {
				if (SIMPLE_PRESENT_SUFFIXES[i].equals("ons")) {
					simplePresent[i] = new Verb(radical + "e" + SIMPLE_PRESENT_SUFFIXES[i], this);
				}else {
					simplePresent[i] = new Verb(radical + SIMPLE_PRESENT_SUFFIXES[i], this);
				}
				words.add(simplePresent[i]);
			}
		}else if (radical != null && radical.endsWith("c")) {
			for (int i = 0 ; i < SIMPLE_PRESENT_SUFFIXES.length ; i++) {
				if (SIMPLE_PRESENT_SUFFIXES[i].equals("ons")) {
					simplePresent[i] = new Verb((radical.substring(0, radical.length()-1) + "ç" + SIMPLE_PRESENT_SUFFIXES[i]), this);
				}else {
					simplePresent[i] = new Verb(radical + SIMPLE_PRESENT_SUFFIXES[i], this);
				}
				words.add(simplePresent[i]);
			}
		}else if (radical != null && radical.endsWith("y")) {
			for (int i = 0 ; i < SIMPLE_PRESENT_SUFFIXES.length ; i++) {
				if (SIMPLE_PRESENT_SUFFIXES[i].equals("ons") || SIMPLE_PRESENT_SUFFIXES[i].equals("ez")) {
					simplePresent[i] = new Verb(radical + SIMPLE_PRESENT_SUFFIXES[i], this);
				}else {
					simplePresent[i] = new Verb((radical.substring(0, radical.length()-1) + "i" + SIMPLE_PRESENT_SUFFIXES[i]), this);
				}
				words.add(simplePresent[i]);	
			}
		}else if (radical != null) {
			for (int i = 0 ; i < SIMPLE_PRESENT_SUFFIXES.length ; i++) {
				simplePresent[i] = new Verb(radical + SIMPLE_PRESENT_SUFFIXES[i], this);
				words.add(simplePresent[i]);
			}
		}
		
		// Initializing past participal
		for (int i = 0 ; i < PAST_PARTICIPAL_SUFFIXES.length ; i++) {
			pastParticipals [i] = new Verb(radical + PAST_PARTICIPAL_SUFFIXES[i], this);
			words.add(pastParticipals[i]);
		}
		
		// Initializing present participal
		presentParticipal = new Verb(radical + PRESENT_PARTICIPAL_SUFFIX, this);
		words.add(presentParticipal);
	}

	
	
	public boolean isPronominal() {
		return pronominal;
	}

	public void setPronominal(boolean pronominal) {
		this.pronominal = pronominal;
	}

	public String getRadical() {
		return radical;
	}
	
	public void setRadical(String radical) {
		this.radical = radical;
	}

	public Verb[] getSimplePresent() {
		return simplePresent;
	}

	public void setSimplePresent(Verb[] simplePresent) {
		this.simplePresent = simplePresent;
	}

	public Verb[] getPastParticipals() {
		return pastParticipals;
	}

	public void setPastParticipals(Verb[] pastParticipals) {
		this.pastParticipals = pastParticipals;
	}

	public Verb getPresentParticipal() {
		return presentParticipal;
	}

	public void setPresentParticipal(Verb presentParticipal) {
		this.presentParticipal = presentParticipal;
	}

	public List<Auxiliary> getAuxiliaries() {
		return auxiliaries;
	}

	public void setAuxiliaries(List<Auxiliary> auxiliaries) {
		this.auxiliaries = auxiliaries;
	}
	
	

}
