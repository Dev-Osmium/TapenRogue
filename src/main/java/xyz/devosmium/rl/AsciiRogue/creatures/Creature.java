package xyz.devosmium.rl.AsciiRogue.creatures;

import xyz.devosmium.rl.AsciiRogue.Tile;
import xyz.devosmium.rl.AsciiRogue.World;
import xyz.devosmium.rl.AsciiRogue.creatures.ai.CreatureAi;
import xyz.devosmium.rl.AsciiRogue.items.Food;
import xyz.devosmium.rl.AsciiRogue.items.Item;
import xyz.devosmium.rl.AsciiRogue.util.Inventory;

import java.awt.*;

public class Creature {
	private World world;

	public int x;
	public int y;
	public int z;

	private char glyph;
	public char glyph() { return glyph; }

	private Color color;
	public Color color() { return color; }

	private CreatureAi ai;
	public void setCreatureAi(CreatureAi ai) { this.ai = ai; }

	private int maxHp;
	public int maxHp() { return maxHp; }

	private int hp;
	public int hp() { return hp; }

	private int maxFood;
	public int maxFood() { return maxFood; }

	private int food;
	public int food() { return food; }

	private int attackValue;
	public int attackValue() { return attackValue; }

	private int defenseValue;
	public int defenseValue() { return defenseValue; }

	private int visionRadius;
	public int visionRadius() { return visionRadius; }

	private String name;
	public String name() { return name; }

	private Inventory inventory;
	public Inventory getInventory() {return inventory;}

	public Creature(World world, char glyph, Color color, String name, int maxHp, int attack, int defense){
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
		this.food = maxFood / 3 *2;
	}

	public void moveBy(int mx, int my, int mz){
		if (mx==0 && my==0 && mz==0)
			return;

		Tile tile = world.tile(x+mx, y+my, z+mz);

		if (mz == -1){
			if (tile == Tile.STAIRS_DOWN) {
				doAction("walk up the stairs to level %d", z+mz+1);
			} else {
				doAction("try to go up but are stopped by the cave ceiling");
				return;
			}
		} else if (mz == 1){
			if (tile == Tile.STAIRS_UP) {
				doAction("walk down the stairs to level %d", z+mz+1);
			} else {
				doAction("try to go down but are stopped by the cave floor");
				return;
			}
		}

		Creature other = world.creature(x+mx, y+my, z+mz);

		if (other == null)
			ai.onEnter(x+mx, y+my, z+mz, tile);
		else
			attack(other);
	}

	public void attack(Creature other){
		int amount = Math.max(0, attackValue() - other.defenseValue());

		amount = (int)(Math.random() * amount) + 1;

		doAction("attack the %s for %d damage", other.name, amount);

		other.modifyHp(-amount);
	}

	public void modifyHp(int amount) {
		hp += amount;

		if (hp < 1) {
			doAction("die");
			leaveCorpse();
			world.remove(this);
		}
	}

	public void modifyFood(int mod) {
		food += mod;

		if (food > maxFood) {
			food = maxFood;
		} else if (food <=0 && isPlayer()) {
			modifyHp(-1000);
		}
	}

	public void dig(int wx, int wy, int wz) {
		world.dig(wx, wy, wz);
		modifyFood(-10);
		doAction("dig");
	}

	public void update(){
		modifyFood(-1);
		ai.onUpdate();
	}

	public boolean canEnter(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz).isGround() && world.creature(wx, wy, wz) == null;
	}

	public void notify(String message, Object ... params){
		ai.onNotify(String.format(message, params));
	}

	public void doAction(String message, Object ... params){
		int r = 9;
		for (int ox = -r; ox < r+1; ox++){
			for (int oy = -r; oy < r+1; oy++){
				if (ox*ox + oy*oy > r*r)
					continue;

				Creature other = world.creature(x+ox, y+oy, z);

				if (other == null)
					continue;

				if (other == this)
					other.notify("You " + message + ".", params);
				else
					other.notify(String.format("The %s %s.", name, makeSecondPerson(message)), params);
			}
		}
	}

	private String makeSecondPerson(String text){
		String[] words = text.split(" ");
		words[0] = words[0] + "s";

		StringBuilder builder = new StringBuilder();
		for (String word : words){
			builder.append(" ");
			builder.append(word);
		}

		return builder.toString().trim();
	}

	public boolean canSee(int wx, int wy, int wz){
		return ai.canSee(wx, wy, wz);
	}

	public Tile tile(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz);
	}

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

	public void drop(Item item) {
		doAction("drop a %s", item.name());
		inventory.remove(item);
		world.addAtEmptySpace(item, x, y, z);
	}

	public void eat(Food item) {
		modifyHp(item.getHealthValue());
		modifyFood(item.getFoodValue());
		inventory.remove(item);
	}

	public boolean isPlayer() {
		return glyph == '@';
	}
}