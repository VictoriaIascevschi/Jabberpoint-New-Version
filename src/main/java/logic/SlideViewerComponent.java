package main.java.logic;

import java.awt.*;
import java.io.Serializable;

public class SlideViewerComponent implements PresentationObserver, Serializable
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

    public Slide getSlide()
    {
        return this.slide;
    }

    public void setSlide(Slide slide)
    {
        this.slide = slide;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID)
    {
        SlideViewerComponent.serialVersionUID = serialVersionUID;
    }

    public static String getFONTNAME()
    {
        return FONTNAME;
    }

    public static void setFONTNAME(String FONTNAME)
    {
        SlideViewerComponent.FONTNAME = FONTNAME;
    }

    public static int getFONTSTYLE()
    {
        return FONTSTYLE;
    }

    public static void setFONTSTYLE(int FONTSTYLE)
    {
        SlideViewerComponent.FONTSTYLE = FONTSTYLE;
    }

    public static int getFONTHEIGHT()
    {
        return FONTHEIGHT;
    }

    public static void setFONTHEIGHT(int FONTHEIGHT)
    {
        SlideViewerComponent.FONTHEIGHT = FONTHEIGHT;
    }

    public static int getXPOS()
    {
        return XPOS;
    }

    public static void setXPOS(int XPOS)
    {
        SlideViewerComponent.XPOS = XPOS;
    }

    public static int getYPOS()
    {
        return YPOS;
    }

    public static void setYPOS(int YPOS)
    {
        SlideViewerComponent.YPOS = YPOS;
    }

    public Dimension getPreferredSize()
    {
        //todo ???
    }

    public void paintComponent(Graphics g)
    {
        //todo ???
    }

    @Override
    public void update(Slide data)
    {
        this.slide = data;
    }
}
