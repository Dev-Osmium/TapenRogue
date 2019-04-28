package xyz.devosmium.rl.AsciiRogue.items;

import java.awt.Color;

public class Item {
    
    private char glyph;
    public char glyph() {return glyph;}

    private Color color;
    public Color color() {return color;}

    private String name;
    public String name() {return name;}

    // Values of Food
    private int foodValue;
    public int getFoodValue() { return foodValue; }
    public void modFoodValue(int mod) { foodValue += mod; }

    public Item(char glyph, Color color, String name) {
        this.glyph = glyph;
        this.color = color;
        this.name = name;
    }
}