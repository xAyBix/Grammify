package ma.supmti.grammify.ui;

import java.awt.Color;
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
	private static ExecutorService executorService;

	public CustomTextArea() {
		super();
		setEditable(true);
		setFont(Constants.mainFont);
		setBackground(Constants.secondaryColor);
		setForeground(Color.WHITE);
		setCaretColor(Color.WHITE);
	}

	// Checks if there are changes in text
	public static void init() {
		//System.err.println("init");
		if (executorService != null) {
			executorService.shutdown();
		}
		executorService = Executors.newFixedThreadPool(1);
		executorService.submit((Runnable) () -> {
			String textAreaCurrentText = "";
			boolean index = false;
			while (true) {
				textAreaCurrentText = MainFrame.textArea.getText();
				//System.err.println(textAreaCurrentText);
				Parser.pureTokens = Tokenizer.tokenize(textAreaCurrentText);
				
				if (!index) {
					index = !index;
					ErrorsDetector.init();
				}
				if (OpenedFile.initialText == null || !OpenedFile.initialText.equals(MainFrame.textArea.getText())) {
					GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - *" + OpenedFile.name);
				} else {
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

	// Shutdown the executor
	public static void shutdown() {
		executorService.shutdown();
	}
}
