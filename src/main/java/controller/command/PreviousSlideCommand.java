package controller.command;

import businesslogic.Presentation;

public class PreviousSlideCommand implements Command
{
    private Presentation presentation;

    public PreviousSlideCommand(Presentation presentation)
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
        presentation.prevSlide();
    }
}
