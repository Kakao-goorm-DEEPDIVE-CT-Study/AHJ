package com.example.exp2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("singleton")
    public SingleTon singleTon(){
        return new SingleTon();
    }

    @Bean
    @Scope("prototype")
    public ProtoType protoType(){
        return new ProtoType();
    }
}
