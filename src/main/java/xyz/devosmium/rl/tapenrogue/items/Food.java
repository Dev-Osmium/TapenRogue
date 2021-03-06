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

import java.awt.Color;

public class Food extends Item {

    private int foodValue;
    public int getFoodValue() { return foodValue; }
    public void modFoodValue(int mod) { foodValue += mod; }

    private int healthValue;
    public int getHealthValue() { return healthValue; }
    public void modHealthValue(int mod) { foodValue += mod; }

    public Food(char glyph, Color color, String name) {
        super(glyph, color, name);
    }
}