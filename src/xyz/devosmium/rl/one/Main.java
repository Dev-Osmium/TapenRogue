package xyz.devosmium.rl.one;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.one.interfaces.IScreen;
import xyz.devosmium.rl.one.screens.StartScreen;

public class Main extends JFrame implements KeyListener{
	private AsciiPanel terminal;
	private IScreen screen;

    public Main(){
        super();
        terminal = new AsciiPanel();
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }


    public void repaint(){
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main app = new Main();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}

}
