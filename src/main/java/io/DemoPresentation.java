package main.java.io;

import main.java.businesslogic.Presentation;
import main.java.businesslogic.Slide;
import main.java.businesslogic.SlideItemFactory;

/**
 * A built-in demo-presentation
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation extends Accessor
{

    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Demo main.java.logic.Presentation");
        Slide slide;
        slide = new Slide();
        SlideItemFactory factory = new SlideItemFactory();
        slide.setTitle("main.java.app.JabberPoint");
        slide.append(factory.createSlideItem(1, "The Java main.java.logic.Presentation Tool"));
        slide.append(factory.createSlideItem(2, "Copyright (c) 1996-2000: Ian Darwin"));
        slide.append(factory.createSlideItem(2, "Copyright (c) 2000-now:"));
        slide.append(factory.createSlideItem(2, "Gert Florijn andn Sylvia Stuurman"));
        slide.append(factory.createSlideItem(4, "Starting main.java.app.JabberPoint without a filename"));
        slide.append(factory.createSlideItem(4, "shows this presentation"));
        slide.append(factory.createSlideItem(1, "Navigate:"));
        slide.append(factory.createSlideItem(3, "Next slide: PgDn or Enter"));
        slide.append(factory.createSlideItem(3, "Previous slide: PgUp or up-arrow"));
        slide.append(factory.createSlideItem(3, "Quit: q or Q"));
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Demonstration of levels and stijlen");
        slide.append(factory.createSlideItem(1, "Level 1"));
        slide.append(factory.createSlideItem(2, "Level 2"));
        slide.append(factory.createSlideItem(1, "Again level 1"));
        slide.append(factory.createSlideItem(1, "Level 1 has style number 1"));
        slide.append(factory.createSlideItem(2, "Level 2 has style number  2"));
        slide.append(factory.createSlideItem(3, "This is how level 3 looks like"));
        slide.append(factory.createSlideItem(4, "And this is level 4"));
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("The third slide");
        slide.append(factory.createSlideItem(1, "To open a new presentation,"));
        slide.append(factory.createSlideItem(2, "use File->Open from the menu."));
        slide.append(factory.createSlideItem(1, " "));
        slide.append(factory.createSlideItem(1, "This is the end of the presentation."));
        SlideItemFactory slideItemFactory = new SlideItemFactory();

        slide.append(slideItemFactory.createSlideItem("image", 1, "main.java.app.JabberPoint.jpg"));
        slide.append(slideItemFactory.createSlideItem("text", 1, "Some text"));

        presentation.append(slide);
    }

    public void saveFile(Presentation presentation, String unusedFilename) throws IllegalArgumentException
    {
        throw new IllegalStateException("Save As->Demo! called");
    }
}
