package ma.supmti.grammify;


import javax.swing.SwingUtilities;

import ma.supmti.grammify.grammar.Word;
import ma.supmti.grammify.ui.MainFrame;


/**
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-15 22:30
 */
public class GrammifyApplication {
	public static void main(String[] args) {
		
		// Load dictionary
		Word.init();
				
		// Run UI
		SwingUtilities.invokeLater(MainFrame::new);

	}

}
