package controller.command;

import io.ReadAccessor;
import io.AccessorFactory;
import businesslogic.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpenCommand implements Command
{
    private Presentation presentation;
    private Frame parent;
    private AccessorFactory accessorFactory;

    public OpenCommand(Presentation presentation, Frame parent)
    {
        this(presentation, parent, new AccessorFactory());
    }

    public OpenCommand(Presentation presentation, Frame parent, AccessorFactory accessorFactory)
    {
        this.presentation = presentation;
        this.parent = parent;
        this.accessorFactory = accessorFactory;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    public Frame getParent()
    {
        return this.parent;
    }

    private FileDialog getFileDialog()
    {
        FileDialog fileDialog = new FileDialog(parent, "Open Presentation", FileDialog.LOAD);

        fileDialog.setFile("*.xml");
        fileDialog.setVisible(true);

        return fileDialog;
    }

    private String getFullPath()
    {
        FileDialog fileDialog = getFileDialog();

        String fileName = fileDialog.getFile();
        String directory = fileDialog.getDirectory();

        if (directory == null || fileName == null)
        {
            return null;  // User cancelled
        }

        return directory + fileName;
    }

    private void showErrorMessage(Exception exc)
    {
        JOptionPane.showMessageDialog(parent,
                "IO Exception: " + exc,
                "Load Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void execute()
    {
        String fullPath = getFullPath();

        if (fullPath == null)
        {
            return;  // User cancelled, do nothing
        }

        presentation.clear();
        try
        {
            ReadAccessor readAccessor = accessorFactory.createReader(fullPath);
            readAccessor.loadFile(presentation, fullPath);
            presentation.setSlideNumber(0);
        } catch (IOException exc)
        {
            showErrorMessage(exc);
        }

        parent.repaint();
    }
}
