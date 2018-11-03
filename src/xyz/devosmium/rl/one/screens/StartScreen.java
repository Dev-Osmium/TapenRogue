package xyz.devosmium.rl.one.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.one.interfaces.IScreen;

public class StartScreen implements IScreen {

	@Override
	public void displayOutput(AsciiPanel terminal) {
		// TODO Auto-generated method stub
		terminal.write("Test",1,1);
		terminal.writeCenter("-- Press [enter] to start --", 22);
	}

	@Override
	public IScreen respondToUserInput(KeyEvent key) {
		// TODO Auto-generated method stub
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}

}
