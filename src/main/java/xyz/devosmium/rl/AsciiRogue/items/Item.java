package xyz.devosmium.rl.AsciiRogue.items;

import java.awt.Color;

public class Item {
    
    protected char glyph;
    public char glyph() {return glyph;}

    protected Color color;
    public Color color() {return color;}

    protected String name;
    public String name() {return name;}



    

    private int defenseValue;
    public int getDefenseValue() { return defenseValue; }
    public void modDefenseValue(int mod) { defenseValue += mod; }

    public Item(char glyph, Color color, String name) {
        this.glyph = glyph;
        this.color = color;
        this.name = name;
    }
}