package controller.command;

import businesslogic.Presentation;
import org.junit.jupiter.api.Test;

import java.awt.Frame;
import java.awt.HeadlessException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GoToSlideCommandTest
{
    @Test
    void getPresentation_whenValidPresentationProvided_returnsSamePresentation()
    {
        Presentation presentation = new Presentation();
        GoToSlideCommand command = new GoToSlideCommand(presentation, null);

        assertSame(presentation, command.getPresentation());
    }

    @Test
    void getParent_whenParentIsNull_returnsNull()
    {
        Presentation presentation = new Presentation();
        GoToSlideCommand command = new GoToSlideCommand(presentation, (Frame) null);

        assertNull(command.getParent());
    }

    @Test
    void execute_whenRunningHeadless_throwsHeadlessException()
    {
        Presentation presentation = new Presentation();
        GoToSlideCommand command = new GoToSlideCommand(presentation, null);

        assertThrows(HeadlessException.class, command::execute);
    }
}
