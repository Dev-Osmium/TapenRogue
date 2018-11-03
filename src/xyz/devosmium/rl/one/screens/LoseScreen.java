package xyz.devosmium.rl.one.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.one.interfaces.IScreen;

public class LoseScreen implements IScreen {

    public void displayOutput(AsciiPanel terminal) {
        terminal.write("You lost.", 1, 1);
        terminal.writeCenter("-- press [enter] to restart --", 22);
    }

    public IScreen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}