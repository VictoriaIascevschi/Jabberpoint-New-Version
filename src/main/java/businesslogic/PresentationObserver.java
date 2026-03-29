package main.java.businesslogic;

import main.java.businesslogic.Presentation;
import main.java.businesslogic.Slide;

public interface PresentationObserver
{
    public void update(Presentation presentation, Slide data);
}
