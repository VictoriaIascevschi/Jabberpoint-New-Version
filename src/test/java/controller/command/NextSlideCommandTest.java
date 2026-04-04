package controller.command;

import businesslogic.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NextSlideCommandTest {

    private Presentation mockPresentation;
    private NextSlideCommand nextSlideCommand;

    @BeforeEach
    void setUp() {
        // Create a mock of Presentation
        mockPresentation = mock(Presentation.class);

        // Create the command with the mock
        nextSlideCommand = new NextSlideCommand(mockPresentation);
    }

    @Test
    void execute_ShouldCallNextSlideOnPresentation() {
        // Act
        nextSlideCommand.execute();

        // Assert
        verify(mockPresentation, times(1)).nextSlide();
    }

    @Test
    void getPresentationShouldReturnTheCorrectPresentation() {
        // Act & Assert
        assertEquals(mockPresentation, nextSlideCommand.getPresentation());
    }

    @Test
    void getPresentationShouldReturnSameInstance() {
        // Act
        Presentation retrievedPresentation = nextSlideCommand.getPresentation();

        // Assert
        assertSame(mockPresentation, retrievedPresentation);
    }

    @Test
    void executeShouldWorkMultipleTimes() {
        // Act
        nextSlideCommand.execute();
        nextSlideCommand.execute();
        nextSlideCommand.execute();

        // Assert
        verify(mockPresentation, times(3)).nextSlide();
    }
}