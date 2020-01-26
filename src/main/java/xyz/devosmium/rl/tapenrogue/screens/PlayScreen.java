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

package xyz.devosmium.rl.tapenrogue.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import xyz.devosmium.rl.tapenrogue.World;
import xyz.devosmium.rl.tapenrogue.WorldBuilder;
import xyz.devosmium.rl.tapenrogue.creatures.Creature;
import xyz.devosmium.rl.tapenrogue.creatures.CreatureFactory;
import xyz.devosmium.rl.tapenrogue.items.ArmorFactory;
import xyz.devosmium.rl.tapenrogue.items.FoodFactory;
import xyz.devosmium.rl.tapenrogue.items.ItemFactory;
import xyz.devosmium.rl.tapenrogue.items.WeaponFactory;
import xyz.devosmium.rl.tapenrogue.util.FieldOfView;

public class PlayScreen implements Screen {
	private World world;
	private Creature player;
	private int screenWidth;
	private int screenHeight;
	private List<String> messages;
	private FieldOfView fov;

	private Screen subscreen;

	public PlayScreen() {
		screenWidth = 80;
		screenHeight = 23;
		messages = new ArrayList<String>();
		System.out.println("Creating world...");
		createWorld();
		System.out.println("World created");
		fov = new FieldOfView(world);

		CreatureFactory creatureFactory = new CreatureFactory(world);
		ItemFactory itemFactory = new ItemFactory(world);
		WeaponFactory weaponFactory = new WeaponFactory(world);
		FoodFactory foodFactory = new FoodFactory(world);
		ArmorFactory armorFactory = new ArmorFactory(world);
		System.out.println("Filling World...");
		createCreatures(creatureFactory);
		createItems(itemFactory);
		createWeapons(weaponFactory);
		createFood(foodFactory);
		createArmor(armorFactory);
		System.out.println("World filled, entering...");

	}



	private void createCreatures(CreatureFactory creatureFactory) {
		player = creatureFactory.newPlayer(messages, fov);

		for (int z = 0; z < world.depth(); z++) {
			for (int i = 0; i < 8; i++) {
				creatureFactory.newFungus(z);
			}
			for (int i = 0; i < 20; i++) {
				creatureFactory.newBat(z);
			}
			for (int i = 0; i < 10; i++) {
				creatureFactory.newZombie(z, player);
			}

			//if (z >= world.depth() - 1) {
				for (int i = 0; i < 8; i++) {
					creatureFactory.newSkeleton(z, player);
			//	}
			}

		}
	}

	private void createItems(ItemFactory itemFactory) {
		for (int z = 0; z < world.depth(); z++) {
			for (int i = 0; i < world.width() * world.height() / 20; i++) {
				itemFactory.newRock(z);
			}
			for (int i = 0; i < 1; i++) {
				itemFactory.newMedkit(z);
			}
		}
	}

	private void createWeapons(WeaponFactory factory) {
		for (int z = 0; z < world.depth(); z++) {
			for (int i = 0; i < 2; i++) {
				factory.placeDagger(z);
			}
		}
	}

	private void createFood(FoodFactory factory) {
		for (int z = 0; z < world.depth(); z++) {
			for (int i = 0; i < 10; i++) {
				factory.newBread(z);
			}
			
			factory.newRation(z);
		}
	}

	private void createArmor(ArmorFactory armorFactory) {
		for (int z = 0; z < world.depth(); z++) {
			for (int i = 0; i < 2; i++) {
				armorFactory.newLightArmor(z);
			}
		}
	}

	private void createWorld() {
		world = new WorldBuilder(90, 32, 5).makeCaves().build();
	}

	public int getScrollX() {
		return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth));
	}

	public int getScrollY() {
		return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
	}

	@Override
	public void displayOutput(AsciiPanel terminal) {
		int left = getScrollX();
		int top = getScrollY();

		displayTiles(terminal, left, top);
		displayMessages(terminal, messages);

		String stats = String.format(" %3d/%3d hp, %8s", player.hp(), player.maxHp(), hunger());
		terminal.write(stats, 1, 23);
		if (subscreen != null) {
			subscreen.displayOutput(terminal);
		}
	}

	private void displayMessages(AsciiPanel terminal, List<String> messages) {
		int top = screenHeight - messages.size();
		for (int i = 0; i < messages.size(); i++) {
			terminal.writeCenter(messages.get(i), top + i);
		}
		messages.clear();
	}

	private void displayTiles(AsciiPanel terminal, int left, int top) {
		fov.update(player.x, player.y, player.z, player.visionRadius());

		for (int x = 0; x < screenWidth; x++) {
			for (int y = 0; y < screenHeight; y++) {
				int wx = x + left;
				int wy = y + top;

				if (player.canSee(wx, wy, player.z))
					terminal.write(world.glyph(wx, wy, player.z), x, y, world.color(wx, wy, player.z));
				else
					terminal.write(fov.tile(wx, wy, player.z).glyph(), x, y, Color.darkGray);
			}
		}
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		if (subscreen != null) {
			subscreen = subscreen.respondToUserInput(key);
		} else {
			switch (key.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
			case KeyEvent.VK_ENTER:
				return new WinScreen();
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_H:
				player.moveBy(-1, 0, 0);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_L:
				player.moveBy(1, 0, 0);
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_K:
				player.moveBy(0, -1, 0);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_J:
				player.moveBy(0, 1, 0);
				break;
			case KeyEvent.VK_Y:
				player.moveBy(-1, -1, 0);
				break;
			case KeyEvent.VK_U:
				player.moveBy(1, -1, 0);
				break;
			case KeyEvent.VK_B:
				player.moveBy(-1, 1, 0);
				break;
			case KeyEvent.VK_N:
				player.moveBy(1, 1, 0);
				break;
			case KeyEvent.VK_D:
				subscreen = new DropScreen(player);
				break;
			case KeyEvent.VK_E:
				subscreen = new EatScreen(player);
				break;
			case KeyEvent.VK_W:
				subscreen = new EquipScreen(player);
				break;
			}
		}

		switch (key.getKeyChar()) {
		case ',':
			player.pickup();
			break;
		case '<':
			player.moveBy(0, 0, -1);
			break;
		case '>':
			player.moveBy(0, 0, 1);
			break;
		}

		if (subscreen == null) {
			world.update();
		}

		if (player.hp() < 1)
			return new LoseScreen();

		return this;
	}

	private String hunger() {
		if (player.food() < player.maxFood() * 0.1) {
			return "Starving";
		} else if (player.food() < player.maxFood() * 0.2) {
			return "Hungry";
		} else if (player.food() > player.maxFood() * 0.9) {
			return "Stuffed";
		} else if (player.food() > player.maxFood() * 0.8) {
			return "Full";
		} else {
			return "";
		}
	}

}