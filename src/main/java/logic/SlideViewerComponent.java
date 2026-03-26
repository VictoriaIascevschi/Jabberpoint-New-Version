package main.java.logic;

import java.awt.*;

public class SlideViewerComponent implements PresentationObserver
{
    private Slide slide;
    private static long serialVersionUID = 227L;
    private static String FONTNAME = "Dialog";
    private static int FONTSTYLE = Font.BOLD;
    private static int FONTHEIGHT = 10;
    private static int XPOS = 1100;
    private static int YPOS = 20;

    public SlideViewerComponent(Slide slide)
    {
        this.slide = slide;
    }

    public Dimension getPreferredSize()
    {

    }

    public void paintComponent(Graphics g)
    {

    }

    @Override
    public void update(Presentation presentation, Slide data)
    {

    }
}
