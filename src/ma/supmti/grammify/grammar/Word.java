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
		words.add(new Pronoun("je", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.PERSONAL, "j"));
		words.add(new Pronoun("tu", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.PERSONAL, null));
		words.add(new Pronoun("il", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, PronounTypes.PERSONAL, null));
		words.add(new Pronoun("elle", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, Arrays.asList(new PronounTypes[] {PronounTypes.PERSONAL, PronounTypes.STRESSED}), null));
		words.add(new Pronoun("on", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.PERSONAL, null));
		words.add(new Pronoun("nous", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, Arrays.asList(new PronounTypes[] {PronounTypes.PERSONAL, PronounTypes.REFLEXIVE, PronounTypes.STRESSED}), null));
		words.add(new Pronoun("vous", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, Arrays.asList(new PronounTypes[] {PronounTypes.PERSONAL, PronounTypes.REFLEXIVE, PronounTypes.STRESSED}), null));
		words.add(new Pronoun("ils", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE, PronounTypes.PERSONAL, null));
		words.add(new Pronoun("elles", GrammaticalNumber.PLURAL, GrammaticalGender.FEMININE, Arrays.asList(new PronounTypes[] {PronounTypes.PERSONAL, PronounTypes.STRESSED}), null));
		words.add(new Pronoun("me", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.REFLEXIVE, "m"));
		words.add(new Pronoun("te", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.REFLEXIVE, "t"));
		words.add(new Pronoun("se", GrammaticalNumber.BOTH, GrammaticalGender.UNISEX, PronounTypes.REFLEXIVE, "s"));
		words.add(new Pronoun("mien", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("mienne", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("miens", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("miennes", GrammaticalNumber.PLURAL, GrammaticalGender.FEMININE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("tien", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("tienne", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("tiens", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("tiennes", GrammaticalNumber.PLURAL, GrammaticalGender.FEMININE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("sien", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("sienne", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("siens", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("siennes", GrammaticalNumber.PLURAL, GrammaticalGender.FEMININE, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("nôtre", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("nôtres", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("vôtre", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("vôtres", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("leur", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("leurs", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, PronounTypes.POSSESSIVE, null));
		words.add(new Pronoun("celui", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, PronounTypes.DEMONSTRATIVE, null));
		words.add(new Pronoun("celle", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, PronounTypes.DEMONSTRATIVE, null));
		words.add(new Pronoun("ceux", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE, PronounTypes.DEMONSTRATIVE, null));
		words.add(new Pronoun("celles", GrammaticalNumber.PLURAL, GrammaticalGender.FEMININE, PronounTypes.DEMONSTRATIVE, null));
		words.add(new Pronoun("ceci", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.DEMONSTRATIVE, null));
		words.add(new Pronoun("cela", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.DEMONSTRATIVE, null));
		words.add(new Pronoun("ça", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.DEMONSTRATIVE, null));
		words.add(new Pronoun("y", GrammaticalNumber.BOTH, GrammaticalGender.UNISEX, PronounTypes.ADVERBIAL, null));
		words.add(new Pronoun("qui", GrammaticalNumber.BOTH, GrammaticalGender.UNISEX, Arrays.asList(new PronounTypes[] {PronounTypes.RELATIVE, PronounTypes.INTERROGATIVE}), null));
		words.add(new Pronoun("que", GrammaticalNumber.BOTH, GrammaticalGender.UNISEX, Arrays.asList(new PronounTypes[] {PronounTypes.RELATIVE, PronounTypes.INTERROGATIVE}), null));
		words.add(new Pronoun("dont", GrammaticalNumber.BOTH, GrammaticalGender.UNISEX, PronounTypes.RELATIVE, null));
		words.add(new Pronoun("où", GrammaticalNumber.BOTH, GrammaticalGender.UNISEX, PronounTypes.RELATIVE, null));
		words.add(new Pronoun("quoi", GrammaticalNumber.BOTH, GrammaticalGender.UNISEX, PronounTypes.INTERROGATIVE, null));
		words.add(new Pronoun("lequel", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, PronounTypes.INTERROGATIVE, null));
		words.add(new Pronoun("lesquels", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE, PronounTypes.INTERROGATIVE, null));
		words.add(new Pronoun("laquelle", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, PronounTypes.INTERROGATIVE, null));
		words.add(new Pronoun("lesquelles", GrammaticalNumber.PLURAL, GrammaticalGender.FEMININE, PronounTypes.INTERROGATIVE, null));
		words.add(new Pronoun("moi", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.STRESSED, null));
		words.add(new Pronoun("toi", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, PronounTypes.STRESSED, null));
		words.add(new Pronoun("lui", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, PronounTypes.STRESSED, null));
		words.add(new Pronoun("eux", GrammaticalNumber.PLURAL, GrammaticalGender.MASCULINE, PronounTypes.STRESSED, null));
		
		// Adjectifs
		
		// Auxiliaries (already initialized in Auxialiary class)
		// Saving Avoir to words
		words.add(Auxiliary.getAvoir());
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getSimplePresent()));
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getImparfait()));
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getFutur()));
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getSimplePast()));
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getPastParticipal()));
		words.addAll(Arrays.asList(Auxiliary.getAvoir().getPresentParticipal()));
		// Saving Etre to words
		words.add(Auxiliary.getEtre());
		words.addAll(Arrays.asList(Auxiliary.getEtre().getSimplePresent()));
		words.addAll(Arrays.asList(Auxiliary.getEtre().getImparfait()));
		words.addAll(Arrays.asList(Auxiliary.getEtre().getFutur()));
		words.addAll(Arrays.asList(Auxiliary.getEtre().getSimplePast()));
		words.addAll(Arrays.asList(Auxiliary.getEtre().getPastParticipal()));
		words.addAll(Arrays.asList(Auxiliary.getEtre().getPresentParticipal()));
		
		// 1st group verbs
		words.add(new Verb1("causer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), false));
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
		words.add(new Verb1("annoncer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("déclarer", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
		words.add(new Verb1("présenter", Arrays.asList(new Auxiliary[] {Auxiliary.getAvoir()}), true));
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
		
		// 2nd group verbs
		words.add(new Verb1("applaudir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("avertir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("bâtir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("bénir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("blanchir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("bondir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("choisir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("définir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("désobéir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("démolir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("dépérir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("embellir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("envahir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("établir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("fleurir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("franchir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("grandir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("grossir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("haïr", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("investir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("jaillir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("maigrir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("mincir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("noircir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("nourrir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("obéir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("pâlir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("punir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("réagir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("réfléchir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("réjouir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("remplir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("réussir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("rôtir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("rougir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("saisir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb1("vieillir", Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb1("agir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb1("finir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb1("guérir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb1("ralentir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb1("salir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb1("trahir", Arrays.asList(Auxiliary.getAvoir()), true));
		
		
		// 3rd group verbs
		
	}
	
	// Finding a word saved by text
	public static List<Word> findByText (String text) {
		List<Word> wordsFound = new ArrayList<>();
		for(Word word : words) {
			if (word.getText().equals(text))
				wordsFound.add(word);
		}
		return wordsFound;
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
