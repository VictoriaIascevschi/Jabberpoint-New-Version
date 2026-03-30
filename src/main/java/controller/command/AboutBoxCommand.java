package main.java.controller.command;

import main.java.ui.AboutBox;

import java.awt.*;

public class AboutBoxCommand implements Command {
    private Frame parent;

    public AboutBoxCommand(Frame parent) {
        this.parent = parent;
    }

    public Frame getParent() {
        return this.parent;
    }

    @Override
    public void execute() {
        AboutBox.show(parent);
    }
}
