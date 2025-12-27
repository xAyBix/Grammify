package ma.supmti.grammify.ui;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

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
	public static JScrollPane mainScrollPane;
	public static CustomTextArea textArea;
	public static LineNumber lineNumber;
	public static StatusBar statusBar;
	private static Caret defaultCaret;
	
	public MainFrame() {
		
		// Interface's frame configuration
		setTitle(Constants.APP_NAME);
		setIconImage(Constants.IMG);
		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		// Add A Menu Bar at top
		setJMenuBar(new MenuBar());
		
		// Add Text Area
		textArea = new CustomTextArea();
		lineNumber = new LineNumber();
		statusBar = new StatusBar();
		mainScrollPane = new JScrollPane(textArea);
		mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
		mainScrollPane.setRowHeaderView(lineNumber);
		mainScrollPane.getRowHeader().setOpaque(true);
		mainScrollPane.getRowHeader().setBackground(Constants.secondaryColor);
		
		textArea.setEditable(false);
		defaultCaret = textArea.getCaret();
		hideCaret();
		add(mainScrollPane, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		
		
	}
	
	public static void showCaret () {
		textArea.setCaret(defaultCaret);
		lineNumber.setVisible(true);
		statusBar.update();
	}
	
	public static void hideCaret () {
		Caret invisibleCaret = new Caret () {
			@Override
			public void install(JTextComponent c) {}

			@Override
			public void deinstall(JTextComponent c) {}

			@Override
			public void paint(Graphics g) {}

			@Override
			public void addChangeListener(ChangeListener l) {}

			@Override
			public void removeChangeListener(ChangeListener l) {}

			@Override
			public boolean isVisible() {
				return false;
			}

			@Override
			public void setVisible(boolean v) {}

			@Override
			public boolean isSelectionVisible() {
				return false;
			}

			@Override
			public void setSelectionVisible(boolean v) {}

			@Override
			public void setMagicCaretPosition(Point p) {}

			@Override
			public Point getMagicCaretPosition() {
				return null;
			}

			@Override
			public void setBlinkRate(int rate) {}

			@Override
			public int getBlinkRate() {
				return 0;
			}

			@Override
			public int getDot() {
				return 0;
			}

			@Override
			public int getMark() {
				return 0;
			}

			@Override
			public void setDot(int dot) {}

			@Override
			public void moveDot(int dot) {}};
		textArea.setCaret(invisibleCaret);
		lineNumber.setVisible(false);
	}
	
	
}
