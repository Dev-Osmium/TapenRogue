package xyz.devosmium.rl.asciirogue.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class LoseScreen implements Screen {
	private AsciiPanel terminal;
	
	public LoseScreen(AsciiPanel terminal){
		this.terminal = terminal;
	}
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.write("You lost.", 1, 1);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen(terminal) : this;
	}
}
