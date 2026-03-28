package main.java.controller.command;

import main.java.io.Accessor;
import main.java.io.XMLAccessor;
import main.java.logic.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SaveCommand implements Command {
    private Presentation presentation;
    private Frame parent;
    private String fileName;

    public SaveCommand(Presentation presentation, Frame parent, String filename) {
        this.presentation = presentation;
        this.parent = parent;
        this.fileName = filename;
    }

    @Override
    public void execute() {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, fileName);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception" + exc,
                    "Save error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
