package com.example.humanvsgoblingui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class TreasureChest extends GridSpot  {
    static String emoji = "\uD83D\uDCB0";
    private double amountCash = 0;
    private Random rand = new Random();

    public TreasureChest(int x, int y){
        super(x,y);
        Image goblinImg = new Image(String.valueOf(HelloApplication.class.getResource("chest.png")));
        ImageView iv1 = new ImageView(goblinImg);
        setTheSpot(iv1);
        this.amountCash = rand.nextInt(100);
    }

    public double getPoints(){
        return this.amountCash;
    }

    @Override
    public String toString() {
        return this.emoji;
    }
}
