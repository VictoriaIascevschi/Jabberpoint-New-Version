package controller;
import controller.command.Command;
import controller.command.ExitCommand;
import controller.command.NextSlideCommand;
import controller.command.PreviousSlideCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>This is the main.java.controller.KeyController (KeyListener)</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class KeyController extends KeyAdapter {
	Map<Integer, Command> commandMap = new HashMap<>();

	public KeyController(Map<Integer, Command> commandMap) {
		this.commandMap = commandMap;
	}

	public void keyPressed(KeyEvent keyEvent) {
		Command command = commandMap.get(keyEvent.getKeyCode());
		if (command != null) {
			command.execute();
		}
	}
}
