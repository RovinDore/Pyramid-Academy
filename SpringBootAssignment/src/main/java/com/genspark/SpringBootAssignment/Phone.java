package com.genspark.SpringBootAssignment;

import org.springframework.stereotype.Component;

@Component
public class Phone {
    private String mob = "mob";

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    @Override
    public String toString() {
        return "{" +
                "mob='" + mob + '\'' +
                '}';
    }
}
