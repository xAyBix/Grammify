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
		words.add(new Verb1("manger", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()})));
		words.add(new Verb1("balayer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()})));
		words.add(new Verb1("acheter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()})));
		words.add(new Verb1("commancer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()})));
		words.add(new Verb1("actualiser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()})));
		words.add(new Verb1("ramasser", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()})));
		words.add(new Verb1("contacter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()})));
		
		words.add(new Verb1("arrêter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir(), Auxiliary.getEtre()})));
		words.add(new Verb1("noyer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir(), Auxiliary.getEtre()})));
		words.add(new Verb1("abandonner", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir(), Auxiliary.getEtre()})));
		words.add(new Verb1("abonner", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir(), Auxiliary.getEtre()})));
		words.add(new Verb1("adapter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir(), Auxiliary.getEtre()})));
		words.add(new Verb1("aimer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir(), Auxiliary.getEtre()})));
		words.add(new Verb1("aider", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir(), Auxiliary.getEtre()})));
		words.add(new Verb1("coucher", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir(), Auxiliary.getEtre()})));
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
