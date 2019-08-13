// Copyright (C) 2019 Owen Salter <owen@devosmium.xyz>
// 
// This file is part of AsciiRogue.
// 
// AsciiRogue is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// AsciiRogue is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with AsciiRogue.  If not, see <http://www.gnu.org/licenses/>.

package xyz.devosmium.rl.AsciiRogue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import xyz.devosmium.rl.AsciiRogue.creatures.Creature;
import xyz.devosmium.rl.AsciiRogue.util.Point;
import xyz.devosmium.rl.AsciiRogue.items.Item;

public class World {
	private Tile[][][] tiles;

	private Item[][][] items;
	
	private int width;
	public int width() { return width; }
	
	private int height;
	public int height() { return height; }

	private int depth;
	public int depth() { return depth; }
	
	private List<Creature> creatures;
	
	public World(Tile[][][] tiles){
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
		this.depth = tiles[0][0].length;
		this.creatures = new ArrayList<Creature>();
		this.items = new Item[width][height][depth];
	}

	public Creature creature(int x, int y, int z){
		for (Creature c : creatures){
			if (c.x == x && c.y == y && c.z == z)
				return c;
		}
		return null;
	}

	public Item item(int x, int y, int z) {
		return items[x][y][z];
	}
	
	public Tile tile(int x, int y, int z){
		if (x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return tiles[x][y][z];
	}

	public char glyph(int x, int y, int z){
		Creature creature = creature(x, y, z);
		if (creature != null)
			return creature.glyph();
		
		if (item(x,y,z) != null)
			return item(x,y,z).glyph();
		
		return tile(x, y, z).glyph();
	}

	public Color color(int x, int y, int z){
		Creature creature = creature(x, y, z);
		if (creature != null)
			return creature.color();
		
		if (item(x,y,z) != null)
			return item(x,y,z).color();
		
		return tile(x, y, z).color();
	}

	public void dig(int x, int y, int z) {
		if (tile(x, y, z).isDiggable())
			tiles[x][y][z] = Tile.FLOOR;
	}
	
	public void addAtEmptyLocation(Creature creature, int z){
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		while (!tile(x,y,z).isGround() || creature(x,y,z) != null);
		
		creature.x = x;
		creature.y = y;
		creature.z = z;
		creatures.add(creature);
	}

	public void addAtEmptyLocation(Item item, int z){
		int x,y;

		System.out.println("Depth = " + z);
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		while (!tile(x,y,z).isGround() || item(x,y,z) != null);

		items[x][y][z] = item;

	}
	
	public void update(){
		List<Creature> toUpdate = new ArrayList<Creature>(creatures);
		for (Creature creature : toUpdate){
			creature.update();
		}
	}

	public void remove(Creature other) {
		creatures.remove(other);
	}


	public void addAtEmptySpace(Item item, int x, int y, int z){
		if (item == null)
			return;
		
		List<Point> points = new ArrayList<Point>();
		List<Point> checked = new ArrayList<Point>();
		
		points.add(new Point(x, y, z));
		
		while (!points.isEmpty()){
			Point p = points.remove(0);
			checked.add(p);
			
			if (!tile(p.x, p.y, p.z).isGround())
				continue;
			 
			if (items[p.x][p.y][p.z] == null){
				items[p.x][p.y][p.z] = item;
				Creature c = this.creature(p.x, p.y, p.z);
				if (c != null)
					c.notify("A %s lands between your feet.", item.name());
				return;
			} else {
				List<Point> neighbors = p.neighbors8();
				neighbors.removeAll(checked);
				points.addAll(neighbors);
			}
		}
	}

	public void remove(int x, int y, int z) {
		items[x][y][z] = null;
	}
	
}