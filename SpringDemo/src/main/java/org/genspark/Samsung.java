package org.genspark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Samsung {
    @Autowired
    @Qualifier("snapdragon")
    MobileProcessor cpu;

    public void config(){
        System.out.println("hi");
        cpu.processor();
    }
}
