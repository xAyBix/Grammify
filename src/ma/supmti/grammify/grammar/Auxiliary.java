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
	private Verb[] pastParticipal;
	private Verb presentParticipal;
	
	// Constructor
	public Auxiliary(String text) {
		super(text, null);
		
		// Check to fill in the conjugation
		if (text.equals("avoir")) {
			simplePresent = new Verb[]{new Verb ("ai", this), new Verb ("as", this), 
					new Verb ("a", this), new Verb ("avons", this), new Verb ("avez", this), new Verb ("ont", this)};
			imparfait = new Verb[] {new Verb ("avais", this), new Verb ("avais", this), 
					new Verb ("avait", this), new Verb ("avions", this), new Verb ("aviez", this), new Verb ("avaient", this)};
			futur = new Verb[]{new Verb ("aurai", this), new Verb ("auras", this), 
					new Verb ("aura", this), new Verb ("aurons", this), new Verb ("aurez", this), new Verb ("auront", this)};
			simplePast = new Verb[]{new Verb ("eus", this), new Verb ("eus", this), 
					new Verb ("eut", this), new Verb ("eûmes", this), new Verb ("eûtes", this), new Verb ("eurent", this)};
			pastParticipal = new Verb[]{new Verb("eu", this), new Verb("eus", this),
					new Verb("eue", this), new Verb("eues", this)};
			presentParticipal = new Verb("ayant", this);
		}else if (text.equals("être")) {
			simplePresent = new Verb[]{new Verb ("suis", this), new Verb ("es", this), 
					new Verb ("est", this), new Verb ("sommes", this), new Verb ("êtes", this), new Verb ("sont", this)};
			imparfait = new Verb[] {new Verb ("étais", this), new Verb ("étais", this), 
					new Verb ("était", this), new Verb ("étions", this), new Verb ("étiez", this), new Verb ("étaient", this)};
			futur = new Verb[]{new Verb ("serai", this), new Verb ("seras", this), 
					new Verb ("sera", this), new Verb ("serons", this), new Verb ("serez", this), new Verb ("seront", this)};
			simplePast = new Verb[]{new Verb ("fus", this), new Verb ("fus", this), 
					new Verb ("fut", this), new Verb ("fûmes", this), new Verb ("fûtes", this), new Verb ("furent", this)};
			Verb ppe = new Verb("été", this);
			pastParticipal = new Verb[]{ppe, ppe, ppe, ppe};
			presentParticipal = new Verb("étant", this);
		}
	}
	
	public static Auxiliary getAvoir () {
		return auxiliaries.get(0);
	}
	
	public static Auxiliary getEtre () {
		return auxiliaries.get(1);
	}
	
	
	public Verb[] getSimplePresent() {
		return simplePresent;
	}
	public void setSimplePresent(Verb[] simplePresent) {
		this.simplePresent = simplePresent;
	}

	public Verb[] getPastParticipal() {
		return pastParticipal;
	}

	public void setPastParticipal(Verb[] pastParticipal) {
		this.pastParticipal = pastParticipal;
	}

	public Verb getPresentParticipal() {
		return presentParticipal;
	}

	public void setPresentParticipal(Verb presentParticipal) {
		this.presentParticipal = presentParticipal;
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
