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

package xyz.devosmium.rl.tapenrogue.items;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.tapenrogue.World;

public class WeaponFactory extends ItemFactory {

    public WeaponFactory(World world) {
        super(world);
    }

    public Item placeDagger(int depth) {
        Weapon item = new Weapon(')', AsciiPanel.brightWhite, "dagger");
        item.modAttackValue(10);
        placeInWorld(item, depth);
        return item;
    }

    public static Item newDagger() {
        
    }

    private void placeInWorld(Weapon weapon, int depth) {
        world.addAtEmptyLocation(weapon, depth);
    }
}