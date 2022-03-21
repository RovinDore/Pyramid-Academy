package com.example.humanvsgoblingui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class HVGGame {
    @FXML
    Pane pane;

    private final int size = 600;
    private final int spots = 10;
    private final int squareSize = size / spots;

    private ImageView[][] Gridspots;
    private Image[][] grid;
    private Human warrior;
    private final ArrayList<Goblin> goblins = new ArrayList<>();
    private final ArrayList<TreasureChest> treasureDrops = new ArrayList<>();

    @FXML
    public void initialize() {
        //Init game with map objects
        if(pane != null) pane.getChildren().forEach(c -> pane.getChildren().remove(c));
        addLand();
        addGoblins(6);
        addHuman();
    }

    public void addTreasure(int amount){
        for (int i = 0; i < amount; i++) {
            int x = squareSize * (int)(Math.random() * spots);
            int y = squareSize * (int)(Math.random() * spots);

            TreasureChest chest = new TreasureChest(x, y);
            chest.setFitWidth(squareSize);
            chest.setFitHeight(squareSize);

            pane.getChildren().add(chest.getTheSpot());
            chest.draw();
            treasureDrops.add(chest);
        }
    }

    private void addHuman(){
        int x = squareSize * (int)(Math.random() * spots);
        int y = squareSize * (int)(Math.random() * spots);

        warrior = new Human(x, y);
        warrior.setFitWidth(squareSize);
        warrior.setFitHeight(squareSize);

        pane.getChildren().add(warrior.getTheSpot());
        warrior.draw();
    }

    private void addGoblins(int amount){
        for (int i = 0; i < amount; i++) {
            int x = squareSize * (int)(Math.random() * spots);
            int y = squareSize * (int)(Math.random() * spots);

            Goblin gobby = new Goblin(x, y);
            gobby.setFitWidth(squareSize);
            gobby.setFitHeight(squareSize);

            pane.getChildren().add(gobby.getTheSpot());
            gobby.draw();
            goblins.add(gobby);
        }
    }

    private void addLand(){
        grid = new Image[spots][spots];
        for (int i = 0; i < size; i += squareSize) {
            for (int j = 0; j < size; j += squareSize) {
                Image grassBlock = new Image(String.valueOf(HelloApplication.class.getResource("grass.jpg")), squareSize, squareSize, true, false);
                grid[i / squareSize][j / squareSize] = grassBlock;
                ImageView iv1 = new ImageView(grassBlock);
                iv1.setX(i);
                iv1.setY(j);
                iv1.setFitWidth(squareSize);
                iv1.setFitHeight(squareSize);
                pane.getChildren().add(iv1);
            }
        }
    }

    //Check which key was pressed or passed
    public void checkCode(GridSpot spot, KeyCode keyCode){
        int spotX = spot.getX();
        int spotY = spot.getY();

        switch (keyCode) {
            case UP, W -> spot.setY(spotY - squareSize);
            case DOWN, S -> spot.setY(spotY + squareSize);
            case LEFT, A -> spot.setX(spotX - squareSize);
            case RIGHT, D -> spot.setX(spotX + squareSize);
            default -> System.out.println(keyCode);
        }
        correctMove(spot);
    }

    public void moveEntity(GridSpot spot, KeyEvent key){
        KeyCode keyCode = key.getCode();
        checkCode(spot, keyCode);
    }

    public void moveEntity(GridSpot spot, KeyCode code){
        checkCode(spot, code);
    }

    //Used for User keyevent action
    public void moveEntity(KeyEvent key){
        //Make sure the correct key was pressed
        switch (key.getCode()) {
            case UP, W, DOWN, S, LEFT, A, RIGHT, D -> {
                moveEntity(warrior, key);
                checkConflict();
                attackWarrior();
            }
        }
    }

    //Check a conflict on a single gridspot
    public boolean checkConflict(GridSpot spot1){
        int gobbyX = spot1.getX(), gobbyY = spot1.getY();
        int warriorX = warrior.getX(), warriorY = warrior.getY();
        return gobbyX == warriorX && gobbyY == warriorY;
    }

    public void checkConflict(){
        ArrayList<GridSpot> toBeRemoved = new ArrayList<>();

        goblins.forEach(goblin -> {
            if(checkConflict(goblin)){
                Outcome combatOutcome = combat();

                switch (combatOutcome) {
                    case HumanDies -> {
                        System.out.println("You died!!");
//                        pane.getChildren().remove(warrior.getTheSpot());
                        //switch to game over screen
                        try {
                            switchToGameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    case GoblinDies -> {
                        System.out.println("The Goblin died!!");
                        warrior.addPoints(10);
                        pane.getChildren().remove(goblin.getTheSpot());
                        toBeRemoved.add(goblin);
                        addTreasure(1);
                    }
                    default -> {
                    }
                }

            }
        });

        treasureDrops.forEach(chest -> {
            if(checkConflict(chest)){
                pane.getChildren().remove(chest.getTheSpot());
                toBeRemoved.add(chest);
                warrior.addPoints(chest.getPoints());
            }
        });
        toBeRemoved.forEach(t -> goblins.remove(t));
        if(goblins.size() == 0) addGoblins(5);
    }

    private Outcome combat(){
        int randNum = (int)(Math.random() * 100) + 1;
        if(randNum % 2 == 0 || randNum % 3 == 0 || randNum % 5 == 0) return Outcome.GoblinDies;
        else return Outcome.HumanDies;
    }

    public void correctMove(GridSpot spot){
        int spotX = spot.getX(), spotY = spot.getY(),
            maxSpot = size - squareSize, minSpot = 0;

        if(spotX > maxSpot) spot.setX(maxSpot);
        if(spotX < minSpot) spot.setX(minSpot);
        if(spotY > maxSpot) spot.setY(maxSpot);
        if(spotY < minSpot) spot.setY(minSpot);

        spot.draw();

    }

    public void determineEnemyMove(Goblin gobby) {
        int warriorX = warrior.getX(), warriorY = warrior.getY(), gobbyX = gobby.getX(), gobbyY = gobby.getY();
        boolean leftOfPlayer = warriorX > gobbyX;
        boolean bottomOfPlayer = warriorY < gobbyY;
        int diffX = leftOfPlayer ? warriorX - gobbyX : gobbyX - warriorX, diffY = bottomOfPlayer ? gobbyY - warriorY : warriorY - gobbyY;

        if (diffX <= (squareSize * 2) && diffY <= (squareSize * 2)){
            if(gobbyY == warriorY){
                if(leftOfPlayer) moveEntity(gobby, KeyCode.RIGHT);
                else moveEntity(gobby, KeyCode.LEFT);
            } else{
                if(bottomOfPlayer) moveEntity(gobby, KeyCode.UP);
                else moveEntity(gobby, KeyCode.DOWN);
            }
        }

    }

    private void switchToGameOver() throws IOException {
        Scene scene;
        Stage stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
        scene = new Scene(fxmlLoader.load());
        HelloApplication GameControl = fxmlLoader.getController();
        GameControl.setPointsAmount((int)warrior.getPoints());
        stage = (Stage) warrior.getTheSpot().getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void attackWarrior(){
        try{
            goblins.forEach(this::determineEnemyMove);
            checkConflict();
        } catch (Exception e){
            System.out.println("Something went wrong");
        }
    }


    //Testing methods
    public void setWarrior(Human warrior) {
        this.warrior = warrior;
    }

    public Human getWarrior() { return this.warrior; }

    public ArrayList<Goblin> getGoblins(){ return this.goblins; }

    public ArrayList<TreasureChest> getTreasureDrops() {
        return treasureDrops;
    }
}
