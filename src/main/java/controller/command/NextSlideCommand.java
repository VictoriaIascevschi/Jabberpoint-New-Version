package main.java.controller.command;

import main.java.businesslogic.Presentation;

public class NextSlideCommand implements Command {
    private Presentation presentation;

    public NextSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    public Presentation getPresentation() {
        return this.presentation;
    }

    @Override
    public void execute() {
        presentation.nextSlide();
    }
}
