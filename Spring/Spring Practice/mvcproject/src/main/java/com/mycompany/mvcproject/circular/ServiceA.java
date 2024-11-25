package com.mycompany.mvcproject.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    @Autowired
    private ServiceB serviceB;

    @Autowired
    public ServiceA(@Lazy ServiceB serviceB) {
        this.serviceB = serviceB;
    }
//
//    @Autowired
//    public void setServiceB(ServiceB serviceB) {
//        this.serviceB = serviceB;
//    }

    public void methodA(){
        System.out.println("method in ServiceA");
        serviceB.methodB();
    }
}
