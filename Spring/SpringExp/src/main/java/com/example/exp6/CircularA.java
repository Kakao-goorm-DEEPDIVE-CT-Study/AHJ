package com.example.exp6;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CircularA {

    private CircularB circularB;

    public CircularA( CircularB circularB){
        this.circularB = circularB;
    }

    public void printInfo(){
        circularB.printInfo();
        System.out.println("CircularA.printInfo : " + circularB);
    }
}
