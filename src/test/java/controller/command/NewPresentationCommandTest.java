package controller.command;

import businesslogic.Presentation;
import businesslogic.testable.TestableFrame;
import businesslogic.testable.TestablePresentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class NewPresentationCommandTest {

    private NewPresentationCommand command;
    private TestablePresentation presentation;
    private TestableFrame parent;

    @BeforeEach
    void setUp() {
        presentation = new TestablePresentation();
        parent = new TestableFrame();
        command = new NewPresentationCommand(presentation, parent);
    }

    @Test
    void getPresentation_withValidPresentation_returnsSamePresentation() {
        // Arrange
        Presentation expectedPresentation = presentation;

        // Act
        Presentation actualPresentation = command.getPresentation();

        // Assert
        assertSame(expectedPresentation, actualPresentation);
    }

    @Test
    void getParent_withValidParent_returnsSameParent() {
        // Arrange
        Frame expectedParent = parent;

        // Act
        Frame actualParent = command.getParent();

        // Assert
        assertSame(expectedParent, actualParent);
    }

    @Test
    void execute_whenCalled_callsClearOnPresentation() {
        // Act
        command.execute();

        // Assert
        assertTrue(presentation.wasClearCalled());
    }

    @Test
    void execute_whenCalled_callsRepaintOnParent() {
        // Act
        command.execute();

        // Assert
        assertTrue(parent.wasRepaintCalled());
    }

    @Test
    void execute_whenCalled_callsClearExactlyOnce() {
        // Act
        command.execute();

        // Assert
        assertEquals(1, presentation.getClearCallCount());
    }

    @Test
    void execute_whenCalled_callsRepaintExactlyOnce() {
        // Act
        command.execute();

        // Assert
        assertEquals(1, parent.getRepaintCallCount());
    }

    @Test
    void execute_whenCalledMultipleTimes_callsClearMultipleTimes() {
        // Act
        command.execute();
        command.execute();
        command.execute();

        // Assert
        assertEquals(3, presentation.getClearCallCount());
    }

    @Test
    void execute_whenCalledMultipleTimes_callsRepaintMultipleTimes() {
        // Act
        command.execute();
        command.execute();
        command.execute();

        // Assert
        assertEquals(3, parent.getRepaintCallCount());
    }
}