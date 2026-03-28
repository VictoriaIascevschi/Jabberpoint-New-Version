package main.java.controller;
import main.java.controller.command.Command;
import main.java.controller.command.ExitCommand;
import main.java.controller.command.NextSlideCommand;
import main.java.controller.command.PreviousSlideCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.HashMap;
import java.util.Map;

/** <p>This is the main.java.controller.KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class KeyController extends KeyAdapter {
	private Map<Integer, Command> commandMap = new HashMap<>();

	public KeyController(Map<Integer, Command> commandMap) {
		this.commandMap = commandMap;
	}

	public void keyPressed(KeyEvent keyEvent) {
		Command command = commandMap.get(keyEvent.getKeyCode());
		if (command != null) {
			command.execute();
		}
	}

//	public void keyPressed(KeyEvent keyEvent) {
//		Command command = commandMap.get(keyEvent.getKeyCode());
//
//		switch(keyEvent.getKeyCode()) {
//			case KeyEvent.VK_PAGE_DOWN:
//			case KeyEvent.VK_DOWN:
//			case KeyEvent.VK_ENTER:
//			case '+':
//				if (command != null) {
//					command.execute();
//				}
//				break;
//			case KeyEvent.VK_PAGE_UP:
//			case KeyEvent.VK_UP:
//			case '-':
//				if (command != null) {
//					command.execute();
//				}
//				break;
//			case 'q':
//			case 'Q':
////				this.command = new ExitCommand();
////				executeCommand();
//				break; // Probably never reached!!
//			default:
//				break;
//		}
//	}
}
