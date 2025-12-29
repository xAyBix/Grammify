package ma.supmti.grammify.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import ma.supmti.grammify.Constants;
import ma.supmti.grammify.grammar.Word;
import ma.supmti.grammify.grammar.detection.ErrorsDetector;
import ma.supmti.grammify.utils.Error;

/**
 * A popup menu that appears when right click on the errors
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2025-12-21 18:00
 */
public class SuggestionsPopup {
	private static JPopupMenu popup = new JPopupMenu();
	public static void showSuggestionsPopup (Error err, int x, int y) {
		JLabel errorMessage = new JLabel(err.getErrorMessage());
		JMenuItem ignoreItem = new JMenuItem("Ignore");
		popup.setVisible(false);
		popup.removeAll();
		popup.setBackground(new Color(0x1E1E1E));
		popup.setBorder(BorderFactory.createLineBorder(new Color(0x3C3C3C)));
		errorMessage.setFont(Constants.mainFont);
		errorMessage.setForeground(new Color(0xF44747));
		ignoreItem.setFont(Constants.secondaryFont);
		ignoreItem.setBackground(new Color(0x1E1E1E));
		ignoreItem.setForeground(Color.WHITE);
		ignoreItem.setBorder(BorderFactory.createEmptyBorder());
		ignoreItem.addActionListener(e -> {
			ErrorsDetector.addToIgnoredList(err);
		});
		popup.add(errorMessage);
		popup.add(ignoreItem);
		
		if (!err.getAlternatives().isEmpty()) {
			for (Word suggestion : err.getAlternatives()) {
				JMenuItem menuItem = new JMenuItem("\"" + suggestion.getText() + "\"");
				menuItem.setFont(Constants.secondaryFont);
				menuItem.setBackground(new Color(0x1E1E1E));
				menuItem.setForeground(Color.WHITE);
				menuItem.setBorder(BorderFactory.createEmptyBorder());
				menuItem.addActionListener(e -> replace(suggestion.getText(), err.getStart(), err.getEnd()));
				popup.add(menuItem);

			}
		}
		popup.show(MainFrame.textArea, x, y);
	}
	private static void replace (String text, int start, int end) {
		try {
	        Document doc = MainFrame.textArea.getDocument();
	        doc.remove(start, end - start);
	        doc.insertString(start, text, null);
	    } catch (BadLocationException ex) {
	        ex.printStackTrace();
	    }
	}
	
}
