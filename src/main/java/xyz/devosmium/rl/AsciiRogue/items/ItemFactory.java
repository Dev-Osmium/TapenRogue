package xyz.devosmium.rl.AsciiRogue.items;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.AsciiRogue.World;

public class ItemFactory {

    private World world;

    public ItemFactory(World world) {
        this.world = world;
    }

    public Item newRock(int depth) {
        System.out.println("Creating rock");
        Item rock = new Item(',', AsciiPanel.yellow, "rock");
        System.out.println("Created rock");
        world.addAtEmptyLocation(rock, depth);
        System.out.println("Added rock to world");
        return rock;
    }

}