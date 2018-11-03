package xyz.devosmium.rl.one.creatures;

import xyz.devosmium.rl.one.Tile;

public class CreatureAi {
	protected Creature creature;
	
	public CreatureAi(Creature creature){
		this.creature = creature;
		this.creature.setCreatureAi(this);
	}
	
	public void onEnter(int x, int y, int i, Tile tile){
	}
	
	public void onUpdate(){
	}
	
	public void onNotify(String message) {
		
	}
}
