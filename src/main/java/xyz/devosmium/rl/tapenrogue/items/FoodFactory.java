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

public class FoodFactory extends ItemFactory {

    public FoodFactory(World world) {
        super(world);
    }

        // Food
        public Item newBread(int depth) {
            Food bread = new Food('b', AsciiPanel.brightGreen, "bread");
            bread.modFoodValue(100);
            bread.modHealthValue(2);
            world.addAtEmptyLocation(bread, depth);
            return bread;
        }

        public Item newRation(int depth) {
            Food ration = new Food('r', AsciiPanel.brightGreen, "ration");
            ration.modFoodValue(125);
            ration.modHealthValue(4);
            world.addAtEmptyLocation(ration, depth);
            return ration;
        }
}