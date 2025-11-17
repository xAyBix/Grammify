package ma.supmti.grammify.grammar;

/**
 * First Group verbs
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
	private String radical;
	
	public Verb1(String text) {
		super(text);
		
		if (text.endsWith("er")) {
			this.radical = text.substring(0, text.length()-2);
		}
		
		if (radical != null && radical.endsWith("g")) {
			for (String sp : simplePresent) {
				if (sp.equals("ons")) {
					words.add(new Verb(radical + "e" + sp));
				}else {
					words.add(new Verb(radical + sp));
				}
			}
		}else if (radical != null && radical.endsWith("c")) {
			for (String sp : simplePresent) {
				if (sp.equals("ons")) {
					words.add(new Verb(radical.substring(0, radical.length()-1) + "ç" + sp));
				}else {
					words.add(new Verb(radical + sp));
				}
			}
		}else if (radical != null && radical.endsWith("y")) {
			for (String sp : simplePresent) {
				if (sp.equals("ons") || sp.equals("ez")) {
					words.add(new Verb(radical + sp));
				}else {
					words.add(new Verb(radical.substring(0, radical.length()-1) + "i" + sp));
				}
			}
		}
	}

	public String getRadical() {
		return radical;
	}
	
	public void setRadical(String radical) {
		this.radical = radical;
	}
	
	

}
