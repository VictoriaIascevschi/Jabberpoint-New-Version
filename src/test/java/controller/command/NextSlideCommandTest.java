package controller.command;

import controller.command.NextSlideCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import businesslogic.Presentation;
import businesslogic.Slide;

import static org.junit.jupiter.api.Assertions.*;

class NextSlideCommandTest
{
    private Presentation realPresentation;
    private NextSlideCommand nextSlideCommand;

    @BeforeEach
    void setUp()
    {
        realPresentation = new Presentation();

        // Adding slides to the presentation
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        realPresentation.append(slide1);
        realPresentation.append(slide2);

        nextSlideCommand = new NextSlideCommand(realPresentation);
    }

    @Test
    void execute_advancesToNextSlide()
    {
        int initialSlide = realPresentation.getSlideNumber();

        nextSlideCommand.execute();

        assertEquals(initialSlide + 1, realPresentation.getSlideNumber());
    }

    @Test
    void getPresentation_returnsPresentation()
    {
        assertSame(realPresentation, nextSlideCommand.getPresentation());
    }
}