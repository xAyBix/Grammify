package ma.supmti.grammify;

import java.awt.Dimension;
import java.awt.Toolkit;

/** 
 * Contains all the static final attributes used frequently across
 * the application
 * 
 * <p>This class is not meant to be instantiated or inherited.</p>
 * 
 * @author Akram BELBEKRI
 * @author Rihab AMEUR
 * @author Hidaya EL ARBAOUI
 * 
 * @since 2025-11-15 21:40
 */

public final class Constants {
	
	// A private constructor to avoid instantiation
	private Constants () {}
	
	// The main application name
	public static final String APP_NAME = "Grammify";
	
	// Screen dimensions
	public static final Dimension SCREEN_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double SCREEN_HEIGHT = SCREEN_DIMENSIONS.getHeight();
	public static final double SCREEN_WIDTH = SCREEN_DIMENSIONS.getWidth();
	
	// Default application frame dimensions (set to 90% of screen dimensions)
	public static final int FRAME_WIDTH = (int) (SCREEN_WIDTH * 0.9);
	public static final int FRAME_HEIGHT = (int) (SCREEN_HEIGHT * 0.9);
}
