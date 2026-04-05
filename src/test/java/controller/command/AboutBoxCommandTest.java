package controller.command;

import org.junit.jupiter.api.Test;

import java.awt.Frame;
import java.awt.HeadlessException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AboutBoxCommandTest
{
    @Test
    void getParent_whenParentIsNull_returnsNull()
    {
        AboutBoxCommand command = new AboutBoxCommand((Frame) null);

        assertNull(command.getParent());
    }

    @Test
    void execute_whenRunningHeadless_throwsHeadlessException()
    {
        AboutBoxCommand command = new AboutBoxCommand((Frame) null);

        assertThrows(HeadlessException.class, command::execute);
    }
}
