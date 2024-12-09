package com.example.exp2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAppXml {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        SingleTon singleTon1 = (SingleTon) context.getBean("singleton");
        singleTon1.addCount();
        System.out.println("singleTon1 = " + singleTon1);
        System.out.println("singleTon1 count : " + singleTon1.getCount());

        SingleTon singleTon2 = (SingleTon) context.getBean("singleton");
        singleTon2.addCount();
        System.out.println("singleTon2 = " + singleTon2);
        System.out.println("singleTon2 count : " + singleTon2.getCount());

        ProtoType protoType1 = (ProtoType) context.getBean("prototype");
        protoType1.addCount();
        System.out.println("protoType1 = " + protoType1);
        System.out.println("protoType1 count : " + protoType1.getCount());

        ProtoType protoType2 = (ProtoType) context.getBean("prototype");
        protoType2.addCount();
        System.out.println("protoType2 = " + protoType2);
        System.out.println("protoType2 count : " + protoType2.getCount());
    }
}
