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

package xyz.devosmium.rl.AsciiRogue.items;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.AsciiRogue.World;

public class ItemFactory {

    protected World world;

    public ItemFactory(World world) {
        this.world = world;
    }

    public Item newRock(int depth) {
        System.out.println("Creating rock");
        Item rock = new Item(',', AsciiPanel.yellow, "rock");
        System.out.println("Created rock");
        System.out.println("Depth = " + depth);
        world.addAtEmptyLocation(rock, depth);
        System.out.println("Added rock to world");
        return rock;
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