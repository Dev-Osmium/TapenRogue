package xyz.devosmium.rl.one.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.one.World;
import xyz.devosmium.rl.one.WorldBuilder;
import xyz.devosmium.rl.one.interfaces.IScreen;
import xyz.devosmium.rl.one.mobs.Creature;
import xyz.devosmium.rl.one.mobs.CreatureFactory;

public class PlayScreen implements IScreen{

	private World world;
    private int screenWidth;
    private int screenHeight;
    Creature player;
    public PlayScreen(){
        screenWidth = 80;
        screenHeight = 21;
        createWorld();

        CreatureFactory creatureFactory = new CreatureFactory(world);
        createCreatures(creatureFactory);

    }

    private void createCreatures(CreatureFactory creatureFactory){
    	player = creatureFactory.newPlayer();
  
    	for (int i = 0; i < 8; i++){
        	creatureFactory.newFungus();
    	}
	}
	@Override
	public void displayOutput(AsciiPanel terminal) {
		// TODO Auto-generated method stub

int left = getScrollX();
        int top = getScrollY();
   
        displayTiles(terminal, left, top);

        terminal.write(player.glyph(), player.x - left, player.y - top, player.color());

	}

	@Override
	public IScreen respondToUserInput(KeyEvent key) {
		// TODO Auto-generated method stub
		switch(key.getKeyCode()) {
		case KeyEvent.VK_LEFT: player.moveBy(-1,0); break;
        case KeyEvent.VK_H: player.moveBy(-1, 0); break;
        case KeyEvent.VK_RIGHT: player.moveBy(1,0); break;
        case KeyEvent.VK_L: player.moveBy( 1, 0); break;
        case KeyEvent.VK_UP: player.moveBy(0,1); break;
        case KeyEvent.VK_K: player.moveBy( 0,-1); break;
        case KeyEvent.VK_DOWN: player.moveBy(0,-1); break;
        case KeyEvent.VK_J: player.moveBy( 0, 1); break;
        case KeyEvent.VK_Y: player.moveBy(-1,-1); break;
        case KeyEvent.VK_U: player.moveBy( 1,-1); break;
        case KeyEvent.VK_B: player.moveBy(-1, 1); break;
        case KeyEvent.VK_N: player.moveBy( 1, 1); break;
		}
		return this;
	}
	private void createWorld(){
        world = new WorldBuilder(90, 31)
              .makeCaves()
              .build();
    }
	public int getScrollX() {
	    return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth));
	}


	public int getScrollY() {
		return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
	}

	private void displayTiles(AsciiPanel terminal, int left, int top) {
	    for (int x = 0; x < screenWidth; x++){
	        for (int y = 0; y < screenHeight; y++){
	            int wx = x + left;
	            int wy = y + top;

	            Creature creature = world.creature(wx, wy);
	            if (creature != null)
	                terminal.write(creature.glyph(), creature.x - left, creature.y - top, creature.color());
	            else
	                terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
	        }
	    }
	}



	
}
