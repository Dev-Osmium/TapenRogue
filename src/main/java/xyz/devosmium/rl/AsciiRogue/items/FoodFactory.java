package xyz.devosmium.rl.AsciiRogue.items;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.AsciiRogue.World;

public class FoodFactory extends ItemFactory {

    public FoodFactory(World world) {
        super(world);
    }

        // Food
        public Item newBread(int depth) {
            Food bread = new Food('b', AsciiPanel.brightGreen, "bread");
            bread.modFoodValue(100);
            world.addAtEmptyLocation(bread, depth);
            return bread;
        }
}