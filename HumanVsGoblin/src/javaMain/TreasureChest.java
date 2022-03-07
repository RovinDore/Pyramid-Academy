import java.util.Random;

public class TreasureChest extends GridSpot  {
    static String emoji = "\uD83D\uDCB0";
    private double amountCash = 0;
    Random rand = new Random();

    public TreasureChest(int index){
        super(index);
        this.setSpotType(emoji);
        this.amountCash = rand.nextInt(10);
    }

    public double getPoints(){
        return this.amountCash;
    }

    @Override
    public String toString() {
        return this.emoji;
    }
}
