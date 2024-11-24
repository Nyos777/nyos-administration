package org.example.smartdeltatest.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  // Определение точек среза
  @Pointcut("execution(* org.example.smartdeltatest.service.*.*(..))")
  public void serviceMethods() {}

  // Логируем до выполнения метода
  @Before("serviceMethods()")
  public void logBefore(JoinPoint joinPoint) {
    logger.info("Method = ({}()) to be called", joinPoint.getSignature().getName());
  }

  @AfterReturning(pointcut = "serviceMethods()", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    logger.info("Method = ({}()) executed successfully, result: {}", joinPoint.getSignature().getName(), result);
  }

  @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
  public void logAfterThrowing(Exception exception) {
    logger.error("Method threw an exception: {}", exception.getMessage());
  }
}
