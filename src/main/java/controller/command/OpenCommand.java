package main.java.controller.command;

import main.java.io.Accessor;
import main.java.io.XMLAccessor;
import main.java.logic.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static main.java.controller.MenuConstants.*;

public class OpenCommand implements Command {
    private Presentation presentation;
    private Frame parent;;

    public OpenCommand(Presentation presentation, Frame parent) {
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
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, FILE);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc,
                    LOADERR, JOptionPane.ERROR_MESSAGE);
        }

        parent.repaint();
    }
}
