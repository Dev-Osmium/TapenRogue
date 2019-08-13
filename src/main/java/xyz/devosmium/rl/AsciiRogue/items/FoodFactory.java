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