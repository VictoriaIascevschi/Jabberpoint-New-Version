package controller.command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import businesslogic.Presentation;
import businesslogic.Slide;

import static org.junit.jupiter.api.Assertions.*;

class PreviousSlideCommandTest {

    private Presentation presentation;
    private PreviousSlideCommand previousSlideCommand;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();

        // Adding slides to the presentation
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        Slide slide3 = new Slide();
        Slide slide4 = new Slide();

        presentation.append(slide1);
        presentation.append(slide2);
        presentation.append(slide3);
        presentation.append(slide4);

        // The command
        previousSlideCommand = new PreviousSlideCommand(presentation);
    }

    @Test
    void execute_goesToPreviousSlide() {
        // Arrange
        presentation.setSlideNumber(1);

        // Act
        previousSlideCommand.execute();

        // Assert
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void execute_doesNotGoBeforeFirstSlide() {
        // Arrange
        presentation.setSlideNumber(0);

        // Act
        previousSlideCommand.execute();

        // Assert
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void execute_WorksFromMiddleSlide() {
        // Arrange
        presentation.setSlideNumber(2);

        // Act
        previousSlideCommand.execute();

        // Assert
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void execute_movesBackMultipleSlides() {
        // Arrange
        presentation.setSlideNumber(3);

        // Act
        previousSlideCommand.execute();
        previousSlideCommand.execute();

        // Assert
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void execute_multipleCalls_stopsAtFirstSlide() {
        // Arrange
        presentation.setSlideNumber(1);

        // Act
        previousSlideCommand.execute();
        previousSlideCommand.execute();
        previousSlideCommand.execute();

        // Assert
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void execute_handlesOneSlide() {
        // Arrange
        Presentation singleSlidePresentation = new Presentation();
        Slide onlySlide = new Slide();
        singleSlidePresentation.append(onlySlide);

        PreviousSlideCommand command = new PreviousSlideCommand(singleSlidePresentation);

        singleSlidePresentation.setSlideNumber(0);

        // Act
        command.execute();

        // Assert
        assertEquals(0, singleSlidePresentation.getSlideNumber());
    }

    @Test
    void execute_onePresentation_throwsNoException() {
        // Arrange
        Presentation emptyPresentation = new Presentation();
        PreviousSlideCommand command = new PreviousSlideCommand(emptyPresentation);

        // Act and Assert
        assertDoesNotThrow(() -> command.execute());
    }

    @Test
    void getPresentation_returnsCorrectPresentation() {
        assertSame(presentation, previousSlideCommand.getPresentation());
    }

    @Test
    void getPresentation_returnsSameInstance() {
        // Arrange
        Presentation retrievedPresentation = previousSlideCommand.getPresentation();

        // Assert
        assertSame(presentation, retrievedPresentation);
    }

    @Test
    void execute_movesFromSlide2ToSlide1() {
        // Arrange
        presentation.setSlideNumber(1);

        // Act
        previousSlideCommand.execute();

        // Assert
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void execute_movesFromSlide3ToSlide2() {
        // Arrange
        presentation.setSlideNumber(2);

        // Act
        previousSlideCommand.execute();

        // Assert
        assertEquals(1, presentation.getSlideNumber());
    }
}