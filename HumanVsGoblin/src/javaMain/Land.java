public class Land extends GridSpot {
    static String emoji = "\uD83C\uDF33";

    public Land(int index){
        super(index);
        this.setSpotType(emoji);
    }

    public Land(){}

    @Override
    public String toString(){
        return this.emoji;
    }
}
