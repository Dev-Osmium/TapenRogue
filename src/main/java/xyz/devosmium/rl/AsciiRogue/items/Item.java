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

import java.awt.Color;

public class Item {
    
    protected char glyph;
    public char glyph() {return glyph;}

    protected Color color;
    public Color color() {return color;}

    protected String name;
    public String name() {return name;}



    

    private int defenseValue;
    public int getDefenseValue() { return defenseValue; }
    public void modDefenseValue(int mod) { defenseValue += mod; }

    public Item(char glyph, Color color, String name) {
        this.glyph = glyph;
        this.color = color;
        this.name = name;
    }
}