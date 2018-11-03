package xyz.devosmium.rl.one.mobs;

import java.awt.Color;

import xyz.devosmium.rl.one.Tile;
import xyz.devosmium.rl.one.World;

public class Creature {
	private World world;
	
	public int x;
	public int y;
	
	private char glyph;
	public char glyph() { return glyph; }
	
    private Color color;
    public Color color() { return color; }

    public Creature(World world, char glyph, Color color){
        this.world = world;
        this.glyph = glyph;
        this.color = color;
    }

    private CreatureAI ai;
	public void setCreatureAi(CreatureAI ai) { this.ai = ai; }

	public void moveBy(int mx, int my){
    	ai.onEnter(x+mx, y+my, world.tile(x+mx, y+my));
	}


	public void dig(int x, int y) {
		if (world.tile(x,y).isDiggable())
			world.tiles[x][y] = Tile.FLOOR;
	}

}
