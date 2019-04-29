package xyz.devosmium.rl.AsciiRogue;

import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import xyz.devosmium.rl.AsciiRogue.screens.*;

public class ApplicationMain extends JFrame implements KeyListener {
	
	private AsciiPanel terminal;
	private Screen screen;
	public static ApplicationMain mApp;
	
	public ApplicationMain(){
		super();
		System.out.println("INFO: Calling Main Constructor");
		System.out.println("Creating terminal");
		terminal = new AsciiPanel();
		add(terminal);
		System.out.println("Adding terminal...");
		pack();
		System.out.println("Packed windows");
		screen = new PlayScreen();
		System.out.println("Created playscreen");
		addKeyListener(this);
		System.out.println("Added keylistener");
		repaint();
		System.out.println("Repainting...");
		
	}
	
	@Override
	public void repaint(){
		terminal.clear();
		screen.displayOutput(terminal);
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
		mApp = app;
	}
}
