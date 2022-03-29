package org.genspark;

public class Bike implements Vehicle {
    private Tyre tyre;

    Bike(Tyre tyre){
        this.tyre = tyre;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public void drive(){
        System.out.print("riding");
    }
}
