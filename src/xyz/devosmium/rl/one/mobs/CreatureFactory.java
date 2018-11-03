package xyz.devosmium.rl.one.mobs;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.one.World;

public class CreatureFactory {
	private World world;
	private int width;
	private int height;
	
	public CreatureFactory(World world) {
		this.world = world;
		this.width = world.width();
		this.height = world.height();
	}
	
	public Creature newPlayer() {
		Creature player = new Creature(world, '@', AsciiPanel.brightWhite);
		return player;
	}

	public Creature newFungus(){
    	Creature fungus = new Creature(world, 'f', AsciiPanel.green);
    	world.addAtEmptyLocation(fungus);
    	new FungusAI(fungus);
    	return fungus;
	}

	

	public void addAtEmptyLocation(Creature creature){
		int x;
		int y;
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		while (!world.tile(x,y).isGround() || world.creature(x,y) != null);
  
		creature.x = x;
		creature.y = y;
		world.creatures.add(creature);
}



}
