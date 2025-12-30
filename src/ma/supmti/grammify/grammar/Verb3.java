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
	private Verb presentParticiple;

	private List<Auxiliary> auxiliaries;
	private Verb3Pattern pattern;
	private boolean pronominal;

	// Constructor
	public Verb3(String text, Verb3Pattern pattern, List<Auxiliary> auxiliaries, boolean pronominal) {
		super(text, null, 3);
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
	    simplePresent[2] = new Verb("faut", this, 3);
	    imparfait[2] = new Verb("fallait", this, 3);
	    futur[2] = new Verb("faudra", this, 3);
	    simplePast[2] = new Verb("fallut", this, 3);
	    
	    words.add(simplePresent[2]);
	    words.add(imparfait[2]);
	    words.add(futur[2]);
	    words.add(simplePast[2]);

		pastParticiples = new Verb[] { new Verb("fallu", this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb("fallant", this, 3);
		words.add(presentParticiple);
	}

	private void buildVouloir() {
		String[] present = { "veux", "veux", "veut", "voulons", "voulez", "veulent" };
		String[] imparfaitForms = { "voulais", "voulais", "voulait", "voulions", "vouliez", "voulaient" };
		String[] futurForms = { "voudrai", "voudras", "voudra", "voudrons", "voudrez", "voudront" };
		String[] simplePastForms = { "voulus", "voulus", "voulut", "voulûmes", "voulûtes", "voulurent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this, 3);
			imparfait[i] = new Verb(imparfaitForms[i], this, 3);
			futur[i] = new Verb(futurForms[i], this, 3);
			simplePast[i] = new Verb(simplePastForms[i], this, 3);

			words.add(simplePresent[i]);
			words.add(imparfait[i]);
			words.add(futur[i]);
			words.add(simplePast[i]);
		}

		pastParticiples = new Verb[] { new Verb("voulu", this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb("voulant", this, 3);
		words.add(presentParticiple);
	}

	private void buildTenir() {
		String base = getText().substring(0, getText().length() - 4);

	    String tienStem   = base + "ien";
	    String tenStem    = base + "en";
	    String futurStem  = base + "iendr";
	    String pastStem   = base + "in";
	    String pastPart   = base + "enu";

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
	        simplePresent[i] = new Verb(present[i], this, 3);
	        imparfait[i] = new Verb(tenStem + imparfaitSuffixes[i], this, 3);
	        futur[i] = new Verb(futurStem + futurSuffixes[i], this, 3);
	        simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this, 3);
	        
	        words.add(simplePresent[i]);
	        words.add(imparfait[i]);
	        words.add(futur[i]);
	        words.add(simplePast[i]);
	    }

	    
		pastParticiples = new Verb[] { new Verb(pastPart, this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb(tenStem + "ant", this, 3);
		words.add(presentParticiple);
	}

	private void buildFaire() {
		String base = getText().substring(0, getText().length() - 4);

	    String faiStem   = base + "ai";
	    String faisStem  = base + "ais";
	    String ferStem   = base + "er";
	    String pastStem  = base + "i";
	    String pastPart  = base + "ait";

		String[] present = { faiStem + "s", faiStem + "s", faiStem + "t", faisStem + "ons", faisStem + "tes",
				base + "font" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this, 3);
			imparfait[i] = new Verb(faisStem + imparfaitSuffixes[i], this, 3);
			futur[i] = new Verb(ferStem + futurSuffixes[i], this, 3);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this, 3);

			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb(faisStem + "ant", this, 3);
		words.add(presentParticiple);
	}

	private void buildAller() {
		String[] present = { "vais", "vas", "va", "allons", "allez", "vont" };
		String[] imparfaitForms = { "allais", "allais", "allait", "allions", "alliez", "allaient" };
		String[] futurForms = { "irai", "iras", "ira", "irons", "irez", "iront" };
		String[] simplePastForms = { "allai", "allas", "alla", "allâmes", "allâtes", "allèrent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this, 3);
			imparfait[i] = new Verb(imparfaitForms[i], this, 3);
			futur[i] = new Verb(futurForms[i], this, 3);
			simplePast[i] = new Verb(simplePastForms[i], this, 3);

			words.add(simplePresent[i]);
			words.add(imparfait[i]);
			words.add(futur[i]);
			words.add(simplePast[i]);
		}

		pastParticiples = new Verb[] { new Verb("allé", this, 3), new Verb("allés", this, 3), new Verb("allée", this, 3),
				new Verb("allées", this, 3) };
		words.addAll(Arrays.asList(pastParticiples));
		presentParticiple = new Verb("allant", this, 3);
		words.add(presentParticiple);
	}

	private void buildVoir() {
		String base = getText().substring(0, getText().length() - 3);

		String voiStem = base + "oi";
		String voyStem = base + "oy";
		String futurStem = base + "err";
		String pastStem = base + "i";
		String pastPart = base + "u";

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
			simplePresent[i] = new Verb(present[i], this, 3);
			imparfait[i] = new Verb(voyStem + imparfaitSuffixes[i], this, 3);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this, 3);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this, 3);

			words.add(simplePresent[i]);
			words.add(imparfait[i]);
			words.add(futur[i]);
			words.add(simplePast[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb(voyStem + "ant", this, 3);
		words.add(presentParticiple);
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
			simplePresent[i] = new Verb(present[i], this, 3);
			imparfait[i] = new Verb(venStem + imparfaitSuffixes[i], this, 3);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this, 3);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this, 3);

			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb(venStem + "ant", this, 3);
		words.add(presentParticiple);
	}

	private void buildDire() {
		 String base = getText().substring(0, getText().length() - 4);

		    String diStem    = base + "i";
		    String futurStem = base + "ir";
		    String pastPart  = base + "it";

			String[] present = { diStem + "s", diStem + "s", diStem + "t", diStem + "sons", diStem + "tes",
					diStem + "sent" };
			String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
			String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
			String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

			for (int i = 0; i < 6; i++) {
				simplePresent[i] = new Verb(present[i], this, 3);
				imparfait[i] = new Verb(diStem + imparfaitSuffixes[i], this, 3);
				futur[i] = new Verb(futurStem + futurSuffixes[i], this, 3);
				simplePast[i] = new Verb(diStem + simplePastSuffixes[i], this, 3);
				
				words.add(simplePast[i]);
				words.add(futur[i]);
				words.add(imparfait[i]);
				words.add(simplePresent[i]);
			}

			pastParticiples = new Verb[] { new Verb(pastPart, this, 3) };
			words.add(pastParticiples[0]);
			presentParticiple = new Verb(diStem + "sant", this, 3);
			words.add(presentParticiple);

	}

	private void buildMettre() {
		String base = getText().substring(0, getText().length() - 5);

	    String metStem    = base + "et";
	    String mettStem   = base + "ett";
	    String futurStem  = base + "ettr";
	    String pastStem   = base + "i";
	    String pastPart   = base + "is";

		String[] present = { metStem + "s", metStem + "s", metStem, metStem + "tons", metStem + "tez",
				mettStem + "ent" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this, 3);
			imparfait[i] = new Verb(mettStem + imparfaitSuffixes[i], this, 3);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this, 3);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this, 3);

			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb(mettStem + "ant", this, 3);
		words.add(presentParticiple);
	}

	private void buildPrendre() {
		String base = getText().substring(0, getText().length() - 6);

	    String prendStem = base + "rend";
	    String prenStem  = base + "ren";
	    String futurStem = prendStem;
	    String pastStem  = base + "ri";
	    String pastPart  = base + "ris";
	    
		String[] present = { prendStem + "s", prendStem + "s", prendStem, prenStem + "ons", prenStem + "ez",
				base + "prennent" };
		String[] imparfaitSuffixes = { "ais", "ais", "ait", "ions", "iez", "aient" };
		String[] futurSuffixes = { "ai", "as", "a", "ons", "ez", "ont" };
		String[] simplePastSuffixes = { "s", "s", "t", "̂mes", "̂tes", "rent" };

		for (int i = 0; i < 6; i++) {
			simplePresent[i] = new Verb(present[i], this, 3);
			imparfait[i] = new Verb(prenStem + imparfaitSuffixes[i], this, 3);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this, 3);
			simplePast[i] = new Verb(pastStem + simplePastSuffixes[i], this, 3);

			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb(prenStem + "ant", this, 3);
		words.add(presentParticiple);
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
			simplePresent[i] = new Verb(present[i], this, 3);
			imparfait[i] = new Verb(fullStem + imparfaitSuffixes[i], this, 3);
			futur[i] = new Verb(futurStem + futurSuffixes[i], this, 3);
			simplePast[i] = new Verb(fullStem + simplePastSuffixes[i], this, 3);
			
			words.add(simplePast[i]);
			words.add(futur[i]);
			words.add(imparfait[i]);
			words.add(simplePresent[i]);
		}

		pastParticiples = new Verb[] { new Verb(pastPart, this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb(fullStem + "ant", this, 3);
		words.add(presentParticiple);
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
			simplePresent[i] = new Verb(pres[i], this, 3);
			imparfait[i] = new Verb(stemLong + imparfaitSuffixes[i], this, 3);
			futur[i] = new Verb(getText() + futurSuffixes[i], this, 3);
			simplePast[i] = new Verb(stemLong + simplePastSuffixes[i], this, 3);

			words.add(simplePresent[i]);
			words.add(imparfait[i]);
			words.add(futur[i]);
			words.add(simplePast[i]);
		}

		pastParticiples = new Verb[] { new Verb(stemLong + "i", this, 3) };
		words.add(pastParticiples[0]);
		presentParticiple = new Verb(stemLong + "ant", this, 3);
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

	public Verb getPresentParticiple() {
		return presentParticiple;
	}

	public void setPresentParticiple(Verb presentParticiple) {
		this.presentParticiple = presentParticiple;
	}

}
