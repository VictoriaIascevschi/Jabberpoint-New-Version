package controller.command;

import businesslogic.Presentation;

import java.awt.*;

public class NewPresentationCommand implements Command
{
    private Presentation presentation;
    private Frame parent;

    public NewPresentationCommand(Presentation presentation, Frame parent)
    {
        this.presentation = presentation;
        this.parent = parent;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    public Frame getParent()
    {
        return this.parent;
    }

    @Override
    public void execute()
    {
        presentation.clear();
        if (parent != null)
        {
            parent.repaint();
        }
    }
}
