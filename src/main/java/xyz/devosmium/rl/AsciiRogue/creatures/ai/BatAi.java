package xyz.devosmium.rl.AsciiRogue.creatures.ai;

import xyz.devosmium.rl.AsciiRogue.creatures.ai.CreatureAi;
import xyz.devosmium.rl.AsciiRogue.creatures.Creature;;

public class BatAi extends CreatureAi {

    public BatAi(Creature creature) {
        super(creature);
    }

    public void onUpdate() {
        wander();
        wander();
    }

}
