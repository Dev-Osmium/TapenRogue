// Copyright (C) 2019 Owen Salter <owen@devosmium.xyz>
// 
// This file is part of tapenrogue.
// 
// tapenrogue is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// tapenrogue is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with tapenrogue.  If not, see <http://www.gnu.org/licenses/>.

package xyz.devosmium.rl.tapenrogue.creatures.ai;

import java.util.List;

import xyz.devosmium.rl.tapenrogue.creatures.Creature;
import xyz.devosmium.rl.tapenrogue.util.Path;
import xyz.devosmium.rl.tapenrogue.util.Point;

public class ZombAi extends CreatureAi {

    private Creature player;

    public ZombAi(Creature creature, Creature player) {
        super(creature);
        this.player = player;
    }

    public void onUpdate() {
        if (creature.canSee(player.x, player.y, player.z)) {
            hunt(player);
        } else {
            wander();
        }
    }

    private void hunt(Creature target) {
        List<Point> points = new Path(creature, target.x, target.y).getPoints();

        int mx = points.get(0).x - creature.x;
        int my = points.get(0).y - creature.y;
        
        creature.moveBy(mx, my, 0);
    }

}