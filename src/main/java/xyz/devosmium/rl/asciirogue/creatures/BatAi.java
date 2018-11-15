package xyz.devosmium.rl.asciirogue.creatures;

import xyz.devosmium.rl.asciirogue.creatures.ai.CreatureAi;

public class BatAi extends CreatureAi {

    public BatAi(Creature creature) {
        super(creature);
    }

    public void onUpdate() {
        wander();
        wander();
    }

}
