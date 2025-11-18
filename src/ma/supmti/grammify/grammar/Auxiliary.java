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
	private Verb pastParticipal;
	private Verb presentParticipal;
	
	// Constructor
	public Auxiliary(String text) {
		super(text, null);
		
		// Check to fill in the conjugation
		if (text.equals("avoir")) {
			simplePresent = new Verb[]{new Verb ("ai", this), new Verb ("as", this), 
					new Verb ("a", this), new Verb ("avons", this), new Verb ("avez", this), new Verb ("ont", this)};
			pastParticipal = new Verb("eu", this);
			presentParticipal = new Verb("ayant", this);
		}else if (text.equals("être")) {
			simplePresent = new Verb[]{new Verb ("suis", this), new Verb ("es", this), 
					new Verb ("est", this), new Verb ("sommes", this), new Verb ("êtes", this), new Verb ("sont", this)};
			pastParticipal = new Verb("été", this);
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
	public Verb getPastParticipal() {
		return pastParticipal;
	}
	public void setPastParticipal(Verb pastParticipal) {
		this.pastParticipal = pastParticipal;
	}

	public Verb getPresentParticipal() {
		return presentParticipal;
	}

	public void setPresentParticipal(Verb presentParticipal) {
		this.presentParticipal = presentParticipal;
	}
	

}
