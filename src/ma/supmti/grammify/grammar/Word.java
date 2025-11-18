package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains the dictionary of words used to check
 * user's inputs
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-17 02:01
 */
public class Word {
	public static List<Word> words = new ArrayList<>();
	private String text;
	private PartOfSpeech partOfSpeech;
	public Word (String text, PartOfSpeech partOfSpeech) {
		this.text = text;
		this.partOfSpeech = partOfSpeech;
	}


	// A method that initialize words
	public static final void init () {
		// Pronouns
		words.add(new Pronoun("je", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("j'", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("tu", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("il", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE));
		words.add(new Pronoun("elle", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE));
		words.add(new Pronoun("nous", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX));
		words.add(new Pronoun("vous", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX));
		words.add(new Pronoun("ils", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE));
		words.add(new Pronoun("elles", GrammaticalNumber.PLURAL, GrammaticalGender.FEMININE));
		words.add(new Pronoun("me", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("m'", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("te", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("t'", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("se", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("s'", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("mon", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE));
		words.add(new Pronoun("ma", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE));
		words.add(new Pronoun("ton", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE));
		words.add(new Pronoun("ta", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE));
		words.add(new Pronoun("son", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE));
		words.add(new Pronoun("sa", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE));
		words.add(new Pronoun("notre", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("votre", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("leur", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX));
		words.add(new Pronoun("mes", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX));
		words.add(new Pronoun("tes", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX));
		words.add(new Pronoun("ses", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX));
		words.add(new Pronoun("nos", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX));
		words.add(new Pronoun("vos", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX));
		words.add(new Pronoun("leurs", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX));
		
		// Auxiliaries (already initialized in Auxialiary class)
		// Saving Avoir to words
		words.add(Auxiliary.getAvoir());
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getSimplePresent()));
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getPastParticipal()));
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getPresentParticipal()));
		// Saving Etre to words
		words.add(Auxiliary.getEtre());
		words.addAll(Arrays.asList(Auxiliary.getEtre().getSimplePresent()));
		words.addAll(Arrays.asList(Auxiliary.getEtre().getPastParticipal()));
		words.addAll(Arrays.asList(Auxiliary.getEtre().getPresentParticipal()));
		
		// 1st group verbs
		words.add(new Verb1("penser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("manger", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("chercher", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("crier", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("danser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("balayer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("acheter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("commancer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("actualiser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("ramasser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("contacter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("marcher", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("travailler", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("tousser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("éternuer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("pleurer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("neiger", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("grêler", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("venter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("geler", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("exister", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("régner", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("sembler", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("étudier", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("cuisiner", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("aimer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("adorer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("habiter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("détester", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("rêver", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		words.add(new Verb1("nager", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
		
		words.add(new Verb1("arrêter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("parler", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("écouter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("jouer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("trouver", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("regarder", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("donner", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("laver", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("nettoyer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("organiser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("noyer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("abandonner", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("abonner", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("adapter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("aider", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("coucher", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("lever", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("réveiller", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("habiller", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("déshabiller", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("brosser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("rappeler", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("promener", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("dépêcher", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("reposer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("fâcher", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("tromper", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("intéresser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("marier", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("amuser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("préparer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		
		words.add(new Verb1("arriver", Arrays.asList(new Auxiliary[] {Auxiliary.getEtre()}), false));
		
	}
	
	// Finding a word saved by the text
	public static Word findByText (String text) {
		for(Word word : words) {
			if (word.getText().equals(text))
				return word;
		}
		return null;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public PartOfSpeech getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	
	
}
