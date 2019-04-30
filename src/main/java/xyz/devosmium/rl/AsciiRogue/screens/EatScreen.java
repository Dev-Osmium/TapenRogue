package xyz.devosmium.rl.AsciiRogue.screens;

import xyz.devosmium.rl.AsciiRogue.creatures.Creature;
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
        return item.getFoodValue() != 0;
    }

    @Override
    protected Screen use(Item item) {
        player.eat(item);
        return null;
    }

}