package org.genspark;

public class Car implements Vehicle {

    public Tyre tyre;

    Car(Tyre tyre){
        this.tyre = tyre;
    }

//    public Tyre getTyre() {
//        return tyre;
//    }
//
//    public void setTyre(Tyre tyre) {
//        this.tyre = tyre;
//    }

    public void drive(){
        System.out.print("driving " + tyre);
    }
}
