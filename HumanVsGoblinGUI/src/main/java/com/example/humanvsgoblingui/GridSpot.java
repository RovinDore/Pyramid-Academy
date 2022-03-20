package com.example.humanvsgoblingui;

import javafx.scene.image.ImageView;

public class GridSpot {
    private int index;
    private String spotType;
    private GridSpot spot;
    private ImageView theSpot;
    private int x, y;

    public void setX(int x) {
        this.x = x;
//        System.out.println("New x " + x);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
//        System.out.println("New y " + y);
        this.y = y;
    }

    public GridSpot(int x, int y, ImageView spot){
        this.x = x;
        this.y = y;
        this.theSpot = spot;
    }

    public GridSpot(int x, int y){
        this.x = x;
        this.y = y;
    }

    public GridSpot(){}

    public int getPosition() {
        return index;
    }

    public ImageView getTheSpot() {
        return this.theSpot;
    }

    public GridSpot setTheSpot(ImageView spot) {
        this.theSpot = spot;
        return this;
    }

    public GridSpot draw(){
        getTheSpot().setTranslateX(x);
        getTheSpot().setTranslateY(y);
        return this;
    }

    public void setFitWidth(int width){
        this.getTheSpot().setFitWidth(width);
    }

    public void setFitHeight(int height){
        this.getTheSpot().setFitHeight(height);
    }

    public GridSpot moveRight(){
        if(this.index + 1 <= 24) this.index += 1;
        return this;
    }

    public GridSpot moveLeft(){
        if(this.index - 1 >= 0) this.index -= 1;
        return this;
    }

    public GridSpot moveNorth(){
        if(this.index - 5 >= 0) this.index -= 5;
        return this;
    }

    public GridSpot moveSouth(){
        if(this.index + 5 <= 24) this.index += 5;
        return this;
    }

    @Override
    public String toString() {
        return spotType;
    }
}
