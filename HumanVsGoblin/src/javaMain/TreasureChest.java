public class TreasureChest extends GridSpot  {
    public String emoji = "\uD83D\uDCB0";
    private double amountCash = 0;

    public TreasureChest(int index){
        super(index);
        this.setSpotType(emoji);
        amountCash = Math.random() * Math.random();
    }

    @Override
    public String toString() {
        return this.emoji;
    }
}
