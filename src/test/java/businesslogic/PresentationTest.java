package businesslogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.SlideViewerComponent;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PresentationTest
{
    Presentation presentation;
    Slide slide1;
    Slide slide2;
    Slide slide3;
    SlideViewerComponent observer;

    @BeforeEach
    void setUp()
    {
        presentation = new Presentation();
        slide1 = new Slide();
        slide2 = new Slide();
        slide3 = new Slide();
        JFrame jframe = new JFrame();
        observer = new SlideViewerComponent(presentation, jframe);
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
        ArrayList<PresentationObserver> observersBeforeAdding = presentation.getObservers();
        presentation.addObserver(observer);
        presentation.removeObserver(observer);
        assertEquals(observersBeforeAdding, presentation.getObservers());
    }

    @Test
    void removeObserver_whenObserversStartWith1Observer_shouldReturnToDefault()
    {
        PresentationObserver observerDefault = observer;
        presentation.addObserver(observerDefault);
        ArrayList<PresentationObserver> observersBeforeAdding = presentation.getObservers();
        presentation.addObserver(observer);
        presentation.removeObserver(observer);
        assertEquals(observersBeforeAdding, presentation.getObservers());
    }

    @Test
    void notifyObservers()
    {
        presentation.addObserver(observer);
        presentation.nextSlide();
        Slide slideAfterUpdate = observer.getSlide();
        Slide currentSlide = presentation.getCurrentSlide();
        assertEquals(currentSlide, slideAfterUpdate);
    }
}