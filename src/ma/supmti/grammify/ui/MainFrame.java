package ma.supmti.grammify.ui;

import javax.swing.JFrame;

import ma.supmti.grammify.Constants;


/**
 * The main frame that contains the GUI components
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2025-11-16 22:15
 */
public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 3816958299287539260L;

	public MainFrame() {
		
		// Interface's frame configuration
		setTitle(Constants.APP_NAME);
		setIconImage(Constants.img);
		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		// Add A Menu Bar at top
		setJMenuBar(new MenuBar());
		
	}
	
}
