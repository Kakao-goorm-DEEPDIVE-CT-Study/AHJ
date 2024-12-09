package com.example.exp1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        ClassA classA = (ClassA) context.getBean("classA");
        classA.printClassInfo();

        ClassB classB = (ClassB) context.getBean("classB");
        classB.printClassInfo();
    }
}
