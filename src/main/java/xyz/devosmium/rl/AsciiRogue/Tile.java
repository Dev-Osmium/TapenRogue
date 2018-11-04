package xyz.devosmium.rl.AsciiRogue;

import java.awt.Color;
import asciiPanel.AsciiPanel;

public enum Tile {
	// Basic Tiles
	FLOOR((char)250, AsciiPanel.yellow),
	WALL((char)177, AsciiPanel.yellow),
	BOUNDS('x', AsciiPanel.brightBlack),
	// Staircase
	STAIRS_UP('>', AsciiPanel.white),
	STAIRS_DOWN('<', AsciiPanel.white),
	// Unknown Tiles
	UNKNOWN(' ', AsciiPanel.white);
	
	private char glyph;
	public char glyph() { return glyph; }
	
	private Color color;
	public Color color() { return color; }
	
	Tile(char glyph, Color color){
		this.glyph = glyph;
		this.color = color;
	}

	public boolean isGround() {
		return this != WALL && this != BOUNDS;
	}

	public boolean isDiggable() {
		return this == Tile.WALL;
	}
}
