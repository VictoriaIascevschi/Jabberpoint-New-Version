package io;

import businesslogic.Presentation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DemoPresentationTest
{
    @Test
    void loadFile_whenCalled_setsPresentationTitle()
    {
        DemoPresentation demoPresentation = new DemoPresentation();
        Presentation presentation = new Presentation();

        demoPresentation.loadFile(presentation, "");

        assertEquals("Demo main.java.logic.Presentation", presentation.getTitle());
    }

    @Test
    void loadFile_whenCalled_appendsThreeSlides()
    {
        DemoPresentation demoPresentation = new DemoPresentation();
        Presentation presentation = new Presentation();

        demoPresentation.loadFile(presentation, "");

        assertEquals(3, presentation.getSize());
    }

    @Test
    void loadFile_whenCalled_setsFirstSlideTitle()
    {
        DemoPresentation demoPresentation = new DemoPresentation();
        Presentation presentation = new Presentation();

        demoPresentation.loadFile(presentation, "");

        assertEquals("main.java.app.JabberPoint", presentation.getSlide(0).getTitle());
    }

    @Test
    void saveFile_whenCalled_throwsIllegalStateException()
    {
        DemoPresentation demoPresentation = new DemoPresentation();
        Presentation presentation = new Presentation();

        assertThrows(IllegalStateException.class, () -> demoPresentation.saveFile(presentation, ""));
    }
}
