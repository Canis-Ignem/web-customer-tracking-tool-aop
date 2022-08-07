package com.jon.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger logger = Logger.getLogger(getClass().getName());

	
	@Pointcut("execution (* com.jon.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution (* com.jon.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}

	@Pointcut("forControllerPackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	@Before( "forAppFlow()" )
	public void before(JoinPoint joinPoint) {
		
		logger.info(" @Before INFO ====> " + joinPoint.getSignature().getDeclaringTypeName());
		
		Object[] args = joinPoint.getArgs();
		
		for(Object obj : args) {
			logger.info("Argument ====> " + obj.toString());
		}
		
	}
	
	@AfterReturning(pointcut="forAppFlow()", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger.info("@AfterReturning INFO ====> " + joinPoint.getSignature().getDeclaringTypeName());
		logger.info("Result ====> "+result);
	}
}

