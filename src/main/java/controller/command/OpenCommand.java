package main.java.controller.command;

import main.java.io.Accessor;
import main.java.io.XMLAccessor;
import main.java.logic.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static main.java.controller.MenuController.LOADERR;

public class OpenCommand implements Command {
    protected static final String LOADERR = "Load Error";
    protected static final String TESTFILE = "test.xml";

    private Presentation presentation;
    private Frame parent;
    private String fileName;

    public OpenCommand(Presentation presentation, Frame parent, String fileName) {
        this.presentation = presentation;
        this.parent = parent;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, fileName);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc,
                    LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }
}
