package com.example.spring_data.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("execution(public * com.example.spring_data.repository.*Repository.find*(..))")
    public void findMethodLoggingBefore(JoinPoint joinPoint) {
        logger.info("starting a find method in " + joinPoint.getTarget().getClass() + //
                " named " + joinPoint.getSignature().getName());
    }

    @After("execution(public * com.example.spring_data.repository.*Repository.find*(..))")
    public void findMethodLoggingAfter(JoinPoint joinPoint) {
        logger.info("finished  a find method in " + joinPoint.getTarget().getClass() + //
                " named " + joinPoint.getSignature().getName());
    }
}
