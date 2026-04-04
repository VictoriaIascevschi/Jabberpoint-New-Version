package controller.command;

import io.Accessor;
import io.XMLAccessor;
import businesslogic.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

    private FileDialog getFileDialog() {
        FileDialog fileDialog = new FileDialog(parent, "Open Presentation", FileDialog.LOAD);

        fileDialog.setFile("*.xml");
        fileDialog.setVisible(true);

        return fileDialog;
    }

    private String getFullPath() {
        FileDialog fileDialog = getFileDialog();

        String fileName = fileDialog.getFile();
        String directory = fileDialog.getDirectory();

        if (directory == null || fileName == null) {
            return null;  // User cancelled
        }

        return directory + fileName;
    }

    private void showErrorMessage(Exception exc) {
        JOptionPane.showMessageDialog(parent,
                "IO Exception: " + exc,
                "Load Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void execute() {
        String fullPath = getFullPath();

        if (fullPath == null) {
            return;  // User cancelled, do nothing
        }

        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();

        try {
            xmlAccessor.loadFile(presentation, fullPath);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            showErrorMessage(exc);
        }

        parent.repaint();
    }
}
