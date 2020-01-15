// Copyright (C) 2019 Owen Salter <owen@devosmium.xyz>
// 
// This file is part of tapenrogue.
// 
// tapenrogue is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// tapenrogue is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with tapenrogue.  If not, see <http://www.gnu.org/licenses/>.

package xyz.devosmium.rl.tapenrogue;

import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.devosmium.rl.tapenrogue.screens.*;

public class ApplicationMain extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 8841027275550835912L;
	private AsciiPanel terminal;
	private Screen screen;
	public static ApplicationMain mApp;
	Logger logger;
	
	public ApplicationMain(){
		super();
		logger = LoggerFactory.getLogger("mainLogger");
		logger.info("Calling main constructor");
		System.out.println("Creating terminal");
		terminal = new AsciiPanel();
		add(terminal);
		System.out.println("Adding terminal...");
		pack();
		System.out.println("Packed windows");
		screen = new StartScreen();
		System.out.println("Created Starting Screen");
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
