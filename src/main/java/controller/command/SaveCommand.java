package controller.command;

import io.Accessor;
import io.XMLAccessor;
import businesslogic.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static controller.MenuConstants.*;

public class SaveCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public SaveCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    public Presentation getPresentation() {
        return this.presentation;
    }

    public Frame getParent() {
        return this.parent;
    }

    private void showSuccessMessage()
    {
        JOptionPane.showMessageDialog(parent,
                "Presentation saved successfully",
                "Save Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(Exception exc)
    {
        JOptionPane.showMessageDialog(parent, IOEX + exc,
                SAVEERR, JOptionPane.ERROR_MESSAGE);
    }

    private FileDialog getFileDialog() {
        FileDialog fileDialog = new FileDialog(parent, "Save Presentation", FileDialog.SAVE);
        fileDialog.setFile(SAVEFILE);
        fileDialog.setVisible(true);

        return fileDialog;
    }

    private String ensureXMLExtension(String fileName) {
        if (fileName == null) {
            return null;
        }

        if (!fileName.toLowerCase().endsWith(".xml")) {
            fileName += ".xml";
        }

        return fileName;
    }

    private String getFullPath() {
        FileDialog fileDialog = getFileDialog();

        String fileName = fileDialog.getFile();
        String directory = fileDialog.getDirectory();

        if (directory == null || fileName == null) {
            return null;
        }

        fileName = ensureXMLExtension(fileName);

        return directory + fileName;
    }

    @Override
    public void execute() {
        Accessor xmlAccessor = new XMLAccessor();

        String fullPath = getFullPath();

        if (fullPath == null) {
            return; // user cancelled -> do nothing
        }

        try {
            xmlAccessor.saveFile(presentation, fullPath);
            showSuccessMessage();
        } catch (IOException exc) {
            showErrorMessage(exc);
        }
    }
}