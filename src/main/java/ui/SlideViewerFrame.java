package main.java.ui;

import main.java.controller.command.*;
import main.java.logic.Presentation;
import main.java.controller.KeyController;
import main.java.controller.MenuController;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

import static main.java.controller.MenuConstants.*;

/**
 * <p>The application window for a slideviewcomponent</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class SlideViewerFrame extends JFrame {
	private static final long serialVersionUID = 3227L;
	
	private static final String JABTITLE = "Jabberpoint 1.6 - OU";
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	
	public SlideViewerFrame(String title, Presentation presentation) {
		super(title);
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
		presentation.setShowView(slideViewerComponent);
		setupWindow(slideViewerComponent, presentation);
	}

// Setup GUI
	public void setupWindow(SlideViewerComponent
			slideViewerComponent, Presentation presentation) {
		setTitle(JABTITLE);
		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		getContentPane().add(slideViewerComponent);

		// Adding all key commands to a map, linking each key event to a certain command
		Map<Integer, Command> commandMap = new HashMap<>();

		commandMap.put(KeyEvent.VK_PAGE_DOWN, new NextSlideCommand(presentation));
		commandMap.put(KeyEvent.VK_DOWN, new NextSlideCommand(presentation));
		commandMap.put(KeyEvent.VK_ENTER, new NextSlideCommand(presentation));

		commandMap.put(KeyEvent.VK_PAGE_UP, new PreviousSlideCommand(presentation));
		commandMap.put(KeyEvent.VK_UP, new PreviousSlideCommand(presentation));

		commandMap.put(KeyEvent.VK_Q, new ExitCommand(presentation));

		addKeyListener(new KeyController(commandMap)); // adding the KEY CONTROLLER to the key listener

		// Adding all menu commands to a map, linking each menu item to a certain command
		Map<String, Command> menuCommandMap = new HashMap<>();
		menuCommandMap.put(OPEN, new OpenCommand(presentation, this));
		menuCommandMap.put(NEW, new NewPresentationCommand(presentation, this));
		menuCommandMap.put(SAVE, new SaveCommand(presentation, this));
		menuCommandMap.put(EXIT, new ExitCommand(presentation));
		menuCommandMap.put(NEXT, new NextSlideCommand(presentation));
		menuCommandMap.put(PREV, new PreviousSlideCommand(presentation));
		menuCommandMap.put(GOTO, new GoToSlideCommand(presentation, this));
		menuCommandMap.put(ABOUT, new AboutBoxCommand(this));

		setMenuBar(new MenuController(this, menuCommandMap));	// adding the MENU CONTROLLER to the menu bar
		setSize(new Dimension(WIDTH, HEIGHT)); // Same sizes as main.java.logic.Slide has.
		setVisible(true);
	}
}
