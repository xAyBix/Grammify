package ma.supmti.grammify.grammar;

import java.util.List;

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
	public static final String[] IMPARFAIT_SUFFIXES = {"ais", "ais", "ait", "ions", "iez", "aient"};
	public static final String[] FUTUR_SUFFIXES = {"erai", "eras", "era", "erons", "erez", "eront"};
	public static final String[] SIMPLE_PAST_SUFFIXES = {"ai", "as", "a", "âmes", "âtes", "èrent"};
	public static final String[] PAST_PARTICIPLE_SUFFIXES = {"é", "és", "ée", "ées"};
	public static final String PRESENT_PARTICIPLE_SUFFIX = "ant";
	
	// Conjugations
	private Verb[] simplePresent;
	private Verb[] imparfait;
	private Verb[] futur;
	private Verb[] simplePast;
	private Verb[] pastParticiples;
	private Verb presentParticiple;
	private String radical;
	
	// Indicates which auxiliaries the verb uses when conjugates
	private List<Auxiliary> auxiliaries;
	
	// Indicates if the verb can be written as "Je me suis <verb>"
	private boolean pronominal;
	
	// Constructor
	public Verb1(String text, List<Auxiliary> auxiliaries, boolean pronominal) {
		super(text, null, 1);
		
		this.auxiliaries = auxiliaries;
		this.pronominal = pronominal;
		simplePresent = new Verb[SIMPLE_PRESENT_SUFFIXES.length];
		imparfait = new Verb[IMPARFAIT_SUFFIXES.length];
		futur = new Verb[FUTUR_SUFFIXES.length];
		simplePast = new Verb[SIMPLE_PAST_SUFFIXES.length];
		pastParticiples = new Verb[PAST_PARTICIPLE_SUFFIXES.length];
		
		if (text.endsWith("er")) {
			this.radical = text.substring(0, text.length()-2);
		}
		// Initializing simple present, imparfait, futur and simple past
		if (radical != null && radical.endsWith("g")) {
			for (int i = 0 ; i < SIMPLE_PRESENT_SUFFIXES.length ; i++) {
				// Simple present
				if (SIMPLE_PRESENT_SUFFIXES[i].equals("ons")) {
					simplePresent[i] = new Verb(radical + "e" + SIMPLE_PRESENT_SUFFIXES[i], this, 1);
				}else {
					simplePresent[i] = new Verb(radical + SIMPLE_PRESENT_SUFFIXES[i], this, 1);
				}
				// Imparfait
				if (IMPARFAIT_SUFFIXES[i].equals("ions") || IMPARFAIT_SUFFIXES[i].equals("iez")) {
					imparfait[i] = new Verb(radical + IMPARFAIT_SUFFIXES[i], this, 1);
				}else {
					imparfait[i] = new Verb(radical + "e" + IMPARFAIT_SUFFIXES[i], this, 1);
				}
				// Futur
				futur[i] = new Verb(radical + FUTUR_SUFFIXES[i], this, 1);
				// Simple past
				if (SIMPLE_PAST_SUFFIXES[i].equals("èrent")) {
					simplePast[i] = new Verb(radical + SIMPLE_PAST_SUFFIXES[i], this, 1);
				}else {
					simplePast[i] = new Verb(radical + "e" + SIMPLE_PAST_SUFFIXES[i], this, 1);
				}
				
				words.add(simplePresent[i]);
				words.add(imparfait[i]);
				words.add(futur[i]);
				words.add(simplePast[i]);
			}
		}else if (radical != null && radical.endsWith("c")) {
			for (int i = 0 ; i < SIMPLE_PRESENT_SUFFIXES.length ; i++) {
				// Simple present
				if (SIMPLE_PRESENT_SUFFIXES[i].equals("ons")) {
					simplePresent[i] = new Verb((radical.substring(0, radical.length()-1) + "ç" + SIMPLE_PRESENT_SUFFIXES[i]), this, 1);
				}else {
					simplePresent[i] = new Verb(radical + SIMPLE_PRESENT_SUFFIXES[i], this, 1);
				}
				// Imparfait
				if (IMPARFAIT_SUFFIXES[i].equals("ions") || IMPARFAIT_SUFFIXES[i].equals("iez")) {
					imparfait[i] = new Verb(radical + IMPARFAIT_SUFFIXES[i], this, 1);
				}else {
					imparfait[i] = new Verb(radical.substring(0, radical.length()-1) + "ç" + IMPARFAIT_SUFFIXES[i], this, 1);
				}
				// Futur
				futur[i] = new Verb(radical + FUTUR_SUFFIXES[i], this, 1);
				// Simple past
				if (SIMPLE_PAST_SUFFIXES[i].equals("èrent")) {
					simplePast[i] = new Verb(radical + SIMPLE_PAST_SUFFIXES[i], this, 1);
				}else {
					simplePast[i] = new Verb(radical.substring(0, radical.length()-1) + "ç" + SIMPLE_PAST_SUFFIXES[i], this, 1);
				}
				
				words.add(simplePresent[i]);
				words.add(imparfait[i]);
				words.add(futur[i]);
				words.add(simplePast[i]);
			}
		}else if (radical != null && radical.endsWith("y")) {// To fix
			for (int i = 0 ; i < SIMPLE_PRESENT_SUFFIXES.length ; i++) {
				// Simple present
				if (SIMPLE_PRESENT_SUFFIXES[i].equals("ons") || SIMPLE_PRESENT_SUFFIXES[i].equals("ez")) {
					simplePresent[i] = new Verb(radical + SIMPLE_PRESENT_SUFFIXES[i], this, 1);
				}else {
					simplePresent[i] = new Verb((radical.substring(0, radical.length()-1) + "i" + SIMPLE_PRESENT_SUFFIXES[i]), this, 1);
				}
				// Imparfait
				imparfait[i] = new Verb(radical + IMPARFAIT_SUFFIXES[i], this, 1);
				// Futur
				futur[i] = new Verb(radical.substring(0, radical.length()-1) + "i" + FUTUR_SUFFIXES[i], this, 1);
				// Simple past
				simplePast[i] = new Verb(radical + SIMPLE_PAST_SUFFIXES[i], this, 1);
				
				words.add(simplePresent[i]);
				words.add(imparfait[i]);
				words.add(futur[i]);
				words.add(simplePast[i]);
			}
		}else if (radical != null) {
			for (int i = 0 ; i < SIMPLE_PRESENT_SUFFIXES.length ; i++) {
				simplePresent[i] = new Verb(radical + SIMPLE_PRESENT_SUFFIXES[i], this, 1);
				imparfait[i] = new Verb(radical + IMPARFAIT_SUFFIXES[i], this, 1);
				futur[i] = new Verb(radical + FUTUR_SUFFIXES[i], this, 1);
				simplePast[i] = new Verb(radical + SIMPLE_PAST_SUFFIXES[i], this, 1);
				
				words.add(simplePresent[i]);
				words.add(imparfait[i]);
				words.add(futur[i]);
				words.add(simplePast[i]);
			}
		}
		
		// Initializing past PARTICIPLE
		for (int i = 0 ; i < PAST_PARTICIPLE_SUFFIXES.length ; i++) {
			pastParticiples [i] = new Verb(radical + PAST_PARTICIPLE_SUFFIXES[i], this, 1);
			words.add(pastParticiples[i]);
		}
		
		// Initializing present PARTICIPLE
		presentParticiple = new Verb(radical + PRESENT_PARTICIPLE_SUFFIX, this, 1);
		words.add(presentParticiple);
	}

	
	public Verb[] getConjugationTime(String verb) {
		for (Verb v : simplePresent) {
			if (v.getText().equals(verb))
				return simplePresent;
		}
		for (Verb v : simplePast) {
			if (v.getText().equals(verb))
				return simplePast;
		}
		for (Verb v : futur) {
			if (v.getText().equals(verb))
				return futur;
		}
		for (Verb v : imparfait) {
			if (v.getText().equals(verb))
				return imparfait;
		}
		return null;
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

	public Verb[] getPastParticiples() {
		return pastParticiples;
	}

	public void setPastParticiples(Verb[] pastParticiples) {
		this.pastParticiples = pastParticiples;
	}

	public Verb getPresentParticiple() {
		return presentParticiple;
	}

	public void setPresentParticiple(Verb presentParticiple) {
		this.presentParticiple = presentParticiple;
	}

	public List<Auxiliary> getAuxiliaries() {
		return auxiliaries;
	}

	public void setAuxiliaries(List<Auxiliary> auxiliaries) {
		this.auxiliaries = auxiliaries;
	}

	public Verb[] getImparfait() {
		return imparfait;
	}

	public void setImparfait(Verb[] imparfait) {
		this.imparfait = imparfait;
	}

	public Verb[] getFutur() {
		return futur;
	}

	public void setFutur(Verb[] futur) {
		this.futur = futur;
	}

	public Verb[] getSimplePast() {
		return simplePast;
	}
	
	public void setSimplePast(Verb[] simplePast) {
		this.simplePast = simplePast;
	}
	
	
}
