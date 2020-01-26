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

package xyz.devosmium.rl.tapenrogue.creatures;

import java.awt.Color;

import xyz.devosmium.rl.tapenrogue.Tile;
import xyz.devosmium.rl.tapenrogue.World;
import xyz.devosmium.rl.tapenrogue.creatures.ai.CreatureAi;
import xyz.devosmium.rl.tapenrogue.items.Food;
import xyz.devosmium.rl.tapenrogue.items.Item;
import xyz.devosmium.rl.tapenrogue.items.Weapon;
import xyz.devosmium.rl.tapenrogue.util.Inventory;

public class Creature {
	private World world;

	public int x;
	public int y;
	public int z;

	private char glyph;

	/**
	 * The glyph (symbol) of the Creature
	 * @return the char representing the creature
	 */
	public char glyph() {
		return glyph;
	}

	private Color color;

	
	/** 
	 * @return The color of the Creature's symbol
	 */
	public Color color() {
		return color;
	}

	private CreatureAi ai;

	
	/** 
	 * @param ai The AI class to be used for the creature
	 */
	public void setCreatureAi(CreatureAi ai) {
		this.ai = ai;
	}

	private int maxHp;

	
	/** 
	 * @return the maximum HP for this creature
	 */
	public int maxHp() {
		return maxHp;
	}

	private int hp;

	
	/** 
	 * @return the actual HP for this creature
	 */
	public int hp() {
		return hp;
	}

	private int maxFood;

	
	/** 
	 * @return the maximum amount of food this creature can eat
	 */
	public int maxFood() {
		return maxFood;
	}

	private int food;

	
	/** 
	 * @return the actual amount of food left
	 */
	public int food() {
		return food;
	}

	private int visionRadius;

	
	/** 
	 * @return the number of tiles this creature can see
	 */
	public int visionRadius() {
		return visionRadius;
	}

	private String name;

	
	/** 
	 * @return the name of the creature
	 */
	public String name() {
		return name;
	}

	private Inventory inventory;

	
	/** 
	 * @return the inventory object
	 */
	public Inventory getInventory() {
		return inventory;
	}

	private Item weapon;

	
	/** 
	 * @return the weapon being held
	 */
	public Item weapon() {
		return weapon;
	}

	private Item armor;

	
	/** 
	 * @return the armor being worn
	 */
	public Item armor() {
		return armor;
	}

	private int attackValue;

	
	/** 
	 * @return the amount of damage this creature can deal, adjusted for weapon values
	 */
	public int attackValue() {
		return attackValue + (weapon == null ? 0 : weapon.getAttackValue());
	}

	private int defenseValue;

	
	/** 
	 * @return the amount of defense this creature has, adjusted for armor values
	 */
	public int defenseValue() {
		return defenseValue + (armor == null ? 0 : armor.getDefenseValue());
	}

	
	/** 
	 * Creates a new Creature
	 * @param world The world the creature resides in
	 * @param glyph The symbol to use for the creature
	 * @param color The color for the symbol
	 * @param name The name for the creature
	 * @param maxHp Maximum HP for the creature
	 * @param attack Base attack value (w/o weapons)
	 * @param defense Base defense value (w/o armor)
	 * @return The creature object
	 */
	public Creature(World world, char glyph, Color color, String name, int maxHp, int attack, int defense) {
		this.world = world;
		this.glyph = glyph;
		this.color = color;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackValue = attack;
		this.defenseValue = defense;
		this.visionRadius = 9;
		this.name = name;
		this.inventory = new Inventory(20);
		this.maxFood = 2000;
		this.food = maxFood / 3 * 2;
	}

	
	/** 
	 * Moves one tile in the specified direction
	 * @param mx the amount to move by laterally along the X-axis
	 * @param my The amount to move by laterally along the Y-axis
	 * @param mz the amount to move by vertically along the Z-axis
	 */
	public void moveBy(int mx, int my, int mz) {
		if (mx == 0 && my == 0 && mz == 0)
			return;

		Tile tile = world.tile(x + mx, y + my, z + mz);

		if (mz == -1) {
			if (tile == Tile.STAIRS_DOWN) {
				doAction("walk up the stairs to level %d", z + mz + 1);
			} else {
				doAction("try to go up but are stopped by the cave ceiling");
				return;
			}
		} else if (mz == 1) {
			if (tile == Tile.STAIRS_UP) {
				doAction("walk down the stairs to level %d", z + mz + 1);
			} else {
				doAction("try to go down but are stopped by the cave floor");
				return;
			}
		}

		Creature other = world.creature(x + mx, y + my, z + mz);

		if (other == null)
			ai.onEnter(x + mx, y + my, z + mz, tile);
		else
			attack(other);
	}

	
	/** 
	 * Attacks another Creature
	 * @param other The creature to attack
	 */
	public void attack(Creature other) {
		int amount = Math.max(0, attackValue() - other.defenseValue());

		amount = (int) (Math.random() * amount) + 1;

		doAction("attack the %s for %d damage", other.name, amount);

		other.modifyHp(-amount);
	}

