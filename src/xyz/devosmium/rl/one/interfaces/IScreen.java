package xyz.devosmium.rl.one.interfaces;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public interface IScreen {
	public void displayOutput(AsciiPanel terminal);
	
	public IScreen respondToUserInput(KeyEvent key);
}
