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
		words.add(new Punctuation(""+((char)10), PunctuationTypes.NEW_LINE));
		words.add(new Punctuation(""+((char)13), PunctuationTypes.NEW_LINE));
		
		// Coordinating Conjunctions
		words.add(new CoordinatingConjunction("mais"));
		words.add(new CoordinatingConjunction("ou"));
		words.add(new CoordinatingConjunction("et"));
		words.add(new CoordinatingConjunction("donc"));
		words.add(new CoordinatingConjunction("or"));
		words.add(new CoordinatingConjunction("ni"));
		words.add(new CoordinatingConjunction("car"));
		
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
		
		// Adjectives
		Adjective.createAdjective("petit", "petite", "petits", "petites");
		Adjective.createAdjective("grand", "grande", "grands", "grandes");
		Adjective.createAdjective("bon", "bonne", "bons", "bonnes");
		Adjective.createAdjective("mauvais", "mauvaise", "mauvais", "mauvaises");
		Adjective.createAdjective("nouveau", "nouvelle", "nouveaux", "nouvelles");
		Adjective.createAdjective("vieux", "vieille", "vieux", "vieilles");
		Adjective.createAdjective("long", "longue", "longs", "longues");
		Adjective.createAdjective("court", "courte", "courts", "courtes");
		Adjective.createAdjective("large", "", "larges", "");
		Adjective.createAdjective("haut", "haute", "hauts", "hautes");
		Adjective.createAdjective("beau", "belle", "beaux", "belles");
		Adjective.createAdjective("joli", "jolie", "jolis", "jolies");
		Adjective.createAdjective("jeune", "", "jeunes", "");
		Adjective.createAdjective("vrai", "vraie", "vrais", "vraies");
		Adjective.createAdjective("heureux", "heureuse", "heureux", "heureuses");
		Adjective.createAdjective("triste", "", "tristes", "");
		Adjective.createAdjective("facile", "", "faciles", "");
		Adjective.createAdjective("difficile", "", "difficiles", "");
		Adjective.createAdjective("rapide", "", "rapides", "");
		Adjective.createAdjective("lent", "lente", "lents", "lentes");
		
		Adjective.createAdjective("rouge", "", "rouges", "");
		Adjective.createAdjective("bleu", "bleue", "bleus", "bleues");
		Adjective.createAdjective("vert", "verte", "verts", "vertes");
		Adjective.createAdjective("jaune", "", "jaunes", "");
		Adjective.createAdjective("orange", "", "oranges", "");
		Adjective.createAdjective("rose", "", "roses", "");
		Adjective.createAdjective("marron", "", "marron", "");
		Adjective.createAdjective("violet", "violette", "violets", "violettes");
		Adjective.createAdjective("gris", "grise", "gris", "grises");
		Adjective.createAdjective("noir", "noire", "noirs", "noires");
		Adjective.createAdjective("blanc", "blanche", "blancs", "blanches");
		Adjective.createAdjective("brun", "brune", "bruns", "brunes");
		Adjective.createAdjective("beige", "", "beiges", "");
		Adjective.createAdjective("turquoise", "", "turquoises", "");
		Adjective.createAdjective("kaki", "", "kaki", "");
		Adjective.createAdjective("bordeaux", "", "bordeaux", "");
		Adjective.createAdjective("ivoire", "", "ivoires", "");
		Adjective.createAdjective("or", "", "or", "");
		Adjective.createAdjective("argent", "", "argent", "");
		
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
		words.add(new Verb3("dormir", Verb3Pattern.DORMIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("sortir", Verb3Pattern.DORMIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("servir", Verb3Pattern.DORMIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("sentir", Verb3Pattern.DORMIR, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("aller", Verb3Pattern.ALLER, Arrays.asList(Auxiliary.getEtre()), false));
		words.add(new Verb3("vouloir", Verb3Pattern.VOULOIR, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("voir", Verb3Pattern.VOIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("prévoir", Verb3Pattern.VOIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("apercevoir", Verb3Pattern.VOIR, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("falloir", Verb3Pattern.FALLOIR, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("tenir", Verb3Pattern.TENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("maintenir", Verb3Pattern.TENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("soutenir", Verb3Pattern.TENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("obtenir", Verb3Pattern.TENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("retenir", Verb3Pattern.TENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("venir", Verb3Pattern.VENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("devenir", Verb3Pattern.VENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("revenir", Verb3Pattern.VENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("souvenir", Verb3Pattern.VENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("prévenir", Verb3Pattern.VENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("intervenir", Verb3Pattern.VENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("parvenir", Verb3Pattern.VENIR, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("dire", Verb3Pattern.DIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("redire", Verb3Pattern.DIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("interdire", Verb3Pattern.DIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("contredire", Verb3Pattern.DIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("médire", Verb3Pattern.DIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("mettre", Verb3Pattern.METTRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("remettre", Verb3Pattern.METTRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("permettre", Verb3Pattern.METTRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("promettre", Verb3Pattern.METTRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("admettre", Verb3Pattern.METTRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("soumettre", Verb3Pattern.METTRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("transmettre", Verb3Pattern.METTRE, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("prendre", Verb3Pattern.PRENDRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("apprendre", Verb3Pattern.PRENDRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("comprendre", Verb3Pattern.PRENDRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("surprendre", Verb3Pattern.PRENDRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("entreprendre", Verb3Pattern.PRENDRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("reprendre", Verb3Pattern.PRENDRE, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("partir", Verb3Pattern.PARTIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("repartir", Verb3Pattern.PARTIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("sortir", Verb3Pattern.PARTIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("servir", Verb3Pattern.PARTIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("sentir", Verb3Pattern.PARTIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("mentir", Verb3Pattern.PARTIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("consentir", Verb3Pattern.PARTIR, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("pressentir", Verb3Pattern.PARTIR, Arrays.asList(Auxiliary.getAvoir()), false));
		
		words.add(new Verb3("faire", Verb3Pattern.FAIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("défaire", Verb3Pattern.FAIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("refaire", Verb3Pattern.FAIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("contrefaire", Verb3Pattern.FAIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		words.add(new Verb3("satisfaire", Verb3Pattern.FAIRE, Arrays.asList(Auxiliary.getAvoir()), false));
		
		// Nouns
		words.add(new Noun("table", GrammaticalGender.FEMININE, "tables"));
		words.add(new Noun("pomme", GrammaticalGender.FEMININE, "pommes"));
		words.add(new Noun("tableau", GrammaticalGender.MASCULINE, "tableaux"));
		words.add(new Noun("cahier", GrammaticalGender.MASCULINE, "cahiers"));
		words.add(new Noun("ordinateur", GrammaticalGender.FEMININE, "ordinateurs"));
		words.add(new Noun("ingénieur", GrammaticalGender.UNISEX, "ingénieurs"));
		words.add(new Noun("ingénieurie", GrammaticalGender.FEMININE, "ingénieuries"));
		words.add(new Noun("ingénieure", GrammaticalGender.FEMININE, "ingénieures"));
		words.add(new Noun("génie", GrammaticalGender.MASCULINE, "génies"));
		words.add(new Noun("professeur", GrammaticalGender.MASCULINE, "professeurs"));
		words.add(new Noun("professeuse", GrammaticalGender.FEMININE, "professeuses"));
		words.add(new Noun("biologie", GrammaticalGender.FEMININE, "biologies"));
		words.add(new Noun("géologie", GrammaticalGender.FEMININE, "géologies"));
		words.add(new Noun("géographie", GrammaticalGender.FEMININE, "géographies"));
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
		words.add(new Noun("porte", GrammaticalGender.FEMININE, "portes"));
		words.add(new Noun("miroir", GrammaticalGender.FEMININE, "miroirs"));
		words.add(new Noun("pain", GrammaticalGender.MASCULINE, "pains"));
		words.add(new Noun("mur", GrammaticalGender.MASCULINE, "murs"));
		words.add(new Noun("baguette", GrammaticalGender.FEMININE, "baguettes"));
		words.add(new Noun("lesson", GrammaticalGender.FEMININE, "lessons"));
		words.add(new Noun("lunette", GrammaticalGender.FEMININE, "lunettes"));
		words.add(new Noun("médecin", GrammaticalGender.UNISEX, "médecins"));
		words.add(new Noun("maître", GrammaticalGender.MASCULINE, "maîtres"));
		words.add(new Noun("maîtresse", GrammaticalGender.FEMININE, "maîtresses"));
		words.add(new Noun("lampe", GrammaticalGender.FEMININE, "lampes"));
		words.add(new Noun("tajine", GrammaticalGender.FEMININE, "tajines"));
		words.add(new Noun("poulet", GrammaticalGender.MASCULINE, "poulets"));
		words.add(new Noun("garçon", GrammaticalGender.MASCULINE, "garçons"));
		words.add(new Noun("fille", GrammaticalGender.FEMININE, "filles"));
		words.add(new Noun("école", GrammaticalGender.FEMININE, "écoles"));
		words.add(new Noun("chose", GrammaticalGender.FEMININE, "choses"));
		
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
		
		//Negation Tools
		words.add(new NegationTool("ne"));
		words.add(new NegationTool("n"));
		words.add(new NegationTool("pas"));
		words.add(new NegationTool("aucun"));
		words.add(new NegationTool("aucune"));
		words.add(new NegationTool("jamais"));
		words.add(new NegationTool("rien"));
		words.add(new NegationTool("plus"));
		words.add(new NegationTool("personne"));
		words.add(new NegationTool("guère"));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("ne"), NegationTool.getNegationToolByText("pas")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("ne"), NegationTool.getNegationToolByText("aucun")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("ne"), NegationTool.getNegationToolByText("aucune")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("ne"), NegationTool.getNegationToolByText("jamais")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("ne"), NegationTool.getNegationToolByText("rien")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("ne"), NegationTool.getNegationToolByText("plus")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("ne"), NegationTool.getNegationToolByText("personne")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("ne"), NegationTool.getNegationToolByText("guère")));
		
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("n"), NegationTool.getNegationToolByText("pas")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("n"), NegationTool.getNegationToolByText("aucun")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("n"), NegationTool.getNegationToolByText("aucune")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("n"), NegationTool.getNegationToolByText("jamais")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("n"), NegationTool.getNegationToolByText("rien")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("n"), NegationTool.getNegationToolByText("plus")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("n"), NegationTool.getNegationToolByText("personne")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("n"), NegationTool.getNegationToolByText("guère")));
		
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("aucun"), NegationTool.getNegationToolByText("ne")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("aucun"), NegationTool.getNegationToolByText("n")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("aucune"), NegationTool.getNegationToolByText("ne")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("aucune"), NegationTool.getNegationToolByText("n")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("rien"), NegationTool.getNegationToolByText("ne")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("rien"), NegationTool.getNegationToolByText("n")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("personne"), NegationTool.getNegationToolByText("ne")));
		NegationTool.combos.add(new NegationCombo(NegationTool.getNegationToolByText("personne"), NegationTool.getNegationToolByText("n")));
	
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
