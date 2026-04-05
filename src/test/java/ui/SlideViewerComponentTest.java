package ui;

import businesslogic.Presentation;
import businesslogic.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerComponentTest
{
    SlideViewerComponent slideViewerComponent;
    Slide slide;
    Presentation presentation;

    @BeforeEach
    void setUp()
    {
        presentation = new Presentation();
        slideViewerComponent = new SlideViewerComponent(presentation, null);
        slide = new Slide();
        slideViewerComponent.setSlide(slide);
    }

    @Test
    void getPreferredSize()
    {
        Dimension realPreferedSize = new Dimension(Slide.WIDTH, Slide.HEIGHT);
        Dimension testedPreferedSize = slideViewerComponent.getPreferredSize();
        assertEquals(realPreferedSize, testedPreferedSize);
    }

    @Test
    void update_nullData_slideShouldStayTheSame()
    {
        Slide oldSlide = slideViewerComponent.getSlide();
        slideViewerComponent.update(presentation, null);
        assertEquals(oldSlide, slideViewerComponent.getSlide());
    }

    @Test
    void update_nonNullData_slideShouldChange()
    {
        Slide newSlide = new Slide();
        slideViewerComponent.update(presentation, newSlide);
        assertEquals(newSlide, slideViewerComponent.getSlide());
    }
}