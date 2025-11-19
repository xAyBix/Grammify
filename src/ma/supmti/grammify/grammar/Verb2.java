package ma.supmti.grammify.grammar;

import java.util.List;

/**
 * First Group verbs that end with "ir" and have a simple conjugation. For
 * example: "finir", "réfléchir"...
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-19 15:13
 */
public class Verb2 extends Verb {
	public static final String[] SIMPLE_PRESENT_SUFFIXES = { "is", "is", "it", "issons", "issez", "issent" };
	public static final String[] IMPARFAIT_SUFFIXES = { "issais", "issais", "issait", "issions", "issiez", "issaient" };
	public static final String[] FUTUR_SUFFIXES = { "irai", "iras", "ira", "irons", "irez", "iront" };
	public static final String[] SIMPLE_PAST_SUFFIXES = { "is", "is", "it", "îmes", "îtes", "irent" };
	public static final String[] PAST_PARTICIPAL_SUFFIXES = { "i", "is", "ie", "ies" };
	public static final String PRESENT_PARTICIPAL_SUFFIX = "issant";

	// Conjugations
	private Verb[] simplePresent;
	private Verb[] imparfait;
	private Verb[] futur;
	private Verb[] simplePast;
	private Verb[] pastParticipals;
	private Verb presentParticipal;
	private String radical;

	// Indicates which auxiliaries the verb uses when conjugates
	private List<Auxiliary> auxiliaries;

	// Indicates if the verb can be written as "Je me suis <verb>"
	private boolean pronominal;

	// Constructor
	public Verb2(String text, List<Auxiliary> auxiliaries, boolean pronominal) {
		super(text, null);

		this.auxiliaries = auxiliaries;
		this.pronominal = pronominal;
		simplePresent = new Verb[SIMPLE_PRESENT_SUFFIXES.length];
		imparfait = new Verb[IMPARFAIT_SUFFIXES.length];
		futur = new Verb[FUTUR_SUFFIXES.length];
		simplePast = new Verb[SIMPLE_PAST_SUFFIXES.length];
		pastParticipals = new Verb[PAST_PARTICIPAL_SUFFIXES.length];
		
		if (text.endsWith("ir")) {
			this.radical = text.substring(0, text.length()-2);
		}

	}

	public Verb[] getSimplePresent() {
		return simplePresent;
	}

	public void setSimplePresent(Verb[] simplePresent) {
		this.simplePresent = simplePresent;
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

	public String getRadical() {
		return radical;
	}

	public void setRadical(String radical) {
		this.radical = radical;
	}

	public List<Auxiliary> getAuxiliaries() {
		return auxiliaries;
	}

	public void setAuxiliaries(List<Auxiliary> auxiliaries) {
		this.auxiliaries = auxiliaries;
	}

	public boolean isPronominal() {
		return pronominal;
	}

	public void setPronominal(boolean pronominal) {
		this.pronominal = pronominal;
	}
	
	

}
