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

package xyz.devosmium.rl.AsciiRogue.creatures.ai;

import xyz.devosmium.rl.AsciiRogue.Tile;
import xyz.devosmium.rl.AsciiRogue.creatures.Creature;
import xyz.devosmium.rl.AsciiRogue.util.Line;
import xyz.devosmium.rl.AsciiRogue.util.Point;

public class CreatureAi {
	protected Creature creature;
	
	public CreatureAi(Creature creature){
		this.creature = creature;
		this.creature.setCreatureAi(this);
	}

	/**
	 *
	 * @param x Current x coordinate
	 * @param y Current y coordinate
	 * @param z Current z coordinate
	 * @param tile The current tile
	 */
	public void onEnter(int x, int y, int z, Tile tile){
		if (tile.isGround()) {
			creature.x = x;
			creature.y = y;
			creature.z = z;
		} else {
			creature.doAction("bump into a wall");
		}
	}
	
	public void onUpdate(){
	}
	
	public void onNotify(String message) {
		
	}

	/**
	 *
	 * @param wx The X-coordinate of the checked tile
	 * @param wy The Y-coordinate of the checked tile
	 * @param wz The Z-coordinate of the checked tile
	 * @return Whether or not the creature can see the tile
	 */
	public boolean canSee(int wx, int wy, int wz) {
		if (creature.z != wz)
			return false;

		if ((creature.x-wx)*(creature.x-wx) + (creature.y-wy)*(creature.y-wy) > creature.visionRadius()*creature.visionRadius())
			return false;

		for (Point p : new Line(creature.x, creature.y, wx, wy)){
			if (creature.tile(p.x, p.y, wz).isGround() || p.x == wx && p.y == wy)
				continue;

			return false;
		}

		return true;
	}

	public void wander() {
		int mx = (int)(Math.random() * 3) - 1;
		int my = (int)(Math.random() * 3) - 1;
		Creature other = creature.creature(creature.x + mx, creature.y + my, creature.z);
		if (other != null && other.glyph() == creature.glyph()) {
			return;
		} else {
			creature.moveBy(mx, my, 0);
		}
	}
}
