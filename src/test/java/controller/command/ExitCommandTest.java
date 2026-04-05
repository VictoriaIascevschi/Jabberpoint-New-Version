package controller.command;

import businesslogic.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExitCommandTest
{
    private ExitCommand exitCommand;
    @Mock
    private Presentation presentation;

    @BeforeEach
    void setUp()
    {
        exitCommand = new ExitCommand(presentation);
    }

    @Test
    void getPresentation_withValidPresentation_returnsSamePresentation()
    {
        // Arrange
        Presentation expectedPresentation = presentation;

        // Act
        Presentation actualPresentation = exitCommand.getPresentation();

        // Assert
        assertSame(expectedPresentation, actualPresentation);
    }

    @Test
    void execute_whenCalled_callsExitOnPresentation()
    {
        // Act
        exitCommand.execute();

        // Assert
        verify(presentation).exit(0);
    }

    @Test
    void execute_whenCalled_callsExitWithStatusCodeZero()
    {
        // Act
        exitCommand.execute();

        // Assert
        verify(presentation).exit(0);
    }

    @Test
    void execute_whenCalled_callsExitExactlyOnce()
    {
        // Act
        exitCommand.execute();

        // Assert
        verify(presentation, times(1)).exit(0);
    }

    @Test
    void execute_whenCalledMultipleTimes_callsExitMultipleTimes()
    {
        // Act
        exitCommand.execute();
        exitCommand.execute();
        exitCommand.execute();

        // Assert
        verify(presentation, times(3)).exit(0);
    }
}
