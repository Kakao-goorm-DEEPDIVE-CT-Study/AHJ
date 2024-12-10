package com.example.exp7.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {

    @Around("execution(* com.example.exp7.service.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println("메서드: " + joinPoint.getSignature() + ", 실행 시간: " + executionTime + "ms");
        return result;
    }

    @Before("execution(* com.example.exp7.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[로그 시작] 메서드: " + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("[파라미터] " + arg);
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.exp7.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[로그 종료] 메서드: " + joinPoint.getSignature().getName() +
                ", 반환값: " + result);
    }
}
