package controller;

import controller.command.Command;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.util.Map;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{
    private static final long serialVersionUID = 227L;

    private MenuBuilder menuBuilder;

    public MenuController(Frame parent, Map<String, Command> commandMap)
    {
        this.menuBuilder = new MenuBuilder(parent, commandMap);

        initializeMenus();
    }

    private void initializeMenus()
    {
        add(menuBuilder.buildFileMenu());
        add(menuBuilder.buildViewMenu());

        Menu helpMenu = menuBuilder.buildHelpMenu();
        add(helpMenu);
        setHelpMenu(helpMenu);
    }
}
