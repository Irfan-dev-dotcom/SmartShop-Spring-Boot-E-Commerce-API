package com.example.SmartShop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.smartshop..*(..))")
    public void logBefore() {
        System.out.println("Method called...");
    }
}
