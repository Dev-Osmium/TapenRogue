package xyz.devosmium.rl.one.mobs;

import xyz.devosmium.rl.one.Tile;

public class CreatureAI {
	protected Creature creature;
	
	public CreatureAI(Creature creature) {
		this.creature = creature;
		this.creature.setCreatureAi(this);
	}
	
	public void onEnter(int x, int y, Tile tile){
	    if (tile.isGround()){
	        creature.x = x;
	        creature.y = y;
	    } else if (tile.isDiggable()) {
	        creature.dig(x, y);
	    }
	}

}
