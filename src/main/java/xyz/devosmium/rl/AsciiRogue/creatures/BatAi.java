package xyz.devosmium.rl.AsciiRogue.creatures;

import xyz.devosmium.rl.AsciiRogue.creatures.ai.CreatureAi;

public class BatAi extends CreatureAi {

    public BatAi(Creature creature) {
        super(creature);
    }

    public void onUpdate() {
        wander();
        wander();
    }

}
