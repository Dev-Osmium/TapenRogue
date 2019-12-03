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

public class ItemFactory {

    protected World world;

    public ItemFactory(World world) {
        this.world = world;
    }

    public Item newRock(int depth) {
        Item rock = new Item(',', AsciiPanel.yellow, "rock");
        world.addAtEmptyLocation(rock, depth);
        return rock;
    }

    public Item newMedkit(int depth) {
        Item medkit = new Item('h', AsciiPanel.magenta, "health potion");
        world.addAtEmptyLocation(medkit, depth);
        return medkit;
    }

    // Weapons


    // Armor
    public Item newLightArmor(int depth) {
        Item item = new Item('[', AsciiPanel.green, "leather armor");
        item.modDefenseValue(6);
        world.addAtEmptyLocation(item, depth);
        return item;
    }



}