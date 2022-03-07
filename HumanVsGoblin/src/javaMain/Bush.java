public class Bush extends GridSpot {
    static String emoji = "\uD83C\uDF33";

    public Bush(int index){
        super(index);
        this.setSpotType(emoji);
    }

    public Bush(){}

    @Override
    public String toString(){
        return this.emoji;
    }
}
