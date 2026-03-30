package main.java.controller.command;

import main.java.io.Accessor;
import main.java.io.XMLAccessor;
import main.java.businesslogic.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static main.java.controller.MenuConstants.*;

public class SaveCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public SaveCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        FileDialog fd = new FileDialog(parent, "Save Presentation", FileDialog.SAVE);
        fd.setVisible(true);

        String filename = fd.getFile();

        if (filename == null) {
            return;
        }

        String fullPath = fd.getDirectory() + filename;

        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, fullPath);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc,
                    SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }
}