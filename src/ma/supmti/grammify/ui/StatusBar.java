package ma.supmti.grammify.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ma.supmti.grammify.Constants;


/**
 * A bottom bar that shows lines number, columns number and characters number
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2025-12-16 22:13
 */
public class StatusBar extends JPanel{
	JLabel statusLabel = new JLabel("  ");
	
	public StatusBar () {
		super(new BorderLayout());
		statusLabel.setFont(Constants.secondaryFont());
		statusLabel.setForeground(Color.WHITE);
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setBackground(Constants.mainColor());
		setBorder(BorderFactory.createEmptyBorder());
		add(statusLabel, BorderLayout.EAST);
	}
	
	private void update (int ln, int col) {
		int chars = MainFrame.textArea.getDocument().getLength();
		statusLabel.setText("Ln " + ln + " | Col " + col + " | Chars " + chars + "  ");
	}
	
	public void update () {
		int caret = MainFrame.textArea.getCaretPosition();
		try {
			int ln = MainFrame.textArea.getDocument().getDefaultRootElement()
					.getElementIndex(caret)+1;
			int lnStart = MainFrame.textArea.getDocument().getDefaultRootElement()
					.getElement(ln-1).getStartOffset();
			int col = caret - lnStart + 1;
			update(ln, col);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