	/**
	 * Figures out if the current creature can reach the target with its weapon, and then calls attack() to do the computation
	 * @param target The creature to attack
	 */
	public void rangedAttack(Creature target) {
		Item weapon = weapon();
		int distance = getDistanceFromCreature(target);
		if (distance <= weapon.getRange()) {
			attack(target);
		} else {
			doAction("You try to attack the %s but you're too far away!", target.name);
		}
	}

	
	/** 
	 * Modifies the Creature's HP by the specified amount
	 * @param amount The amount to modify the HP by
	 */
	public void modifyHp(int amount) {
		hp += amount;

		if (hp < 1) {
			doAction("die");
			leaveCorpse();
			world.remove(this);
		}
	}

	
	/** 
	 * @param mod Amount to modify food by
	 */
	public void modifyFood(int mod) {
		food += mod;

		// If the player tries to eat more food than possible, set food to maxFood
		if (food > maxFood) {
			food = maxFood;
		// If the player starves, kill them
		} else if (food <= 0 && isPlayer()) {
			modifyHp(-1000);
		}
	}

	
	/** 
	 * @param wx
	 * @param wy
	 * @param wz
	 */
	public void dig(int wx, int wy, int wz) {
		world.dig(wx, wy, wz);
		modifyFood(-10);
		doAction("dig");
	}

	public void update() {
		modifyFood(-1);
		ai.onUpdate();
	}

	
	/** 
	 * @param wx
	 * @param wy
	 * @param wz
	 * @return boolean
	 */
	public boolean canEnter(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz).isGround() && world.creature(wx, wy, wz) == null;
	}

	
	/** 
	 * @param message
	 * @param params
	 */
	public void notify(String message, Object... params) {
		ai.onNotify(String.format(message, params));
	}

	
	/** 
	 * @param message
	 * @param params
	 */
	public void doAction(String message, Object... params) {
		int r = 9;
		for (int ox = -r; ox < r + 1; ox++) {
			for (int oy = -r; oy < r + 1; oy++) {
				if (ox * ox + oy * oy > r * r)
					continue;

				Creature other = world.creature(x + ox, y + oy, z);

				if (other == null)
					continue;

				if (other == this)
					other.notify("You " + message + ".", params);
				else
					other.notify(String.format("The %s %s.", name, makeSecondPerson(message)), params);
			}
		}
	}

	
	/** 
	 * @param text
	 * @return String
	 */
	private String makeSecondPerson(String text) {
		String[] words = text.split(" ");
		words[0] = words[0] + "s";

		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			builder.append(" ");
			builder.append(word);
		}

		return builder.toString().trim();
	}

	
	/** 
	 * @param wx
	 * @param wy
	 * @param wz
	 * @return boolean
	 */
	public boolean canSee(int wx, int wy, int wz) {
		return ai.canSee(wx, wy, wz);
	}

	
	/** 
	 * @param wx
	 * @param wy
	 * @param wz
	 * @return Tile
	 */
	public Tile tile(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz);
	}

	
	/** 
	 * @param wx
	 * @param wy
	 * @param wz
	 * @return Creature
	 */
	public Creature creature(int wx, int wy, int wz) {
		return world.creature(wx, wy, wz);
	}

	public void leaveCorpse() {
		Food corpse = new Food('%', color, "corpse of " + name);
		corpse.modFoodValue(maxHp * 3);
		world.addAtEmptySpace(corpse, x, y, z);
	}

	public void pickup() {
		Item item = world.item(x, y, z);

		if (inventory.isFull() || item == null) {
			doAction("grab at the ground");
		} else {
			doAction("pick up a %s", item.name());
			world.remove(x, y, z);
			inventory.add(item);
		}
	}

	
	/** 
	 * @param item
	 */
	public void drop(Item item) {
		doAction("drop a %s", item.name());
		inventory.remove(item);
		world.addAtEmptySpace(item, x, y, z);
	}

	
	/** 
	 * @param item
	 */
	public void eat(Food item) {
		modifyHp(item.getHealthValue());
		modifyFood(item.getFoodValue());
		inventory.remove(item);
	}

	
	/** 
	 * @return boolean
	 */
	public boolean isPlayer() {
		return glyph == '@';
	}

	
	/** 
	 * @param item
	 */
	public void unequip(Item item) {
		if (item == null) {
			return;
		}

		if (item == armor) {
			doAction("remove a" + item.name());
			armor = null;
		} else if (item == weapon) {
			doAction("put away a" + item.name());
			weapon = null;
		}
	}

	
	/** 
	 * @param item
	 */
	public void equip(Item item) {
		if (item.getAttackValue() == 0 && item.getDefenseValue() == 0)
			return;

		if (item.getAttackValue() >= item.getDefenseValue()) {
			unequip(weapon);
			doAction("wield a " + item.name());
			weapon = item;
		} else {
			unequip(armor);
			doAction("put on a " + item.name());
			armor = item;
		}
	}

	private int getDistanceFromCreature(Creature other) {
		return (int) Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
	}
}