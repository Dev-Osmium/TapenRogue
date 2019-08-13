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

import xyz.devosmium.rl.AsciiRogue.creatures.Creature;
import xyz.devosmium.rl.AsciiRogue.creatures.CreatureFactory;

public class FungusAi extends CreatureAi {
	private CreatureFactory factory;
	private int spreadcount;
	
	public FungusAi(Creature creature, CreatureFactory factory) {
		super(creature);
		this.factory = factory;
	}

	public void onUpdate(){
		if (spreadcount < 5 && Math.random() < 0.01)
			spread();
	}
	
	private void spread(){
		int x = creature.x + (int)(Math.random() * 11) - 5;
		int y = creature.y + (int)(Math.random() * 11) - 5;
		
		if (!creature.canEnter(x, y, creature.z))
			return;
		
		creature.doAction("spawn a child");
		
		Creature child = factory.newFungus(creature.z);
		child.x = x;
		child.y = y;
		child.z = creature.z;
		spreadcount++;
	}
}