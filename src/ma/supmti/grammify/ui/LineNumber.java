package ma.supmti.grammify.ui;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

import ma.supmti.grammify.Constants;

import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A line number at the left side of the text area
 * 
 * 
 * @author Akram BELBEKRI
 * 
 * @since 2025-12-13 15:00
 */
public class LineNumber extends JComponent implements DocumentListener {

	public LineNumber() {
		MainFrame.textArea.getDocument().addDocumentListener(this);
		MainFrame.textArea.setMargin(new Insets(0, 5, 0, 0));

		setFont(MainFrame.textArea.getFont());
		setForeground(Constants.thirdColor());
	}

	@Override
	public Dimension getPreferredSize() {
		Element root = MainFrame.textArea.getDocument().getDefaultRootElement();
		int lineCount = root.getElementCount();
		int digits = String.valueOf(lineCount).length();

		FontMetrics fm = getFontMetrics(getFont());
		int width = fm.charWidth('0') * digits + 10;

		return new Dimension(width, Integer.MAX_VALUE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		FontMetrics fm = getFontMetrics(getFont());
		Rectangle clip = g.getClipBounds();

		int startOffset = MainFrame.textArea.viewToModel2D(new Point(0, clip.y));
		int endOffset = MainFrame.textArea.viewToModel2D(new Point(0, clip.y + clip.height));

		Element root = MainFrame.textArea.getDocument().getDefaultRootElement();

		int startLine = root.getElementIndex(startOffset);
		int endLine = root.getElementIndex(endOffset);

		for (int line = startLine; line <= endLine; line++) {
			Element lineElem = root.getElement(line);
			try {
				Rectangle r = MainFrame.textArea.modelToView2D(lineElem.getStartOffset()).getBounds();
				int y = r.y + r.height - fm.getDescent();
				g.drawString(String.valueOf(line + 1), 5, y);
			} catch (BadLocationException ignored) {
			}
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		repaint();
		revalidate();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		repaint();
		revalidate();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		repaint();
		revalidate();
	}
}
