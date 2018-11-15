package xyz.devosmium.rl.AsciiRogue.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.AsciiRogue.ApplicationMain;

public class StartScreen implements Screen {

	private AsciiPanel terminal;
	public StartScreen(AsciiPanel terminal) {
		this.terminal = terminal;
	}
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("-- press [enter] to start --", 22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_ENTER) {
			ApplicationMain.gameLoop().start();
		}
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen(terminal) : this;
	}
}
