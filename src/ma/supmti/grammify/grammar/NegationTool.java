package ma.supmti.grammify.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe tools used in negation
 * 
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-28 17:32
 */
public class NegationTool extends Word{
	public static List<NegationTool> negationTools = new ArrayList<>();
	public static List <NegationCombo>  combos = new ArrayList<>();
	public NegationTool(String text) {
		super(text, PartOfSpeech.NEGATION_TOOLS);
		negationTools.add(this);
	}
	public static NegationTool getNegationToolByText (String text) {
		for (NegationTool nt : negationTools) {
			if (nt.getText().equals(text))
				return nt;
		}
		return null;
	}

}

// Helper class
class NegationCombo {
	private NegationTool tool1;
	private NegationTool tool2;
	public NegationCombo (NegationTool tool1, NegationTool tool2) {
		this.tool1 = tool1;
		this.tool2 = tool2;
	}
	public NegationTool getTool1() {
		return tool1;
	}
	public void setTool1(NegationTool tool1) {
		this.tool1 = tool1;
	}
	public NegationTool getTool2() {
		return tool2;
	}
	public void setTool2(NegationTool tool2) {
		this.tool2 = tool2;
	}
	
}