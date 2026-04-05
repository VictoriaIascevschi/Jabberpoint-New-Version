package ui;

import businesslogic.PresentationObserver;
import businesslogic.Slide;
import businesslogic.Presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 * <p>main.java.ui.SlideViewerComponent is a graphical component that can show slides.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent implements PresentationObserver
{
 	private Presentation presentation;
	private Slide slide; // current slide
	private Font labelFont = null; // font for labels
	private static final Color COLOR = Color.black;
	private static final long serialVersionUID = 227L;
	
	private static final Color BGCOLOR = Color.white;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;
	private JFrame frame = null;

    public SlideViewerComponent(Presentation presentation, JFrame frame)
    {
        setBackground(BGCOLOR);
        this.presentation = presentation;
        this.slide = presentation.getCurrentSlide();
        labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
        this.frame = frame;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    public void setPresentation(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public Slide getSlide()
    {
        return this.slide;
    }

    public void setSlide(Slide slide)
    {
        this.slide = slide;
    }

    public Font getLabelFont()
    {
        return this.labelFont;
    }

    public void setLabelFont(Font labelFont)
    {
        this.labelFont = labelFont;
    }

    public JFrame getFrame()
    {
        return this.frame;
    }

    public void setFrame(JFrame frame)
    {
        this.frame = frame;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    // draw the slide
    public void paintComponent(Graphics graphics)
    {
        graphics.setColor(BGCOLOR);
        graphics.fillRect(0, 0, getSize().width, getSize().height);

        if (presentation.getSlideNumber() < 0 || slide == null)
        {
            return;
        }

        graphics.setFont(labelFont);
        graphics.setColor(COLOR);
        graphics.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
                presentation.getSize(), XPOS, YPOS);

        Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
        slide.draw(graphics, area, this);
    }

    @Override
    public void update(Presentation presentation, Slide data)
    {
        if (data == null)
        {
            repaint();

            return;
        }

        this.slide = data;
        
        repaint();

        if (frame != null)
        {
            frame.setTitle(presentation.getTitle());
        }

    }
}
