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

package xyz.devosmium.rl.AsciiRogue.screens;

import xyz.devosmium.rl.AsciiRogue.creatures.Creature;
import xyz.devosmium.rl.AsciiRogue.items.Armor;
import xyz.devosmium.rl.AsciiRogue.items.Item;
import xyz.devosmium.rl.AsciiRogue.items.Weapon;

public class EquipScreen extends InventoryBaseScreen {
    public EquipScreen(Creature player) {
        super(player);
    }

    protected String getVerb() {
        return "wear or wield";
    }

    protected boolean isAcceptable(Item item) {
        return item instanceof Armor || item instanceof Weapon;
    }

    protected Screen use(Item item) {
        player.equip(item);
        return null;
    }
}