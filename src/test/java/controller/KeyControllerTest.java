package controller;

import controller.command.Command;
import controller.command.testable.TestableCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class KeyControllerTest {

    private KeyController keyController;
    private Map<Integer, Command> commandMap;
    private TestableCommand mockCommand;
    private TestableCommand anotherCommand;

    @BeforeEach
    void setUp() {
        commandMap = new HashMap<>();
        mockCommand = new TestableCommand();
        anotherCommand = new TestableCommand();
        keyController = new KeyController(commandMap);
    }

    @Test
    void constructor_withValidCommandMap_storesCommandMap() {
        // Arrange
        Map<Integer, Command> expectedMap = commandMap;

        // Act
        KeyController controller = new KeyController(expectedMap);

        // Assert
        assertSame(expectedMap, controller.commandMap);
    }

    @Test
    void keyPressed_whenKeyCodeExistsInMap_executesCorrespondingCommand() {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        commandMap.put(keyCode, mockCommand);
        KeyEvent keyEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, keyCode, ' ');

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        assertTrue(mockCommand.wasExecuted());
    }

    @Test
    void keyPressed_whenKeyCodeExistsInMap_executesOnlyOnce() {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        commandMap.put(keyCode, mockCommand);
        KeyEvent keyEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, keyCode, ' ');

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        assertEquals(1, mockCommand.getExecutionCount());
    }

    @Test
    void keyPressed_whenKeyCodeDoesNotExistInMap_doesNotExecuteAnyCommand() {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        // commandMap is empty
        KeyEvent keyEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, keyCode, ' ');

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        assertFalse(mockCommand.wasExecuted());
    }

    @Test
    void keyPressed_whenMultipleKeyCodesExist_executesOnlyMatchingCommand() {
        // Arrange
        int enterKeyCode = KeyEvent.VK_ENTER;
        int spaceKeyCode = KeyEvent.VK_SPACE;
        commandMap.put(enterKeyCode, mockCommand);
        commandMap.put(spaceKeyCode, anotherCommand);
        KeyEvent keyEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, enterKeyCode, ' ');

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        assertTrue(mockCommand.wasExecuted());
    }

    @Test
    void keyPressed_whenMultipleKeyCodesExist_doesNotExecuteNonMatchingCommand() {
        // Arrange
        int enterKeyCode = KeyEvent.VK_ENTER;
        int spaceKeyCode = KeyEvent.VK_SPACE;
        commandMap.put(enterKeyCode, mockCommand);
        commandMap.put(spaceKeyCode, anotherCommand);
        KeyEvent keyEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, enterKeyCode, ' ');

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        assertFalse(anotherCommand.wasExecuted());
    }

    @Test
    void keyPressed_withDifferentKeyCodes_executesCorrectCommandForEachKey() {
        // Arrange
        int enterKeyCode = KeyEvent.VK_ENTER;
        int spaceKeyCode = KeyEvent.VK_SPACE;
        commandMap.put(enterKeyCode, mockCommand);
        commandMap.put(spaceKeyCode, anotherCommand);

        KeyEvent enterEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, enterKeyCode, ' ');
        KeyEvent spaceEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, spaceKeyCode, ' ');

        // Act
        keyController.keyPressed(enterEvent);

        // Assert
        assertTrue(mockCommand.wasExecuted());
    }

    @Test
    void keyPressed_withDifferentKeyCodes_executesCorrectCommandForSpaceKey() {
        // Arrange
        int enterKeyCode = KeyEvent.VK_ENTER;
        int spaceKeyCode = KeyEvent.VK_SPACE;
        commandMap.put(enterKeyCode, mockCommand);
        commandMap.put(spaceKeyCode, anotherCommand);

        KeyEvent spaceEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, spaceKeyCode, ' ');

        // Act
        keyController.keyPressed(spaceEvent);

        // Assert
        assertTrue(anotherCommand.wasExecuted());
    }

    @Test
    void keyPressed_whenCommandIsNullInMap_doesNotThrowException() {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        commandMap.put(keyCode, null);
        KeyEvent keyEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, keyCode, ' ');

        // Act and Assert
        assertDoesNotThrow(() -> keyController.keyPressed(keyEvent));
    }

    @Test
    void keyPressed_whenKeyEventHasNoModifiers_stillExecutesCommand() {
        // Arrange
        int keyCode = KeyEvent.VK_ENTER;
        commandMap.put(keyCode, mockCommand);
        KeyEvent keyEvent = new KeyEvent(new java.awt.Frame(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, keyCode, ' ');

        // Act
        keyController.keyPressed(keyEvent);

        // Assert
        assertTrue(mockCommand.wasExecuted());
    }
}