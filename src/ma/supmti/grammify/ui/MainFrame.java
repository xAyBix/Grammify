package ma.supmti.grammify.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

import ma.supmti.grammify.Constants;
import ma.supmti.grammify.GrammifyApplication;
import ma.supmti.grammify.io.FileManager;



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
	
	private JPanel welcomePanel;
    private JPanel editorPanel;
    private boolean isWelcomeScreenVisible = true;
    
    public MainFrame() {
        // Interface's frame configuration
        setTitle(Constants.APP_NAME);
        setIconImage(Constants.IMG);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Add A Menu Bar at top
        setJMenuBar(new MenuBar());
        
        // Create the editor panel (but don't show it yet)
        createEditorPanel();
        
        // Create and show the welcome screen
        createWelcomeScreen();
        
        // Set the welcome screen as the initial content
        add(welcomePanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private void createEditorPanel() {
        editorPanel = new JPanel(new BorderLayout());
        
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
        
        editorPanel.add(mainScrollPane, BorderLayout.CENTER);
        editorPanel.add(statusBar, BorderLayout.SOUTH);
    }
    
    private void createWelcomeScreen() {
        welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(Constants.secondaryColor);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 20, 10, 20);
        
        // App Icon/Logo
        JLabel iconLabel = new JLabel();
        if (Constants.IMG != null) {
            ImageIcon scaledIcon = new ImageIcon(Constants.IMG.getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            iconLabel.setIcon(scaledIcon);
        }
        gbc.gridy = 0;
        welcomePanel.add(iconLabel, gbc);
        
        // App Title
        JLabel titleLabel = new JLabel(Constants.APP_NAME);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        welcomePanel.add(titleLabel, gbc);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Your Modern Text Editor");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(180, 180, 180));
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 20, 30, 20);
        welcomePanel.add(subtitleLabel, gbc);
        
        // Action Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 0, 15));
        buttonsPanel.setOpaque(false);
        
        JButton newFileBtn = createWelcomeButton("New File", "Start with a blank document", e -> {
            switchToEditor();
            FileManager.newFile();
        });
        
        JButton openFileBtn = createWelcomeButton("Open File", "Open an existing document", e -> {
            switchToEditor();
            FileManager.openFile();
        });
        
        JButton recentFilesBtn = createWelcomeButton("Recent Files", "Access recently opened files", e -> {
            switchToEditor();
            // Add your recent files logic here
        });
        
        buttonsPanel.add(newFileBtn);
        buttonsPanel.add(openFileBtn);
        buttonsPanel.add(recentFilesBtn);
        
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 20, 20, 20);
        welcomePanel.add(buttonsPanel, gbc);
        
        // Keyboard shortcuts hint
        JLabel hintLabel = new JLabel("<html><center>Press <b>Ctrl+N</b> for new file or <b>Ctrl+O</b> to open</center></html>");
        hintLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        hintLabel.setForeground(new Color(140, 140, 140));
        gbc.gridy = 4;
        gbc.insets = new Insets(30, 20, 10, 20);
        welcomePanel.add(hintLabel, gbc);
    }
    
    private JButton createWelcomeButton(String title, String description, ActionListener action) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout(10, 5));
        button.setPreferredSize(new Dimension(350, 70));
        button.setBackground(new Color(45, 45, 48));
        button.setBorder(new EmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        
        // Description
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(new Color(180, 180, 180));
        
        JPanel textPanel = new JPanel(new BorderLayout(0, 5));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(descLabel, BorderLayout.CENTER);
        
        button.add(textPanel, BorderLayout.CENTER);
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(60, 60, 65));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(45, 45, 48));
            }
        });
        
        button.addActionListener(action);
        
        return button;
    }
    
    /**
     * Switches from welcome screen to the text editor
     */
    public static void switchToEditor() {
        MainFrame frame = GrammifyApplication.mainFrame;
        if (frame.isWelcomeScreenVisible) {
            frame.remove(frame.welcomePanel);
            frame.add(frame.editorPanel, BorderLayout.CENTER);
            frame.isWelcomeScreenVisible = false;
            frame.revalidate();
            frame.repaint();
        }
    }
    
    /**
     * Shows the welcome screen (useful for "Close All" functionality)
     */
    public static void switchToWelcome() {
        MainFrame frame = GrammifyApplication.mainFrame;
        if (!frame.isWelcomeScreenVisible) {
            frame.remove(frame.editorPanel);
            frame.add(frame.welcomePanel, BorderLayout.CENTER);
            frame.isWelcomeScreenVisible = true;
            textArea.setEditable(false);
            hideCaret();
            frame.revalidate();
            frame.repaint();
        }
    }
    
    public static void showCaret() {
        textArea.setCaret(defaultCaret);
        lineNumber.setVisible(true);
        statusBar.update();
        switchToEditor();
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
