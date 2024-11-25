package com.mycompany.mvcproject.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {
    @Autowired
    private ServiceA serviceA;//순환 의존성 해결 : 필드 주입

    @Autowired
    public ServiceB(@Lazy ServiceA serviceA) {
        this.serviceA = serviceA;
    }
//
//    @Autowired
//    public void setServiceA(ServiceA serviceA) {
//        this.serviceA = serviceA;
//    }

    public void methodB(){
        System.out.println("method in ServiceB");
        serviceA.methodA();
    }

}
