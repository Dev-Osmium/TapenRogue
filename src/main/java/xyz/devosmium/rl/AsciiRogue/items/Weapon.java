package xyz.devosmium.rl.AsciiRogue.items;

import java.awt.Color;

public class Weapon extends Item {

    private int attackValue;
    public int getAttackValue() { return attackValue; }
    public void modAttackValue(int mod) { attackValue += mod; }

    public Weapon(char glyph, Color color, String name) {
        super(glyph, color, name);
    }
}