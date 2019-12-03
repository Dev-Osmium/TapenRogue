// Copyright (C) 2019 Dev-Osmium
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

package xyz.devosmium.rl.tapenrogue.util;

import java.util.List;

import xyz.devosmium.rl.tapenrogue.creatures.Creature;

public class Path {
    
    private static PathFinder finder = new PathFinder();

    private List<Point> points;
    public List<Point> getPoints() { return points; }

    public Path(Creature creature, int x, int y) {
        points = finder.findPath(creature, 
            new Point(creature.x, creature.y, creature.z),
            new Point(x, y, creature.z), 300);
    }
}