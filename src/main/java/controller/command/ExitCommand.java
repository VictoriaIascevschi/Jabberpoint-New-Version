package controller.command;

import businesslogic.Presentation;

public class ExitCommand implements Command
{
    private Presentation presentation;

    public ExitCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    @Override
    public void execute()
    {
        presentation.exit(0);
    }
}

