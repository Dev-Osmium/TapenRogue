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

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.AsciiRogue.creatures.Creature;
import xyz.devosmium.rl.AsciiRogue.items.Item;
import xyz.devosmium.rl.AsciiRogue.screens.Screen;

public abstract class InventoryBaseScreen implements Screen {

    protected Creature player;
    private String letters;

    protected abstract String getVerb();

    protected abstract boolean isAcceptable(Item item);

    protected abstract Screen use(Item item);

    public InventoryBaseScreen(Creature player) {
        this.player = player;
        this.letters = "abcdefghijklmnopqrstuvwxyz";
    }

    public void displayOutput(AsciiPanel terminal) {
        ArrayList<String> lines = getList();

        int y = 23 - lines.size();
        int x = 4;

        if (lines.size() > 0) {
            terminal.clear(' ', x, y, 20, lines.size());
        }

        for (String line : lines) {
            terminal.write(line, x, y++);
        }

        terminal.clear(' ', 0, 23, 80, 1);
        terminal.write("What would you like to " + getVerb() + "?", 2, 23);

        terminal.repaint();
    }

    private ArrayList<String> getList() {
        ArrayList<String> lines = new ArrayList<String>();
        Item[] inventory = player.getInventory().getItems();

        for (int i = 0; i < inventory.length; i++) {
            Item item = inventory[i];

            if (item == null) {
                continue;
            }

            String line = letters.charAt(i) + " - " + item.glyph() + " " + item.name();

            lines.add(line);
        }
        return lines;
    }

    public Screen respondToUserInput(KeyEvent key) {
        char c = key.getKeyChar();

        Item[] items = player.getInventory().getItems();

        if (letters.indexOf(c) > -1 && items.length > letters.indexOf(c) && items[letters.indexOf(c)] != null
                && isAcceptable(items[letters.indexOf(c)])) {
            return use(items[letters.indexOf(c)]);
        } else if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
            return null;
        } else {
            return this;
        }
    }
}