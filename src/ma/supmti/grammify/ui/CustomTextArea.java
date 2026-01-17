package ma.supmti.grammify.ui;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
	public static boolean isLoadingFile = false;

	public CustomTextArea() {
		super();
		setEditable(true);
		setFont(Constants.mainFont());
		setBackground(Constants.secondaryColor());
		setForeground(Color.WHITE);
		setCaretColor(Color.WHITE);
	}

	// Checks if there are changes in text
	public static void init() {

		
		MainFrame.textArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (CustomTextArea.isLoadingFile) return;
				updateAll();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (CustomTextArea.isLoadingFile) return;
				updateAll();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (CustomTextArea.isLoadingFile) return;
				updateAll();

			}
		});
	}
	private static void updateAll () {
		String textAreaCurrentText = MainFrame.textArea.getText();
		// System.err.println(textAreaCurrentText);
		Parser.pureTokens = Tokenizer.tokenize(textAreaCurrentText);

		ErrorsDetector.init();
		
		if (OpenedFile.initialText == null || !OpenedFile.initialText.equals(MainFrame.textArea.getText())) {
			GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - *" + OpenedFile.name);
		} else {
			GrammifyApplication.mainFrame.setTitle(Constants.APP_NAME + " - " + OpenedFile.name);
		}
		MainFrame.statusBar.update();
	}
}
