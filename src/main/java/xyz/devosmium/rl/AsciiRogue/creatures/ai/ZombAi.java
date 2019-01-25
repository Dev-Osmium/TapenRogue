package xyz.devosmium.rl.AsciiRogue.creatures.ai;

import xyz.devosmium.rl.AsciiRogue.creatures.Creature;

public class ZombAi extends CreatureAi {

    public ZombAi(Creature creature) {
        super(creature);
    }

    public void onUpdate() {
        wander();
    }

}