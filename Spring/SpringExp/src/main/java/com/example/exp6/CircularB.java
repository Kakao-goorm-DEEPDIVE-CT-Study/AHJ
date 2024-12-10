package com.example.exp6;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CircularB {

    private CircularA circularA;

    public CircularB(@Lazy CircularA circularA){
        this.circularA = circularA;
    }

    public void printInfo(){
        circularA.printInfo();
        System.out.println("CircularB.printInfo : " + circularA);
    }

}
