package ma.supmti.grammify;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.JFrame;


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
		
		// Interface's frame configuration
		JFrame appFrame = new JFrame(Constants.APP_NAME);
		appFrame.setIconImage(Constants.img);
		appFrame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.setLocationRelativeTo(null);
		appFrame.setVisible(true);
		

	}

}
