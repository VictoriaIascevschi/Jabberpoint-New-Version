package main.java.controller.command;

import main.java.logic.Presentation;

import javax.swing.*;
import java.awt.*;

import static main.java.controller.command.MenuConstants.PAGENR;

public class GoToSlideCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public GoToSlideCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    public Presentation getPresentation() {
        return this.presentation;
    }

    public Frame getParent() {
        return this.parent;
    }

    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog(parent, (Object)PAGENR);
        if (pageNumberStr != null) {
            try {
                int pageNumber = Integer.parseInt(pageNumberStr);
                presentation.setSlideNumber(pageNumber - 1);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent,
                        "Invalid page number",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
