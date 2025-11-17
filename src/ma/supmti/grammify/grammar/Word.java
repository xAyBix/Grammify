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
		((Runnable) ()-> words.add(new Pronoun("je", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("tu", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("il", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE))).run();
		((Runnable) ()-> words.add(new Pronoun("elle", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE))).run();
		((Runnable) ()-> words.add(new Pronoun("nous", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("vous", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("ils", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE))).run();
		((Runnable) ()-> words.add(new Pronoun("elles", GrammaticalNumber.PLURAL, GrammaticalGender.FEMININE))).run();
		((Runnable) ()-> words.add(new Pronoun("me", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("m'", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("te", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("t'", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("se", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("s'", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("mon", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE))).run();
		((Runnable) ()-> words.add(new Pronoun("ma", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE))).run();
		((Runnable) ()-> words.add(new Pronoun("ton", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE))).run();
		((Runnable) ()-> words.add(new Pronoun("ta", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE))).run();
		((Runnable) ()-> words.add(new Pronoun("son", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE))).run();
		((Runnable) ()-> words.add(new Pronoun("sa", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE))).run();
		((Runnable) ()-> words.add(new Pronoun("notre", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("votre", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("leur", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("mes", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("tes", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("ses", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("nos", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("vos", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX))).run();
		((Runnable) ()-> words.add(new Pronoun("leurs", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX))).run();
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
