package com.example.exp2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAppAppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SingleTon singleTon1 = context.getBean(SingleTon.class);
        singleTon1.addCount();
        System.out.println("singleTon1 = " + singleTon1);
        System.out.println("singleTon1 count : " + singleTon1.getCount());
        SingleTon singleTon2 = context.getBean(SingleTon.class);
        singleTon2.addCount();
        System.out.println("singleTon2 = " + singleTon2);
        System.out.println("singleTon2 count : " + singleTon2.getCount());

        ProtoType protoType1 = context.getBean(ProtoType.class);
        protoType1.addCount();
        System.out.println("protoType1 = " + protoType1);
        System.out.println("protoType1 count : " + protoType1.getCount());
        ProtoType protoType2 = context.getBean(ProtoType.class);
        protoType2.addCount();
        System.out.println("protoType2 = " + protoType2);
        System.out.println("protoType2 count : " + protoType2.getCount());
    }
}
