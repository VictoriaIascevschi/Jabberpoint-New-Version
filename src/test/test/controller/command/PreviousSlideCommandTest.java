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
        // Create a real presentation
        presentation = new Presentation();

        // Add slides so we can navigate
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        Slide slide3 = new Slide();
        Slide slide4 = new Slide();

        presentation.append(slide1);
        presentation.append(slide2);
        presentation.append(slide3);
        presentation.append(slide4);

        // Create the command
        previousSlideCommand = new PreviousSlideCommand(presentation);
    }

    @Test
    @DisplayName("execute() should go to previous slide when not at first slide")
    void executeShouldGoToPreviousSlide() {
        // Start at slide 2 (index 1)
        presentation.setSlideNumber(1);

        // Act
        previousSlideCommand.execute();

        // Assert - should be at slide 1 (index 0)
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    @DisplayName("execute() should not go before first slide")
    void executeShouldNotGoBeforeFirstSlide() {
        // Start at first slide (index 0)
        presentation.setSlideNumber(0);

        // Act
        previousSlideCommand.execute();

        // Assert - should stay at first slide
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    @DisplayName("execute() should work correctly from middle slide")
    void executeShouldWorkFromMiddleSlide() {
        // Start at slide 3 (index 2)
        presentation.setSlideNumber(2);

        // Act
        previousSlideCommand.execute();

        // Assert - should go to slide 2 (index 1)
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    @DisplayName("Multiple execute() calls should move back multiple slides")
    void multipleExecutesShouldMoveBackMultipleSlides() {
        // Start at slide 4 (index 3)
        presentation.setSlideNumber(3);

        // Act - execute twice
        previousSlideCommand.execute(); // Goes to slide 3 (index 2)
        previousSlideCommand.execute(); // Goes to slide 2 (index 1)

        // Assert
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    @DisplayName("execute() should stop at first slide even with multiple calls")
    void executeShouldStopAtFirstSlideWithMultipleCalls() {
        // Start at slide 2 (index 1)
        presentation.setSlideNumber(1);

        // Act - execute three times (only one should actually move)
        previousSlideCommand.execute(); // Goes to slide 1 (index 0)
        previousSlideCommand.execute(); // Should stay at slide 1
        previousSlideCommand.execute(); // Should stay at slide 1

        // Assert
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    @DisplayName("execute() should handle presentation with single slide")
    void executeShouldHandleSingleSlide() {
        // Create new presentation with only one slide
        Presentation singleSlidePresentation = new Presentation();
        Slide onlySlide = new Slide();
        singleSlidePresentation.append(onlySlide);

        PreviousSlideCommand command = new PreviousSlideCommand(singleSlidePresentation);

        // Start at the only slide (index 0)
        singleSlidePresentation.setSlideNumber(0);

        // Act
        command.execute();

        // Assert - should stay at the only slide
        assertEquals(0, singleSlidePresentation.getSlideNumber());
    }

    @Test
    @DisplayName("execute() should handle presentation with no slides gracefully")
    void executeShouldHandleEmptyPresentation() {
        // Create presentation with no slides
        Presentation emptyPresentation = new Presentation();
        PreviousSlideCommand command = new PreviousSlideCommand(emptyPresentation);

        // Act & Assert - should not throw exception
        // Note: Behavior depends on how prevSlide() handles empty presentation
        assertDoesNotThrow(() -> command.execute());
    }

    @Test
    @DisplayName("getPresentation() should return the correct presentation")
    void getPresentationShouldReturnCorrectPresentation() {
        assertSame(presentation, previousSlideCommand.getPresentation());
    }

    @Test
    @DisplayName("getPresentation() should return the same instance passed to constructor")
    void getPresentationShouldReturnSameInstance() {
        Presentation retrievedPresentation = previousSlideCommand.getPresentation();

        assertSame(presentation, retrievedPresentation);
    }

    @Test
    @DisplayName("Command should work after presentation changes")
    void executeShouldWorkAfterPresentationChanges() {
        // Start at slide 2 (index 1)
        presentation.setSlideNumber(1);

        // Execute to go to slide 1
        previousSlideCommand.execute();
        assertEquals(0, presentation.getSlideNumber());

        // Manually go to slide 3
        presentation.setSlideNumber(2);

        // Execute to go back to slide 2
        previousSlideCommand.execute();
        assertEquals(1, presentation.getSlideNumber());
    }
}