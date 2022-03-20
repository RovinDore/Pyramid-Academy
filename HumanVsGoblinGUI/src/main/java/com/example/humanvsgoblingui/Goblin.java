package com.example.humanvsgoblingui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Goblin extends Human {
    static String emoji = "\uD83D\uDC79";
    private int health = 0, strength = 0;

    public Goblin(int x, int y){
        super(x,y);
        Image goblinImg = new Image(String.valueOf(HelloApplication.class.getResource("goblin.png")));
        ImageView iv1 = new ImageView(goblinImg);
        setTheSpot(iv1);
        health = 100;
        strength = 100;
//        System.out.println("init " + x + " | ");
    }

    public Goblin pursuePlayer(Human man){
        if(man == null) return this;
        int playerPosition = man.getPosition();
        int myPosition = this.getPosition();

        int temp;
        temp = myPosition - playerPosition;
        if(myPosition > playerPosition){
            if(temp > 5) moveNorth();
            else moveLeft();
        } else {
            if(temp < 5) moveSouth();
            else moveRight();
        }
//        System.out.println(playerPosition + " " + this.getPosition() + " "+ temp);
        return this;
    }

    @Override
    public String toString(){
        return this.emoji;
    }

}
