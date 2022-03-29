package org.genspark;

public class Tyre {
    private int size;
    private String brand;

    public Tyre(String brand, int size) {
        this.brand = brand;
        this.size = size;
    }

    public Tyre() { }

//    public int getSize() {
//        return size;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }

    @Override
    public String toString() {
        return "Tyre{" +
                "size=" + size +
                ", brand='" + brand + '\'' +
                '}';
    }
}
