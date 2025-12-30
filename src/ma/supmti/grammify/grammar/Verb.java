package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-17 03:11
 */
public class Verb extends Word{
	// Stores all verbs
	public static List<Verb> verbs = new ArrayList<Verb>();
	
	private Verb infinitive;
	private int group;
	
	// Constructor
	public Verb(String text, Verb infinitive, int group) {
		super(text, PartOfSpeech.VERB);
		this.group = group;
		if(infinitive == null) {
			this.infinitive = this;
		}else {
			this.infinitive = infinitive;
		}
	}
	
	// Checks if the verb is infinitive
	public boolean isInfinitive() {
		return infinitive == null;
	}
	
	public Verb getInfinitive() {
		return infinitive;
	}
	public void setInfinitive(Verb infinitive) {
		this.infinitive = infinitive;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	

}
