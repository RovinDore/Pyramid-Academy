public class GridSpot {
    private int index;
    private String spotType;
    private GridSpot spot;

    public GridSpot(int index){
        this.index = index;
    }

    public GridSpot(){}

    public int getPosition() {
        return index;
    }

    public void setPosition(int index) {
        this.index = index;
    }

    public String getSpotType() {
        return spotType;
    }

    public GridSpot setSpotType(String type){
        this.spotType = type;
        return this;
    }

    public GridSpot setSpot(GridSpot spot) {
        this.spot = spot;
        return this;
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
