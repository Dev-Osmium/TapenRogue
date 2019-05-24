package xyz.devosmium.rl.AsciiRogue.screens;

import xyz.devosmium.rl.AsciiRogue.creatures.Creature;
import xyz.devosmium.rl.AsciiRogue.items.Food;
import xyz.devosmium.rl.AsciiRogue.items.Item;

public class EatScreen extends InventoryBaseScreen {


    public EatScreen(Creature player) {
        super(player);
    }
    @Override
    protected String getVerb() {
        return "eat";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        if (item instanceof Food) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected Screen use(Item item) {
        if (item instanceof Food) {
            player.eat((Food)item);
        } else {
            throw new IllegalArgumentException("Tried to eat a non-food item.");
        }
        return null;
    }

}