import java.util.ArrayList;

public class Human extends GridSpot {
    static String emoji = "\uD83E\uDDCD";
    private int health = 0, strength = 0;
    private double points;
    private ArrayList<Item> inventory = new ArrayList<>();

    public Human(int index){
        super(index);
        this.health = 100;
        this.strength = 100;
        this.points = 0;
    }

    public int getHealth() {
        return health;
    }

    public Human setHealth(int health) {
        this.health = health;
        return this;
    }

    public Human addToInventory(Item item){
        inventory.add(item);
        return this;
    }

    public double getPoints() {
        return points;
    }

    public Human addPoints(double points){
        this.points += points;
        return this;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Outcome combat(){
        int randNum = (int)(Math.random() * 10) + 1;
        if(randNum % 2 == 0) {
            ++points;
            return Outcome.GoblinDies;
        }
        else return Outcome.HumanDies;
    }

    @Override
    public String toString(){
        return this.emoji;
    }

}
