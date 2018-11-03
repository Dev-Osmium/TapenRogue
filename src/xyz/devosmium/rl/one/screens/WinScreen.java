package xyz.devosmium.rl.one.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.one.interfaces.IScreen;

public class WinScreen implements IScreen {

	@Override
	public void displayOutput(AsciiPanel terminal) {
		// TODO Auto-generated method stub
		terminal.write("You won",1,1);
		terminal.writeCenter("-- Press [enter] to restart --", 22);
	}

	@Override
	public IScreen respondToUserInput(KeyEvent key) {
		// TODO Auto-generated method stub
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}

}
