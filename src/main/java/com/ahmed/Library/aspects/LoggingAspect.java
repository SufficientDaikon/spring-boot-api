package com.ahmed.Library.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.ahmed.Library.controllers.*.*(..))")
    public void logBeforeMethod() {
        logger.info("A method is called in the controller");
    }

    @AfterThrowing(pointcut = "execution(* com.ahmed.Library..*(..))", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        logger.error("An exception has been thrown: " + exception.getMessage());
    }

    @Around("execution(* com.ahmed.Library.controllers.*.addBook(..)) || execution(* com.ahmed.Library.controllers.*.updateBook(..)) || execution(* com.ahmed.Library.controllers.*.createPatron(..)) || execution(* com.ahmed.Library.controllers.*.updatePatron(..)) || execution(* com.ahmed.Library.controllers.*.borrowBook(..)) || execution(* com.ahmed.Library.controllers.*.returnBook(..))")
    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
