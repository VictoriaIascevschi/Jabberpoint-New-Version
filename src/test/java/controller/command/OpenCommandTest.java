package controller.command;

import businesslogic.Presentation;
import org.junit.jupiter.api.Test;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OpenCommandTest
{
    @Test
    void getPresentation_whenValidPresentationProvided_returnsSamePresentation()
    {
        Presentation presentation = new Presentation();
        OpenCommand command = new OpenCommand(presentation, null);

        assertSame(presentation, command.getPresentation());
    }

    @Test
    void getParent_whenParentIsNull_returnsNull()
    {
        Presentation presentation = new Presentation();
        OpenCommand command = new OpenCommand(presentation, (Frame) null);

        assertNull(command.getParent());
    }

    @Test
    void execute_whenRunningHeadless_throwsHeadlessException()
    {
        Presentation presentation = new Presentation();
        OpenCommand command = new OpenCommand(presentation, null);

        assertThrows(HeadlessException.class, command::execute);
    }

    @Test
    void getFullPath_whenRunningHeadless_throwsHeadlessException()
    {
        Presentation presentation = new Presentation();
        OpenCommand command = new OpenCommand(presentation, null);

        assertThrows(HeadlessException.class, () -> invokePrivate(command, "getFullPath"));
    }

    @Test
    void showErrorMessage_whenRunningHeadless_throwsHeadlessException()
    {
        Presentation presentation = new Presentation();
        OpenCommand command = new OpenCommand(presentation, null);

        assertThrows(HeadlessException.class, () -> invokePrivate(command, "showErrorMessage", Exception.class, new IOException("io")));
    }

    private Object invokePrivate(Object target, String methodName, Class<?> parameterType, Object argument)
    {
        try
        {
            Method method = target.getClass().getDeclaredMethod(methodName, parameterType);
            method.setAccessible(true);

            return method.invoke(target, argument);
        }
        catch (InvocationTargetException ex)
        {
            if (ex.getCause() instanceof RuntimeException runtimeException)
            {
                throw runtimeException;
            }
            throw new RuntimeException(ex.getCause());
        }
        catch (ReflectiveOperationException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    private Object invokePrivate(Object target, String methodName)
    {
        try
        {
            Method method = target.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);

            return method.invoke(target);
        }
        catch (InvocationTargetException ex)
        {
            if (ex.getCause() instanceof RuntimeException runtimeException)
            {
                throw runtimeException;
            }
            throw new RuntimeException(ex.getCause());
        }
        catch (ReflectiveOperationException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
