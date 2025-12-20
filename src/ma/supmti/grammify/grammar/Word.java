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
		this.text = text.toLowerCase();
		this.partOfSpeech = partOfSpeech;
	}


	// A method that initialize words
	public static final void init () {
		//Punctuations
		words.add(new Punctuation(".", PunctuationTypes.PERIOD));
		words.add(new Punctuation(",", PunctuationTypes.COMMA));
		words.add(new Punctuation("?", PunctuationTypes.QUESTION_MARK));
		words.add(new Punctuation("!", PunctuationTypes.EXCLAMATION_POINT));
		words.add(new Punctuation("(", PunctuationTypes.LEFT_PARENTHESE));
		words.add(new Punctuation(")", PunctuationTypes.RIGHT_PARENTHESE));
		words.add(new Punctuation("'", PunctuationTypes.APOSTROPHE));
		words.add(new Punctuation("...", PunctuationTypes.ELLIPSIS));
		words.add(new Punctuation("-", PunctuationTypes.HYPHEN));
		words.add(new Punctuation(":", PunctuationTypes.COLON));
		words.add(new Punctuation("\"", PunctuationTypes.QUOTATION_MARK));
		words.add(new Punctuation(" ", PunctuationTypes.WHITE_SPACE));
		words.add(new Punctuation(System.lineSeparator(), PunctuationTypes.NEW_LINE));
		
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
		words.add(new Verb2("applaudir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("avertir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("bâtir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("bénir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("blanchir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("bondir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("choisir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("définir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("désobéir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("démolir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("dépérir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("embellir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("envahir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("établir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("fleurir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("franchir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("grandir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("grossir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("haïr", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("investir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("jaillir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("maigrir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("mincir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("noircir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("nourrir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("obéir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("pâlir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("punir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("réagir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("réfléchir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("réjouir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("remplir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("réussir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("rôtir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("rougir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("saisir", Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb2("vieillir", Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb2("agir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb2("finir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb2("guérir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb2("ralentir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb2("salir", Arrays.asList(Auxiliary.getAvoir()), true));
		words.add(new Verb2("trahir", Arrays.asList(Auxiliary.getAvoir()), true));
		
		
		// 3rd group verbs
		
		// Nouns
		words.add(new Noun("table", GrammaticalGender.FEMININE, "tables"));
		words.add(new Noun("pomme", GrammaticalGender.FEMININE, "pommes"));
		words.add(new Noun("tableau", GrammaticalGender.MASCULINE, "tableaux"));
		words.add(new Noun("cahier", GrammaticalGender.MASCULINE, "cahiers"));
		words.add(new Noun("ordinateur", GrammaticalGender.FEMININE, "ordinateurs"));
		words.add(new Noun("ingénieur", GrammaticalGender.MASCULINE, "ingénieurs"));
		words.add(new Noun("ingénieurie", GrammaticalGender.FEMININE, "ingénieuries"));
		words.add(new Noun("ingénieure", GrammaticalGender.FEMININE, "ingénieures"));
		words.add(new Noun("génie", GrammaticalGender.MASCULINE, "génies"));
		words.add(new Noun("professeur", GrammaticalGender.MASCULINE, "professeurs"));
		words.add(new Noun("professeuse", GrammaticalGender.FEMININE, "professeuses"));
		words.add(new Noun("géologie", GrammaticalGender.FEMININE, "géologies"));
		words.add(new Noun("terre", GrammaticalGender.FEMININE, "terres"));
		words.add(new Noun("parent", GrammaticalGender.MASCULINE, "parents"));
		words.add(new Noun("ciel", GrammaticalGender.MASCULINE, ""));
		words.add(new Noun("ville", GrammaticalGender.FEMININE, "villes"));
		words.add(new Noun("ami", GrammaticalGender.MASCULINE, "amis"));
		words.add(new Noun("amie", GrammaticalGender.FEMININE, "amies"));
		words.add(new Noun("jour", GrammaticalGender.MASCULINE, "jours"));
		words.add(new Noun("nuit", GrammaticalGender.FEMININE, "nuits"));
		words.add(new Noun("monde", GrammaticalGender.MASCULINE, "mondes"));
		words.add(new Noun("homme", GrammaticalGender.MASCULINE, "hommes"));
		words.add(new Noun("femme", GrammaticalGender.FEMININE, "femmes"));
		words.add(new Noun("enfant", GrammaticalGender.MASCULINE, "enfants"));
		words.add(new Noun("affaire", GrammaticalGender.FEMININE, "affaires"));
		words.add(new Noun("an", GrammaticalGender.MASCULINE, "ans"));
		words.add(new Noun("année", GrammaticalGender.FEMININE, "années"));
		words.add(new Noun("appel", GrammaticalGender.MASCULINE, "appels"));
		words.add(new Noun("mois", GrammaticalGender.MASCULINE, ""));
		words.add(new Noun("vie", GrammaticalGender.FEMININE, "vies"));
		words.add(new Noun("maison", GrammaticalGender.FEMININE, "maisons"));
		words.add(new Noun("fois", GrammaticalGender.FEMININE, ""));
		words.add(new Noun("argent", GrammaticalGender.MASCULINE, "argents"));
		words.add(new Noun("arbre", GrammaticalGender.FEMININE, "arbres"));
		words.add(new Noun("station", GrammaticalGender.FEMININE, "stations"));
		words.add(new Noun("pantalon", GrammaticalGender.MASCULINE, "pontalons"));
		words.add(new Noun("animal", GrammaticalGender.MASCULINE, "animaux"));
		words.add(new Noun("parfum", GrammaticalGender.MASCULINE, "parfums"));
		words.add(new Noun("roi", GrammaticalGender.MASCULINE, "rois"));
		words.add(new Noun("reine", GrammaticalGender.FEMININE, "reines"));
		words.add(new Noun("chien", GrammaticalGender.MASCULINE, "chiens"));
		words.add(new Noun("chienne", GrammaticalGender.FEMININE, "chiennes"));
		words.add(new Noun("chat", GrammaticalGender.MASCULINE, "chats"));
		words.add(new Noun("chatte", GrammaticalGender.FEMININE, "chattes"));
		words.add(new Noun("erreur", GrammaticalGender.FEMININE, "erreurs"));
		words.add(new Noun("faute", GrammaticalGender.FEMININE, "fautes"));
		words.add(new Noun("grammaire", GrammaticalGender.FEMININE, "grammaires"));
		words.add(new Noun("banane", GrammaticalGender.FEMININE, "bananes"));
		words.add(new Noun("fleur", GrammaticalGender.FEMININE, "fleurs"));
		
		// DETERMINERS
		words.add(new Determiner("un", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, DeterminerTypes.INDIFINITE));
		words.add(new Determiner("une", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, DeterminerTypes.INDIFINITE));
		words.add(new Determiner("des", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.INDIFINITE));
		
		words.add(new Determiner("le", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, DeterminerTypes.DIFINITE));
		words.add(new Determiner("la", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, DeterminerTypes.DIFINITE));
		words.add(new Determiner("l", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, DeterminerTypes.DIFINITE));
		words.add(new Determiner("les", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.DIFINITE));
		
		words.add(new Determiner("de", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, DeterminerTypes.PARTITIFS));
		words.add(new Determiner("du", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, DeterminerTypes.PARTITIFS));
		words.add(new Determiner("des", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.PARTITIFS));
		
		words.add(new Determiner("mon", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("ma", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("mes", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("ton", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("ta", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("tes", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("son", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("sa", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("ses", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("notre", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("votre", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("nos", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("vos", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("leur", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		words.add(new Determiner("leurs", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.POSSESSIVE));
		
		words.add(new Determiner("ce", GrammaticalNumber.SINGULAR, GrammaticalGender.MASCULINE, DeterminerTypes.DEMONSTRATIVE));
		words.add(new Determiner("cet", GrammaticalNumber.SINGULAR, GrammaticalGender.UNISEX, DeterminerTypes.DEMONSTRATIVE));
		words.add(new Determiner("cette", GrammaticalNumber.SINGULAR, GrammaticalGender.FEMININE, DeterminerTypes.DEMONSTRATIVE));
		words.add(new Determiner("ces", GrammaticalNumber.PLURAL, GrammaticalGender.UNISEX, DeterminerTypes.DEMONSTRATIVE));
	
	}
	
	// Finding a word saved by text
	public static List<Word> findByText (String text) {
		List<Word> wordsFound = new ArrayList<>();
		String lowerText = text.toLowerCase();
		for(Word word : words) {
			if (word.getText().equals(lowerText))
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
