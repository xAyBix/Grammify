package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the logic of coordinating conjunctions
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-12-23 21:51
 */
public class CoordinatingConjunction extends Word{
	public static List<CoordinatingConjunction> coordinatingConjunctions = new ArrayList<>();
	public CoordinatingConjunction (String text) {
		super(text, PartOfSpeech.CONJUCTION);
		coordinatingConjunctions.add(this);
	}
}
