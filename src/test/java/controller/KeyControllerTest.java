package controller;

import controller.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeyControllerTest
{
    private KeyController keyController;
    private Map<Integer, Command> commandMap;
    @Mock
    private Command mockCommand;
    @Mock
    private Command anotherCommand;

    @BeforeEach
    void setUp() {
        commandMap = new HashMap<>();
        keyController = new KeyController(commandMap);
    }

    @Test
    void constructor_withValidCommandMap_storesCommandMap()
    {
        // Arrange
        Map<Integer, Command> expectedMap = commandMap;

        // Act
        KeyController controller = new KeyController(expectedMap);

        // Assert
        assertSame(expectedMap, controller.commandMap);
    }

    @Test
    void keyPressed_whenKeyCodeExistsInMap_executesCorrespondingCommand()
    {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        commandMap.put(keyCode, mockCommand);
        KeyEvent keyEvent = createKeyPressedEvent(keyCode);

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        verify(mockCommand).execute();
    }

    @Test
    void keyPressed_whenKeyCodeExistsInMap_executesOnlyOnce()
    {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        commandMap.put(keyCode, mockCommand);
        KeyEvent keyEvent = createKeyPressedEvent(keyCode);

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void keyPressed_whenKeyCodeDoesNotExistInMap_doesNotExecuteAnyCommand()
    {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        // commandMap is empty
        KeyEvent keyEvent = createKeyPressedEvent(keyCode);

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        verifyNoInteractions(mockCommand);
    }

    @Test
    void keyPressed_whenMultipleKeyCodesExist_executesOnlyMatchingCommand()
    {
        // Arrange
        int enterKeyCode = KeyEvent.VK_ENTER;
        int spaceKeyCode = KeyEvent.VK_SPACE;
        commandMap.put(enterKeyCode, mockCommand);
        commandMap.put(spaceKeyCode, anotherCommand);
        KeyEvent keyEvent = createKeyPressedEvent(enterKeyCode);

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        verify(mockCommand).execute();
    }

    @Test
    void keyPressed_whenMultipleKeyCodesExist_doesNotExecuteNonMatchingCommand()
    {
        // Arrange
        int enterKeyCode = KeyEvent.VK_ENTER;
        int spaceKeyCode = KeyEvent.VK_SPACE;
        commandMap.put(enterKeyCode, mockCommand);
        commandMap.put(spaceKeyCode, anotherCommand);
        KeyEvent keyEvent = createKeyPressedEvent(enterKeyCode);

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        verifyNoInteractions(anotherCommand);
    }

    @Test
    void keyPressed_withDifferentKeyCodes_executesCorrectCommandForEachKey()
    {
        // Arrange
        int enterKeyCode = KeyEvent.VK_ENTER;
        int spaceKeyCode = KeyEvent.VK_SPACE;
        commandMap.put(enterKeyCode, mockCommand);
        commandMap.put(spaceKeyCode, anotherCommand);

        KeyEvent enterEvent = createKeyPressedEvent(enterKeyCode);
        // Act
        keyController.keyPressed(enterEvent);

        // Assert
        verify(mockCommand).execute();
    }

    @Test
    void keyPressed_withDifferentKeyCodes_executesCorrectCommandForSpaceKey()
    {
        // Arrange
        int enterKeyCode = KeyEvent.VK_ENTER;
        int spaceKeyCode = KeyEvent.VK_SPACE;
        commandMap.put(enterKeyCode, mockCommand);
        commandMap.put(spaceKeyCode, anotherCommand);

        KeyEvent spaceEvent = createKeyPressedEvent(spaceKeyCode);

        // Act
        keyController.keyPressed(spaceEvent);

        // Assert
        verify(anotherCommand).execute();
    }

    @Test
    void keyPressed_whenCommandIsNullInMap_doesNotThrowException()
    {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        commandMap.put(keyCode, null);
        KeyEvent keyEvent = createKeyPressedEvent(keyCode);

        // Act and Assert
        assertDoesNotThrow(() -> keyController.keyPressed(keyEvent));
    }

    @Test
    void keyPressed_whenKeyEventHasNoModifiers_stillExecutesCommand()
    {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        commandMap.put(keyCode, mockCommand);
        KeyEvent keyEvent = createKeyPressedEvent(keyCode);

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        verify(mockCommand).execute();
    }

    private KeyEvent createKeyPressedEvent(int keyCode)
    {
        return new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, ' ');
    }
}
