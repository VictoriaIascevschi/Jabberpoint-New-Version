package controller.command;

import businesslogic.Presentation;

public class NextSlideCommand implements Command
{
    private Presentation presentation;

    public NextSlideCommand(Presentation presentation)
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
        presentation.nextSlide();
    }
}
