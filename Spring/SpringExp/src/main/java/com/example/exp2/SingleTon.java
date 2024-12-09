package com.example.exp2;

public class SingleTon {
    private int count = 0;
    public void SingleTon(){
        System.out.println("new SingleTon");
    }
    public void addCount(){
        count++;
    }
    public int getCount(){
        return count;
    }
}
