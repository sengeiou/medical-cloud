package com.zhou.medical.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

    private final static Log logger = LogFactory.getLog(TestAspect.class);


    @Pointcut("@annotation(com.zhou.medical.manager.TestAnn)")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("doBeforedoBeforedoBeforedoBefore============================");
    }

    @AfterReturning(value = "controllerAspect()", returning = "results")
    public void afterReturning(JoinPoint joinpoint, Object results) throws Throwable {
        System.out.println("afterReturningafterReturningafterReturning============================");
    }

    @AfterThrowing(value = "controllerAspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        System.out.println("afterThrowafterThrowafterThrowafterThrow============================");
    }

}
