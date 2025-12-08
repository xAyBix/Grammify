package ma.supmti.grammify.ui;

import java.awt.Font;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextPane;

import ma.supmti.grammify.Constants;
import ma.supmti.grammify.GrammifyApplication;
import ma.supmti.grammify.grammar.detection.ErrorsDetector;
import ma.supmti.grammify.grammar.detection.Parser;
import ma.supmti.grammify.grammar.detection.Tokenizer;
import ma.supmti.grammify.io.OpenedFile;


/**
 * A custom text area
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2025-12-06 02:31
 */
public class CustomTextArea extends JTextPane {
	
	private static final long serialVersionUID = -690400514247574287L;

	public CustomTextArea () {
		super();
		setEditable(true);
		setFont(new Font("Monospaced", 0, 16) );
	}
	
	// Checks if there are changes in text
	public static void init () {
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.submit((Runnable) () -> {
			String textAreaCurrentText = "";
			boolean index = false;
			while (true) {
				textAreaCurrentText = MainFrame.textArea.getText();
				System.err.println(textAreaCurrentText);
				Parser.pureTokens = Tokenizer.tokenize(textAreaCurrentText);
				if (!index) {
					index = !index;
					ErrorsDetector.init();
				}
				
				if (!OpenedFile.initialText.equals(MainFrame.textArea.getText())) {
					GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - *" + OpenedFile.name);
				}else {
					GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - " + OpenedFile.name);
				}
				
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
