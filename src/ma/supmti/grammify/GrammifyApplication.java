package ma.supmti.grammify;

import javax.swing.JFrame;


/**
 * 
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-15 21:30
 */
public class GrammifyApplication {
	public static void main(String[] args) {
		
		// Interface's frame configuration
		JFrame appFrame = new JFrame(Constants.APP_NAME);
		appFrame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.setLocationRelativeTo(null);
		appFrame.setVisible(true);
		

	}

}
