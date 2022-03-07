public class Item {
    private String name;
    private int quantity;
    private String type;

    @Override
    public String toString(){
        return this.name + this.quantity + this.type;
    }
}
