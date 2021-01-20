package com.attilavarga.customer.relationship.manager.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.attilavarga.customer.relationship.manager.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.attilavarga.customer.relationship.manager.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.attilavarga.customer.relationship.manager.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @Before: calling method: " + theMethod);
		
		Object[] args = theJoinPoint.getArgs();
		for (Object tempArg : args) {
			myLogger.info("=====>> argument: " + tempArg);
		}
	}
	
	@AfterReturning(pointcut="forAppFlow()", returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);
		myLogger.info("=====>> result: " + theResult);
	}
}
