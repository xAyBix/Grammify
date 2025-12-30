package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains auxiliaries "être" and "avoir"
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-17 09:47
 */
public class Auxiliary extends Verb{
	
	public static final List<Auxiliary> auxiliaries = new ArrayList<Auxiliary>(
			Arrays.asList(new Auxiliary[] {
					new Auxiliary("avoir"),
					new Auxiliary("être")
			})
			);
	
	// Conjugation (if you add a time don't forget to initialize 
	// it in the constructor and then add it to the auxialiaries sections of Word.words list)
	private Verb[] simplePresent;
	private Verb[] imparfait;
	private Verb[] futur;
	private Verb[] simplePast;
	private Verb[] pastParticiples;
	private Verb presentParticiple;
	
	// Constructor
	public Auxiliary(String text) {
		super(text, null, 0);
		
		// Check to fill in the conjugation
		if (text.equals("avoir")) {
			simplePresent = new Verb[]{new Verb ("ai", this, 0), new Verb ("as", this, 0), 
					new Verb ("a", this, 0), new Verb ("avons", this, 0), new Verb ("avez", this, 0), new Verb ("ont", this, 0)};
			imparfait = new Verb[] {new Verb ("avais", this, 0), new Verb ("avais", this, 0), 
					new Verb ("avait", this, 0), new Verb ("avions", this, 0), new Verb ("aviez", this, 0), new Verb ("avaient", this, 0)};
			futur = new Verb[]{new Verb ("aurai", this, 0), new Verb ("auras", this, 0), 
					new Verb ("aura", this, 0), new Verb ("aurons", this, 0), new Verb ("aurez", this, 0), new Verb ("auront", this, 0)};
			simplePast = new Verb[]{new Verb ("eus", this, 0), new Verb ("eus", this, 0), 
					new Verb ("eut", this, 0), new Verb ("eûmes", this, 0), new Verb ("eûtes", this, 0), new Verb ("eurent", this, 0)};
			Verb ppe = new Verb("eu", this, 0);
			pastParticiples = new Verb[]{ppe, ppe, ppe, ppe};
			presentParticiple = new Verb("ayant", this, 0);
		}else if (text.equals("être")) {
			simplePresent = new Verb[]{new Verb ("suis", this, 0), new Verb ("es", this, 0), 
					new Verb ("est", this, 0), new Verb ("sommes", this, 0), new Verb ("êtes", this, 0), new Verb ("sont", this, 0)};
			imparfait = new Verb[] {new Verb ("étais", this, 0), new Verb ("étais", this, 0), 
					new Verb ("était", this, 0), new Verb ("étions", this, 0), new Verb ("étiez", this, 0), new Verb ("étaient", this, 0)};
			futur = new Verb[]{new Verb ("serai", this, 0), new Verb ("seras", this, 0), 
					new Verb ("sera", this, 0), new Verb ("serons", this, 0), new Verb ("serez", this, 0), new Verb ("seront", this, 0)};
			simplePast = new Verb[]{new Verb ("fus", this, 0), new Verb ("fus", this, 0), 
					new Verb ("fut", this, 0), new Verb ("fûmes", this, 0), new Verb ("fûtes", this, 0), new Verb ("furent", this, 0)};
			Verb ppe = new Verb("été", this, 0);
			pastParticiples = new Verb[]{ppe, ppe, ppe, ppe};
			presentParticiple = new Verb("étant", this, 0);
		}
	}
	
	public static Auxiliary getAvoir () {
		return auxiliaries.get(0);
	}
	
	public static Auxiliary getEtre () {
		return auxiliaries.get(1);
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
