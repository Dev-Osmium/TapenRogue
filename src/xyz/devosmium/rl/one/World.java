package xyz.devosmium.rl.one;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import xyz.devosmium.rl.one.mobs.Creature;

public class World {
	public List<Creature> creatures;
	public Tile[][] tiles;
	private int width;
	public int width() { return width; }
	
	private int height;
	public int height() { return height; }
	
	public World(Tile[][] tiles) {
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
		this.creatures = new ArrayList();

	}

	public Creature creature(int x, int y){
		for (Creature c : creatures){
			if (c.x == x && c.y == y)
				return c;
    		}
    	return null;
	}

	public Tile tile(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height)
            return Tile.BOUNDS;
        else
            return tiles[x][y];
    }
	

	public char glyph(int x, int y){
		return tile(x, y).glyph();
    }


	public Color color(int x, int y){
        return tile(x, y).color();
    }

	
	public void addAtEmptyLocation(Creature creature){
	    int x;
	    int y;

	    do {
	        x = (int)(Math.random() * width);
	        y = (int)(Math.random() * height);
	    }
	    while (!tile(x,y).isGround());

	    creature.x = x;
	    creature.y = y;
	}

	



}
