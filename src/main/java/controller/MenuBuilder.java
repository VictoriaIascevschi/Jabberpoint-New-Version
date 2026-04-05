package controller;

import controller.command.Command;

import java.awt.*;
import java.util.Map;

import static controller.MenuConstants.FILE;
import static controller.MenuConstants.*;

public class MenuBuilder {
    private Frame parent;
    private Map<String, Command> commandMap;

    public MenuBuilder(Frame parent, Map<String, Command> commandMap) {
        this.parent = parent;
        this.commandMap = commandMap;
    }

    public Menu buildFileMenu() {
        Menu fileMenu = new Menu(FILE);

        fileMenu.add(createMenuItem("Open", commandMap.get(OPEN)));
        fileMenu.add(createMenuItem("New", commandMap.get(NEW)));
        fileMenu.add(createMenuItem("Save", commandMap.get(SAVE)));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem("Exit", commandMap.get(EXIT)));

        return fileMenu;
    }

    public Menu buildViewMenu() {
        Menu viewMenu = new Menu(VIEW);

        viewMenu.add(createMenuItem("Next", commandMap.get(NEXT)));
        viewMenu.add(createMenuItem("Previous", commandMap.get(PREV)));
        viewMenu.add(createMenuItem("Go to", commandMap.get(GOTO)));

        return viewMenu;
    }

    public Menu buildHelpMenu() {
        Menu helpMenu = new Menu(HELP);

        helpMenu.add(createMenuItem("About", commandMap.get(ABOUT)));

        return helpMenu;
    }

    private MenuItem createMenuItem(String label, Command command) {
        MenuItem menuItem = new MenuItem(label);
        menuItem.addActionListener(e -> command.execute());

        return menuItem;
    }
}
