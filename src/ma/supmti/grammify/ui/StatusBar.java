package ma.supmti.grammify.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * A bottom bar that shows lines number, columns number and characters number
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2025-12-16 22:13
 */
public class StatusBar extends JPanel{
	JLabel statusLabel = new JLabel("Ln 1 | Col 1 | Chars 0");
	
	public StatusBar () {
		super();
		statusLabel.setVisible(false);
		add(statusLabel);
	}
	
	

}
