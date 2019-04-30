package xyz.devosmium.rl.AsciiRogue.items;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.AsciiRogue.World;

public class WeaponFactory extends ItemFactory {

    public WeaponFactory(World world) {
        super(world);
    }

    public Item newDagger(int depth) {
        Weapon item = new Weapon(')', AsciiPanel.brightWhite, "dagger");
        item.modAttackValue(10);
        world.addAtEmptyLocation(item, depth);
        return item;
    }
}