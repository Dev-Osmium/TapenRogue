package xyz.devosmium.rl.AsciiRogue.items;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.AsciiRogue.World;

public class ItemFactory {

    private World world;

    public ItemFactory(World world) {
        this.world = world;
    }

    public Item newRock(int depth) {
        Item rock = new Item(',', AsciiPanel.yellow, "rock");
        world.addAtEmptyLocation(rock, depth);
        return rock;
    }

}