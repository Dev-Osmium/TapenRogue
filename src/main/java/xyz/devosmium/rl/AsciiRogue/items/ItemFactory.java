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

    // Food
    public Item newBread(int depth) {
        Item bread = new Item('b', AsciiPanel.brightGreen, "bread");
        bread.modFoodValue(100);
        world.addAtEmptyLocation(bread, depth);
        return bread;
    }

}