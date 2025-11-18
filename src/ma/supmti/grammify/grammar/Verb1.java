package ma.supmti.grammify.grammar;

import java.util.List;

/**
 * First Group verbs that ends with "er"
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
	public static String[] simplePresent = {"e", "es", "e", "ons", "ez", "ent"};
	public static String[] pastParticipal = {"é", "és", "ée", "ées"};
	public static String presentParticipal = "ant";
	private String radical;
	// Indicates which auxiliaries the verb uses when conjugates
	private List<Auxiliary> auxiliaries;
	// Indicates if the verb can be written as "Je me suis <verb>"
	private boolean pronominal;
	
	public Verb1(String text, List<Auxiliary> auxiliaries, boolean pronominal) {
		super(text, null);
		
		this.auxiliaries = auxiliaries;
		this.pronominal = pronominal;
		
		if (text.endsWith("er")) {
			this.radical = text.substring(0, text.length()-2);
		}
		
		if (radical != null && radical.endsWith("g")) {
			for (String sp : simplePresent) {
				if (sp.equals("ons")) {
					words.add(new Verb(radical + "e" + sp, this));
				}else {
					words.add(new Verb(radical + sp, this));
				}
			}
		}else if (radical != null && radical.endsWith("c")) {
			for (String sp : simplePresent) {
				if (sp.equals("ons")) {
					words.add(new Verb((radical.substring(0, radical.length()-1) + "ç" + sp), this));
				}else {
					words.add(new Verb(radical + sp, this));
				}
			}
		}else if (radical != null && radical.endsWith("y")) {
			for (String sp : simplePresent) {
				if (sp.equals("ons") || sp.equals("ez")) {
					words.add(new Verb(radical + sp, this));
				}else {
					words.add(new Verb((radical.substring(0, radical.length()-1) + "i" + sp), this));
				}
			}
		}
		for (String pp : pastParticipal)
		 words.add(new Verb(radical + pp, this));
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
	
	

}
