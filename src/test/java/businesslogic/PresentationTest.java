package businesslogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.SlideViewerComponent;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PresentationTest
{
    private Presentation presentation;
    private Slide slide1;
    private Slide slide2;
    private Slide slide3;

    @BeforeEach
    void setUp()
    {
        presentation = new Presentation();
        slide1 = new Slide();
        slide2 = new Slide();
        slide3 = new Slide();
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.append(slide3);
    }

    @Test
    void prevSlide_whenCurrentSlideIs1_shouldBe0()
    {
        presentation.setSlideNumber(1);
        presentation.prevSlide();
        int slideNumber = presentation.getSlideNumber();
        assertEquals(0, slideNumber);
    }

    @Test
    void prevSlide_whenCurrentSlideIs0_shouldBe0()
    {
        presentation.setSlideNumber(0);
        presentation.prevSlide();
        int slideNumber = presentation.getSlideNumber();
        assertEquals(0, slideNumber);
    }

    @Test
    void nextSlide_whenCurrentSlideIs0_shouldBe1()
    {
        presentation.setSlideNumber(0);
        presentation.nextSlide();
        int slideNumber = presentation.getSlideNumber();
        assertEquals(1, slideNumber);
    }

    @Test
    void nextSlide_whenCurrentSlideIs2_shouldBe2()
    {
        presentation.setSlideNumber(2);
        presentation.nextSlide();
        int slideNumber = presentation.getSlideNumber();
        assertEquals(2, slideNumber);
    }

    @Test
    void getSlide_inputMinus1_shouldReturnNull()
    {
        Slide slide = presentation.getSlide(-1);
        assertNull(slide);
    }

    @Test
    void getSlide_input0_shouldReturnslide1()
    {
        Slide slide = presentation.getSlide(0);
        assertEquals(slide1, slide);
    }

    @Test
    void getSlide_input2_shouldReturnslide3()
    {
        Slide slide = presentation.getSlide(2);
        assertEquals(slide3, slide);
    }

    @Test
    void getSlide_inputMinus3_shouldReturnNull()
    {
        Slide slide = presentation.getSlide(3);
        assertNull(slide);
    }

    @Test
    void removeObserver_whenObserversStartsEmpty_shouldReturnToDefault()
    {
        PresentationObserver observer = createDummyObserver();
        ArrayList<PresentationObserver> observersBeforeAdding = presentation.getObservers();
        presentation.addObserver(observer);
        presentation.removeObserver(observer);
        assertEquals(observersBeforeAdding, presentation.getObservers());
    }

    @Test
    void removeObserver_whenObserversStartWith1Observer_shouldReturnToDefault()
    {
        PresentationObserver observer = createDummyObserver();
        PresentationObserver observerDefault = observer;
        presentation.addObserver(observerDefault);
        ArrayList<PresentationObserver> observersBeforeAdding = presentation.getObservers();
        presentation.addObserver(observer);
        presentation.removeObserver(observer);
        assertEquals(observersBeforeAdding, presentation.getObservers());
    }

    @Test
    void notifyObservers_whenCurrentSlideChanges_updatesObserverWithCurrentSlide()
    {
        CapturingObserver observer = new CapturingObserver();
        presentation.addObserver(observer);
        presentation.nextSlide();
        Slide slideAfterUpdate = observer.slide;
        Slide currentSlide = presentation.getCurrentSlide();
        assertEquals(currentSlide, slideAfterUpdate);
    }


    @Test
    void clear()
    {
        presentation.clear();
        ArrayList<Slide> emptyArray = new ArrayList<Slide>();
        assertEquals(-1, presentation.getSlideNumber());
        assertEquals(emptyArray, presentation.getShowList());
    }

    @Test
    void append()
    {
        Slide newSlide = new Slide();
        assertFalse(presentation.getShowList().contains(newSlide));
        presentation.append(newSlide);
        assertTrue(presentation.getShowList().contains(newSlide));
    }

    @Test
    void addObserver()
    {
        PresentationObserver newObserver = createDummyObserver();
        assertFalse(presentation.getObservers().contains(newObserver));
        presentation.addObserver(newObserver);
        assertTrue(presentation.getObservers().contains(newObserver));

    }

    private PresentationObserver createDummyObserver()
    {
        return (presentation, data) ->
        {
            // no-op observer used for list-management tests
        };
    }


    private static class CapturingObserver implements PresentationObserver
    {
        private Slide slide;

        @Override
        public void update(Presentation presentation, Slide data)
        {
            this.slide = data;
        }
    }
}
