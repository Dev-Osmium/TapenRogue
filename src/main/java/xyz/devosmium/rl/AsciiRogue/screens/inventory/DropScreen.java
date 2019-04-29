package xyz.devosmium.rl.AsciiRogue.screens.inventory;

import xyz.devosmium.rl.AsciiRogue.creatures.Creature;
import xyz.devosmium.rl.AsciiRogue.items.Item;
import xyz.devosmium.rl.AsciiRogue.screens.Screen;

public class DropScreen extends InventoryBaseScreen {

    public DropScreen(Creature player) {
        super(player);
    }
    @Override
    protected String getVerb() {
        return "drop";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        return true;
    }

    @Override
    protected Screen use(Item item) {
        player.drop(item);
        return null;
    }

}