package xyz.devosmium.rl.tapenrogue.creatures.ai;

import java.util.List;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.tapenrogue.creatures.Creature;
import xyz.devosmium.rl.tapenrogue.items.Item;
import xyz.devosmium.rl.tapenrogue.util.Path;
import xyz.devosmium.rl.tapenrogue.util.Point;

public class SkeletonAi extends CreatureAi {

    private Creature player;

    public SkeletonAi(Creature creature, Creature player) {
        super(creature);
        this.player = player;
        Item bow = new Item('(',AsciiPanel.brightWhite, "bow");
        bow.modAttackValue(4);
        bow.modRange(10);
        this.creature.equip(bow);
    }

    public void onUpdate() {
        if (creature.canSee(player.x, player.y, player.z)) {
            hunt(player);
        } else {
            wander();
        }

        if (creature.getDistanceFromCreature(player) <= 10) {
            creature.rangedAttack(player);
        }
    }

    private void hunt(Creature target) {
        List<Point> points = new Path(creature, target.x, target.y).getPoints();
        int mx = points.get(0).x - creature.x;
        int my = points.get(0).y - creature.y;

        creature.moveBy(mx, my, 0);
    }

}