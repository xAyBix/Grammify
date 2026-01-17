package ma.supmti.grammify;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ma.supmti.grammify.grammar.Word;
import ma.supmti.grammify.ui.MainFrame;


/**
 * Contains main method (Startup of the application)
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-15 22:30
 */
public class GrammifyApplication {
	
	public static MainFrame mainFrame;
	
	public static void main(String[] args) {
		// Enable anti-aliasing and improve font rendering
	    System.setProperty("awt.useSystemAAFontSettings", "on");
	    System.setProperty("swing.aatext", "true");
	    
	    // For high DPI displays
	    System.setProperty("sun.java2d.uiScale", "1.0");
	    
	    // Use system look and feel for better native font rendering (optional)
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		// Load dictionary
		Word.init();
		System.out.println("Nombre des mots ajoutes au dictionnaire: " + Word.words.size());
		
				
		// Run UI
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		    	mainFrame = new MainFrame();
		    }
		});
		
		// Debugging
//		Parser.pureTokens = Tokenizer.tokenize("j'étudie  la biologie, et non la geologie en 2025.");
//		System.out.println(Parser.parse());
//		ErrorsDetector.init(Parser.parse());
//		System.out.println(FileManager.openFile());
//		FileManager.saveFile("hi");
		

	}

}
