package controller.command;

import businesslogic.Presentation;
import businesslogic.testable.TestablePresentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {

    private ExitCommand exitCommand;
    private TestablePresentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new TestablePresentation();
        exitCommand = new ExitCommand(presentation);
    }

    @Test
    void getPresentation_withValidPresentation_returnsSamePresentation() {
        // Arrange
        Presentation expectedPresentation = presentation;

        // Act
        Presentation actualPresentation = exitCommand.getPresentation();

        // Assert
        assertSame(expectedPresentation, actualPresentation);
    }

    @Test
    void execute_whenCalled_callsExitOnPresentation() {
        // Act
        exitCommand.execute();

        // Assert
        assertTrue(presentation.wasExitCalled());
    }

    @Test
    void execute_whenCalled_callsExitWithStatusCodeZero() {
        // Act
        exitCommand.execute();

        // Assert
        assertEquals(0, presentation.getExitStatusCode());
    }

    @Test
    void execute_whenCalled_callsExitExactlyOnce() {
        // Act
        exitCommand.execute();

        // Assert
        assertEquals(1, presentation.getExitCallCount());
    }

    @Test
    void execute_whenCalledMultipleTimes_callsExitMultipleTimes() {
        // Act
        exitCommand.execute();
        exitCommand.execute();
        exitCommand.execute();

        // Assert
        assertEquals(3, presentation.getExitCallCount());
    }
}