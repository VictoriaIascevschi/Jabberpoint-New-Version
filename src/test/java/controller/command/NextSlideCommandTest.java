package controller.command;

import controller.command.NextSlideCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import businesslogic.Presentation;
import businesslogic.Slide;

import static org.junit.jupiter.api.Assertions.*;

class NextSlideCommandTest {

    private Presentation realPresentation;
    private NextSlideCommand nextSlideCommand;

    @BeforeEach
    void setUp() {
        // Use a real Presentation object
        realPresentation = new Presentation();

        // Add some slides so we can test navigation
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        realPresentation.append(slide1);
        realPresentation.append(slide2);

        nextSlideCommand = new NextSlideCommand(realPresentation);
    }

    @Test
    void executeShouldAdvanceToNextSlide() {
        int initialSlide = realPresentation.getSlideNumber();

        nextSlideCommand.execute();

        assertEquals(initialSlide + 1, realPresentation.getSlideNumber());
    }

    @Test
    void getPresentationShouldReturnPresentation() {
        assertSame(realPresentation, nextSlideCommand.getPresentation());
    }
}