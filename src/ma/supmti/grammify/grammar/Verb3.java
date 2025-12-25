package ma.supmti.grammify.grammar;

import java.util.Arrays;
import java.util.List;

/**
 * Third Group verbs that end are irregular and have no single set of rules. For
 * example: "aller", "tenir", "faire"...
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-21 05:16
 */
public class Verb3 extends Verb{
	private Verb[] simplePresent = new Verb[6];
	private Verb[] imparfait = new Verb[6];
	private Verb[] futur = new Verb[6];
	private Verb[] simplePast = new Verb[6];
	private Verb[] pastParticiples;
	private Verb presentParticipal;

	private List<Auxiliary> auxiliaries;
	private Verb3Pattern pattern;
	private boolean pronominal;

	// Constructor
	public Verb3(String text, Verb3Pattern pattern, List<Auxiliary> auxiliaries, boolean pronominal) {
		super(text, null);
		this.pattern = pattern;
		this.auxiliaries = auxiliaries;
		this.pronominal = pronominal;

		buildConjugation();
	}

	private void buildConjugation() {
		switch (pattern) {
		case DORMIR -> buildDormir();
		case PARTIR -> buildPartir();
		case PRENDRE -> buildPrendre();
		case METTRE -> buildMettre();
		case DIRE -> buildDire();
		case VENIR -> buildVenir();
		case TENIR -> buildTenir();
		case VOIR -> buildVoir();
		case ALLER -> buildAller();
		case FAIRE -> buildFaire();
		}

	}

	private void buildTenir() {

	}

	private void buildFaire() {

	}

	private void buildAller() {
		String[] present = { "vais", "vas", "va", "allons", "allez", "vont" };
		String[] imparfaitForms = { "allais", "allais", "allait", "allions", "alliez", "allaient" };
		String[] futurForms = { "irai", "iras", "ira", "irons", "irez", "iront" };
		String[] simplePastForms = { "allai", "allas", "alla", "allâmes", "allâtes", "allèrent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this);
			imparfait[i] = new Verb(imparfaitForms[i], this);
			futur[i] = new Verb(futurForms[i], this);
			simplePast[i] = new Verb(simplePastForms[i], this);

			words.add(simplePresent[i]);
			words.add(imparfait[i]);
			words.add(futur[i]);
			words.add(simplePast[i]);
		}

		pastParticiples = new Verb[] { new Verb("allé", this), new Verb("allés", this), new Verb("allée", this),
				new Verb("allées", this) };
		words.addAll(Arrays.asList(pastParticiples));
		presentParticipal = new Verb("allant", this);
		words.add(presentParticipal);
	}

	private void buildVoir() {

	}

	private void buildVenir() {

	}

	private void buildDire() {

	}

	private void buildMettre() {

	}

	private void buildPrendre() {

	}

	private void buildPartir() {

	}

	private void buildDormir() {
		String stemShort = getText().substring(0, getText().length() - 3);
		String stemLong = getText().substring(0, getText().length() - 2);

		String[] pres = { stemShort + "s", stemShort + "s", stemShort + "t", stemLong + "ons", stemLong + "ez",
				stemLong + "ent" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "is", "is", "it", "îmes", "îtes", "irent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(pres[i], this);
			imparfait[i] = new Verb(stemLong + imparfaitSuffixes[i], this);
			futur[i] = new Verb(getText() + futurSuffixes[i], this);
			simplePast[i] = new Verb(stemLong + simplePastSuffixes[i], this);

			words.add(simplePresent[i]);
			words.add(imparfait[i]);
			words.add(futur[i]);
			words.add(simplePast[i]);
		}

		pastParticiples = new Verb[] { new Verb(stemLong + "i", this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb(stemLong + "ant", this);
		words.add(presentParticipal);
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

	public Verb[] getPastParticiples() {
		return pastParticiples;
	}

	public void setPastParticiples(Verb[] pastParticiples) {
		this.pastParticiples = pastParticiples;
	}

	public List<Auxiliary> getAuxiliaries() {
		return auxiliaries;
	}

	public void setAuxiliaries(List<Auxiliary> auxiliaries) {
		this.auxiliaries = auxiliaries;
	}

	public Verb3Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Verb3Pattern pattern) {
		this.pattern = pattern;
	}

	public boolean isPronominal() {
		return pronominal;
	}

	public void setPronominal(boolean pronominal) {
		this.pronominal = pronominal;
	}

	public Verb getPresentParticipal() {
		return presentParticipal;
	}

	public void setPresentParticipal(Verb presentParticipal) {
		this.presentParticipal = presentParticipal;
	}

}
