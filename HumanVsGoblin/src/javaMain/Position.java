public interface Position {
    int position = -1;

    default void setPosition(int x){
//        this.position = x;
    }

    default int getPosition(){
        return position;
    }
}
