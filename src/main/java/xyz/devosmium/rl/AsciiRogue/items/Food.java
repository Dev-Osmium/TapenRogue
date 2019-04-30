package xyz.devosmium.rl.AsciiRogue.items;

import java.awt.Color;

public class Food extends Item {

    private int foodValue;
    public int getFoodValue() { return foodValue; }
    public void modFoodValue(int mod) { foodValue += mod; }

    public Food(char glyph, Color color, String name) {
        super(glyph, color, name);
    }
}