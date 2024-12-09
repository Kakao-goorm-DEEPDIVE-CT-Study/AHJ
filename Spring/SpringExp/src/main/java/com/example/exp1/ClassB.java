package com.example.exp1;

public class ClassB {
    ClassA classA;

    public void setClassA(ClassA classA){
        this.classA = classA;
    }
    public void printClassInfo(){
        System.out.println("classA : " + classA);
    }
}
