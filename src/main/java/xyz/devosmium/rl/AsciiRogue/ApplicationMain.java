package xyz.devosmium.rl.AsciiRogue;

import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import xyz.devosmium.rl.AsciiRogue.screens.*;

public class ApplicationMain extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1060623638149583738L;
	private AsciiPanel terminal;
	private Screen screen;
	private static Thread gameLoop;
	public static Thread gameLoop() { return gameLoop; }
	
	public ApplicationMain(){
		super();
		terminal = new AsciiPanel();
		this.gameLoop = new Thread(new PlayScreen(terminal));
		
		add(terminal);
		pack();
		screen = new PlayScreen(terminal);
		gameLoop.start();
		addKeyListener(this);
		repaint();
	}
	
	@Override
	public void repaint(){
		terminal.clear();
		super.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		screen = screen.respondToUserInput(e);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }
	
	public static void main(String[] args) {
		System.out.println("INFO: Starting up");
		ApplicationMain app = new ApplicationMain();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}
