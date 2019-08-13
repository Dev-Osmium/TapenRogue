// Copyright (C) 2019 Owen Salter <owen@devosmium.xyz>
// 
// This file is part of AsciiRogue.
// 
// AsciiRogue is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// AsciiRogue is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with AsciiRogue.  If not, see <http://www.gnu.org/licenses/>.

package xyz.devosmium.rl.AsciiRogue.util;

import xyz.devosmium.rl.AsciiRogue.Tile;
import xyz.devosmium.rl.AsciiRogue.World;

public class FieldOfView {

    private World world;
    private int depth;

    private boolean[][] visible;
    public boolean isVisible(int x, int y, int z){
        return z == depth && x >= 0 && y >= 0 && x < visible.length && y < visible[0].length && visible[x][y];
    }


    private Tile[][][] tiles;
    public Tile tile(int x, int y, int z){
        return tiles[x][y][z];
    }

    public FieldOfView(World world){
        this.world = world;
        this.visible = new boolean[world.width()][world.height()];
        this.tiles = new Tile[world.width()][world.height()][world.depth()];

        for (int x = 0; x < world.width(); x++){
            for (int y = 0; y < world.height(); y++){
                for (int z = 0; z < world.depth(); z++){
                    tiles[x][y][z] = Tile.UNKNOWN;
                }
            }
        }
    }


    public void update(int wx, int wy, int wz, int r){
        depth = wz;
        visible = new boolean[world.width()][world.height()];

        for (int x = -r; x < r; x++){
            for (int y = -r; y < r; y++){
                if (x*x + y*y > r*r)
                    continue;

                if (wx + x < 0 || wx + x >= world.width()
                        || wy + y < 0 || wy + y >= world.height())
                    continue;

                for (Point p : new Line(wx, wy, wx + x, wy + y)){
                    Tile tile = world.tile(p.x, p.y, wz);
                    visible[p.x][p.y] = true;
                    tiles[p.x][p.y][wz] = tile;

                    if (!tile.isGround())
                        break;
                }
            }
        }
    }


}
