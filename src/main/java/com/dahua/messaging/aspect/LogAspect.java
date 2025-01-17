package com.dahua.messaging.aspect;
import java.util.Date;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogAspect {

    //First star means return type
    // second star means class
    //third star is methods
    //.. means arguments
    @Around("execution(* com.dahua.messaging.controller.*.*(..))")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Date startTime = new Date();
        boolean isExceptionThrown = false;
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable exception) {
            isExceptionThrown = true;
            throw exception;
        } finally {
            Date endTime = new Date();
            log.info("{}.{} execution time: {} ms, is exception thrown: {}",
                    proceedingJoinPoint.getTarget().getClass().getSimpleName(), //controller
                    proceedingJoinPoint.getSignature().getName(), //method
                    endTime.getTime() - startTime.getTime(), isExceptionThrown);
        }

    }

}
