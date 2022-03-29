package org.genspark;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Phone> ph;
    private List<Phone> sourceList;
    private Address add;

    public List<Phone> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Phone> sourceList) {
        this.sourceList = sourceList;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Phone> getPh() { return ph; }

    public void setPh(List<Phone> ph) { this.ph = ph; }

    public Address getAdd() { return add; }

    public void setAdd(Address add) { this.add = add; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ph=" + ph +
                ", add=" + add +
                '}';
    }
}
