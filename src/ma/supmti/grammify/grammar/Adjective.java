package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the logic of adjectives
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-12-22 12:17
 */
public class Adjective extends Word {
	public static List<Adjective> adjectives = new ArrayList<>();
	private Adjective oppositeGender;
	private Adjective plural;
	private Adjective singular;
	private GrammaticalNumber grammaticalNumber;
	private GrammaticalGender grammaticalGender;

	private Adjective(String text, GrammaticalGender gender, GrammaticalNumber number) {
		super(text, PartOfSpeech.ADJECTIVE);
		this.grammaticalGender = gender;
		this.grammaticalNumber = number;
		adjectives.add(this);
		Word.words.add(this);
	}

	public static Adjective createAdjective(String masculineSingular, String feminineSingular, String masculinePlural,
			String femininePlural) {
		boolean unisex = feminineSingular.isEmpty() && femininePlural.isEmpty();

		// Singular
		Adjective sing = new Adjective(masculineSingular,
				unisex ? GrammaticalGender.UNISEX : GrammaticalGender.MASCULINE, GrammaticalNumber.SINGULAR);

		// Plural
		Adjective plur = masculinePlural.isEmpty() ? null
				: new Adjective(masculinePlural, unisex ? GrammaticalGender.UNISEX : GrammaticalGender.MASCULINE,
						GrammaticalNumber.PLURAL);

		// Link singular with plural
		sing.plural = plur;
		if (plur != null)
			plur.singular = sing;

		// UNISEX CASE — STOP HERE
		if (unisex) {
			return sing;
		}

		// Feminine singular
		Adjective femSing = new Adjective(feminineSingular, GrammaticalGender.FEMININE, GrammaticalNumber.SINGULAR);

		// Feminine plural
		Adjective femPlur = femininePlural.isEmpty() ? null
				: new Adjective(femininePlural, GrammaticalGender.FEMININE, GrammaticalNumber.PLURAL);

		// Link feminine singular with plural
		femSing.plural = femPlur;
		if (femPlur != null)
			femPlur.singular = femSing;

		// Link opposite genders
		sing.oppositeGender = femSing;
		femSing.oppositeGender = sing;

		if (plur != null && femPlur != null) {
			plur.oppositeGender = femPlur;
			femPlur.oppositeGender = plur;
		}

		return sing;
	}
	
	/*
	 * Checks if the adjective is an unisex
	 * 
	 * */
	public boolean isUnisex() {
	    return grammaticalGender == GrammaticalGender.UNISEX;
	}
	
	/*
	 * Returns the entered gender and number of the adjective
	 * 
	 * 
	 * */
	public Adjective agree(GrammaticalGender gender, GrammaticalNumber number) {
	    Adjective base = this;

	    if (!isUnisex() && gender != grammaticalGender) {
	        base = oppositeGender;
	    }
	    if (base != null && number != grammaticalNumber) {
	        base = base.plural;
	    }
	    return base;
	}

	public Adjective getOppositeGender() {
		return oppositeGender;
	}

	public void setOppositeGender(Adjective oppositeGender) {
		this.oppositeGender = oppositeGender;
	}

	public Adjective getPlural() {
		return plural;
	}

	public void setPlural(Adjective plural) {
		this.plural = plural;
	}

	public Adjective getSingular() {
		return singular;
	}

	public void setSingular(Adjective singular) {
		this.singular = singular;
	}

	public GrammaticalNumber getGrammaticalNumber() {
		return grammaticalNumber;
	}

	public void setGrammaticalNumber(GrammaticalNumber grammaticalNumber) {
		this.grammaticalNumber = grammaticalNumber;
	}

	public GrammaticalGender getGrammaticalGender() {
		return grammaticalGender;
	}

	public void setGrammaticalGender(GrammaticalGender grammaticalGender) {
		this.grammaticalGender = grammaticalGender;
	}

}
