package com.binted.Binted.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Value("${logging.aspect.enabled}")
  private boolean isLoggingEnabled;

  @Before("execution(* com.binted.Binted.service..*(..))")
  public void logMethodInvocation(JoinPoint joinPoint) {
    if (isLoggingEnabled) {
      String className = joinPoint.getSignature().getDeclaringTypeName();
      String methodName = joinPoint.getSignature().getName();
      logger.info("Invoking method: {}.{}", className, methodName);
    }
  }
}
