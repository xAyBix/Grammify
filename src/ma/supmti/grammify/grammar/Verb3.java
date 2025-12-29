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
public class Verb3 extends Verb {
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
		case VOULOIR -> buildVouloir();
		case VOIR -> buildVoir();
		case ALLER -> buildAller();
		case FAIRE -> buildFaire();
		case FALLOIR -> buildFalloir();
		}

	}

	private void buildFalloir() {
	    simplePresent[2] = new Verb("faut", this);
	    imparfait[2] = new Verb("fallait", this);
	    futur[2] = new Verb("faudra", this);
	    simplePast[2] = new Verb("fallut", this);
	    
	    words.add(simplePresent[2]);
	    words.add(imparfait[2]);
	    words.add(futur[2]);
	    words.add(simplePast[2]);

		pastParticiples = new Verb[] { new Verb("fallu", this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb("fallant", this);
		words.add(presentParticipal);
	}

	private void buildVouloir() {
		String[] present = { "veux", "veux", "veut", "voulons", "voulez", "veulent" };
		String[] imparfaitForms = { "voulais", "voulais", "voulait", "voulions", "vouliez", "voulaient" };
		String[] futurForms = { "voudrai", "voudras", "voudra", "voudrons", "voudrez", "voudront" };
		String[] simplePastForms = { "voulus", "voulus", "voulut", "voulûmes", "voulûtes", "voulurent" };

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

		pastParticiples = new Verb[] { new Verb("voulu", this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb("voulant", this);
		words.add(presentParticipal);
	}

	private void buildTenir() {
		String base = getText().substring(0, getText().length() - 4);

	    String tienStem   = base + "tien";
	    String tenStem    = base + "ten";
	    String futurStem  = base + "tiendr";
	    String pastStem   = base + "tin";
	    String pastPart   = base + "tenu";

		String[] present = { tienStem + "s", tienStem + "s", tienStem + "t", tenStem + "ons", tenStem + "ez",
				tienStem + "nent" };
		String[] imparfaitSuffixes = {
		        "ais", "ais", "ait", "ions", "iez", "aient"
		    };
		String[] futurSuffixes = {
		        "ai", "as", "a", "ons", "ez", "ont"
		    };
		String[] simplePastSuffixes = {
		        "s", "s", "t", "̂mes", "̂tes", "rent"
		    };

	    for (int i = 0; i < 6; i++) {
	        simplePresent[i] = new Verb(present[i], this);
	        imparfait[i] = new Verb(tenStem + imparfaitSuffixes[i], this);
	        futur[i] = new Verb(futurStem + futurSuffixes[i], this);
	        simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this);
	        
	        words.add(simplePresent[i]);
	        words.add(imparfait[i]);
	        words.add(futur[i]);
	        words.add(simplePast[i]);
	    }

	    
		pastParticiples = new Verb[] { new Verb(pastPart, this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb(tenStem + "ant", this);
		words.add(presentParticipal);
	}

	private void buildFaire() {
		String base = getText().substring(0, getText().length() - 4);

	    String faiStem   = base + "fai";
	    String faisStem  = base + "fais";
	    String ferStem   = base + "fer";
	    String pastStem  = base + "fi";
	    String pastPart  = base + "fait";

		String[] present = { faiStem + "s", faiStem + "s", faiStem + "t", faisStem + "ons", faisStem + "tes",
				base + "font" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this);
			imparfait[i] = new Verb(faisStem + imparfaitSuffixes[i], this);
			futur[i] = new Verb(ferStem + futurSuffixes[i], this);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this);

			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb(faisStem + "ant", this);
		words.add(presentParticipal);
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
		String base = getText().substring(0, getText().length() - 3);

		String voiStem = base + "voi";
		String voyStem = base + "voy";
		String futurStem = base + "verr";
		String pastStem = base + "vi";
		String pastPart = base + "vu";

		if (getText().equals("prévoir")) {
			futurStem = base + "voir";
		} else if (getText().equals("apercevoir")) {
			futurStem = "apercev";
		}

		String[] present = { voiStem + "s", voiStem + "s", voiStem + "t", voyStem + "ons", voyStem + "ez",
				voiStem + "ent" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this);
			imparfait[i] = new Verb(voyStem + imparfaitSuffixes[i], this);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this);

			words.add(simplePresent[i]);
			words.add(imparfait[i]);
			words.add(futur[i]);
			words.add(simplePast[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb(voyStem + "ant", this);
		words.add(presentParticipal);
	}

	private void buildVenir() {
		String base = getText().substring(0, getText().length() - 4);

	    String vienStem   = base + "vien";
	    String venStem    = base + "ven";
	    String futurStem  = base + "viendr";
	    String pastStem   = base + "vin";
	    String pastPart   = base + "venu";

		String[] present = { vienStem + "s", vienStem + "s", vienStem + "t", venStem + "ons", venStem + "ez",
				vienStem + "nent" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this);
			imparfait[i] = new Verb(venStem + imparfaitSuffixes[i], this);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this);

			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb(venStem + "ant", this);
		words.add(presentParticipal);
	}

	private void buildDire() {
		 String base = getText().substring(0, getText().length() - 4);

		    String diStem    = base + "di";
		    String futurStem = base + "dir";
		    String pastPart  = base + "dit";

			String[] present = { diStem + "s", diStem + "s", diStem + "t", diStem + "sons", diStem + "tes",
					diStem + "sent" };
			String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
			String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
			String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

			for (int i = 0; i < 6; i++) {
				simplePresent[i] = new Verb(present[i], this);
				imparfait[i] = new Verb(diStem + imparfaitSuffixes[i], this);
				futur[i] = new Verb(futurStem + futurSuffixes[i], this);
				simplePast[i] = new Verb(diStem + simplePastSuffixes[i], this);
				
				words.add(simplePast[i]);
				words.add(futur[i]);
				words.add(imparfait[i]);
				words.add(simplePresent[i]);
			}

			pastParticiples = new Verb[] { new Verb(pastPart, this) };
			words.add(pastParticiples[0]);
			presentParticipal = new Verb(diStem + "sant", this);
			words.add(presentParticipal);

	}

	private void buildMettre() {
		String base = getText().substring(0, getText().length() - 5);

	    String metStem    = base + "met";
	    String mettStem   = base + "mett";
	    String futurStem  = base + "mettr";
	    String pastStem   = base + "mi";
	    String pastPart   = base + "mis";

		String[] present = { metStem + "s", metStem + "s", metStem, metStem + "tons", metStem + "tez",
				mettStem + "ent" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this);
			imparfait[i] = new Verb(mettStem + imparfaitSuffixes[i], this);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this);

			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb(mettStem + "ant", this);
		words.add(presentParticipal);
	}

	private void buildPrendre() {
		String base = getText().substring(0, getText().length() - 6);

	    String prendStem = base + "prend";
	    String prenStem  = base + "pren";
	    String futurStem = prendStem;
	    String pastStem  = base + "pri";
	    String pastPart  = base + "pris";
	    
		String[] present = { prendStem + "s", prendStem + "s", prendStem, prenStem + "ons", prenStem + "ez",
				base + "prennent" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this);
			imparfait[i] = new Verb(prenStem + imparfaitSuffixes[i], this);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this);

			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb(prenStem + "ant", this);
		words.add(presentParticipal);
	}

	private void buildPartir() {
		String base = getText().substring(0, getText().length() - 2);

	    String shortStem = base.substring(0, base.length() - 1);
	    String fullStem  = base;
	    String futurStem = base + "ir";
	    String pastPart  = base + "i";

		String[] present = { shortStem + "s", shortStem + "s", shortStem + "t", fullStem + "ons", fullStem + "ez",
				shortStem + "ent" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "is", "is", "it", "îmes", "îtes", "irent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this);
			imparfait[i] = new Verb(fullStem + imparfaitSuffixes[i], this);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this);
			simplePast[i] = new Verb(fullStem + simplePastSuffixes[i], this);
			
			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this) };
		words.add(pastParticiples[0]);
		presentParticipal = new Verb(fullStem + "ant", this);
		words.add(presentParticipal);
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
