package controller.command;

import businesslogic.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewPresentationCommandTest
{
    private NewPresentationCommand command;
    @Mock
    private Presentation presentation;
    @Mock
    private Frame parent;

    @BeforeEach
    void setUp()
    {
        command = new NewPresentationCommand(presentation, parent);
    }

    @Test
    void getPresentation_withValidPresentation_returnsSamePresentation()
    {
        // Arrange
        Presentation expectedPresentation = presentation;

        // Act
        Presentation actualPresentation = command.getPresentation();

        // Assert
        assertSame(expectedPresentation, actualPresentation);
    }

    @Test
    void getParent_withValidParent_returnsSameParent()
    {
        // Arrange
        Frame expectedParent = parent;

        // Act
        Frame actualParent = command.getParent();

        // Assert
        assertSame(expectedParent, actualParent);
    }

    @Test
    void execute_whenCalled_callsClearOnPresentation()
    {
        // Act
        command.execute();

        // Assert
        verify(presentation).clear();
    }

    @Test
    void execute_whenCalled_repaintsParent()
    {
        // Act
        command.execute();

        // Assert
        verify(parent).repaint();
    }

    @Test
    void execute_whenCalled_callsClearExactlyOnce()
    {
        // Act
        command.execute();

        // Assert
        verify(presentation, times(1)).clear();
    }

    @Test
    void execute_whenCalledMultipleTimes_callsClearMultipleTimes()
    {
        // Act
        command.execute();
        command.execute();
        command.execute();

        // Assert
        verify(presentation, times(3)).clear();
    }

    @Test
    void execute_whenCalledMultipleTimes_repaintsParentMultipleTimes()
    {
        // Act
        command.execute();
        command.execute();
        command.execute();

        // Assert
        verify(parent, times(3)).repaint();
    }

    @Test
    void execute_whenParentIsNull_doesNotThrow()
    {
        NewPresentationCommand commandWithNullParent = new NewPresentationCommand(presentation, null);

        // Act + Assert
        assertDoesNotThrow(commandWithNullParent::execute);
        verify(presentation).clear();
    }
}
