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

package xyz.devosmium.rl.tapenrogue.creatures;

import java.util.List;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.tapenrogue.World;
import xyz.devosmium.rl.tapenrogue.creatures.ai.*;
import xyz.devosmium.rl.tapenrogue.util.FieldOfView;

public class CreatureFactory {
	private World world;

	/**
	 * Creates a new Factory for Creatures
	 * @param world The world to generate the creatures in
	 * @author Owen Salter
	 * @since 0.7.3
	 * @see Creature
	 */
	public CreatureFactory(World world){
		this.world = world;
	}

	/**
	 * Creates a new Player, with a message buffer and FOV calculator
	 * @param messages Message buffer
	 * @param fov FOV calculator
	 * @return The Player object
	 * @author Owen Salter
	 * @since 0.5.0
	 * @see PlayerAi
	 */
	public Creature newPlayer(List<String> messages, FieldOfView fov){
		Creature player = new Creature(world, '@', AsciiPanel.brightWhite, "player", 100, 20, 5);
		world.addAtEmptyLocation(player, 0);
		new PlayerAi(player, messages, fov);
		return player;
	}

	/**
	 * Creates a new fungus at the specified depth
	 * @param depth The z-level to generate at
	 * @return the Fungus object
	 * @author Owen Salter
	 * @since 0.5.0
	 * @see FungusAi
	 */
	public Creature newFungus(int depth){
		Creature fungus = new Creature(world, 'f', AsciiPanel.green, "fungus", 10, 0, 0);
		world.addAtEmptyLocation(fungus, depth);
		new FungusAi(fungus, this);
		return fungus;
	}

	/**
	 * Creates a new bat at the specified depth
	 * @param depth The z-level to generate at
	 * @return The Bat object
	 * @author Owen Salter
	 * @since 0.5.0
	 * @see BatAi
	 */
	public Creature newBat(int depth){
		Creature bat = new Creature(world, 'b', AsciiPanel.yellow, "bat", 15, 5, 0);
		world.addAtEmptyLocation(bat, depth);
		new BatAi(bat);
		return bat;
	}

	/**
	 * Creates a new Zombie at the specified depth and targeting the specified Player
	 * @param depth The z-level to generate at
	 * @param player the Player to target with Pathfinding
	 * @author Owen Salter
	 * @since 0.5.0
	 * @see ZombAi
	 */
	public Creature newZombie(int depth, Creature player) {
		Creature zombie = new Creature(world, 'z', AsciiPanel.red, "zombie", 15,5,0);
		world.addAtEmptyLocation(zombie, depth);
		new ZombAi(zombie, player);
		return zombie;
	}


}