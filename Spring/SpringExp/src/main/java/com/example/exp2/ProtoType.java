package com.example.exp2;

public class ProtoType {
    private int count = 0;
    public static void ProtoType(){
        System.out.println("new ProtoType");
    }
    public void addCount(){
        count++;
    }
    public int getCount(){
        return count;
    }
}
