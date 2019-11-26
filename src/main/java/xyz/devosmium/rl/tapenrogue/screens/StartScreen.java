package xyz.devosmium.rl.tapenrogue.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class StartScreen implements Screen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.clear();
        terminal.write("Welcome to Tapen", 1, 1);
        terminal.write("You stand at the entrance of a vast cave network.", 1, 2);
        terminal.write("Tales have gone around about the riches buried here", 1, 3);
        terminal.write("But there are worse things in this world than going destitute", 1, 4);
        terminal.write("Press <ENTER> to descend or <ESC> to return home.", 1, 5);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            return new PlayScreen();
        } else if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        return this;
    }
}