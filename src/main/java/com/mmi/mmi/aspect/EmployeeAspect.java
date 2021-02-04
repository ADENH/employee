package com.mmi.mmi.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mmi.mmi.config.LoggingAspect;
import com.mmi.mmi.model.Employee;

@Aspect
@Component
public class EmployeeAspect {
	
	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
	
	@AfterReturning(value="execution(* com.mmi.mmi.service.serviceimpl.EmployeeServiceImpl.*(..))",returning="employee")  
	public void afterReturningAdvice(JoinPoint joinPoint, Employee employee)  
	{  
	LOGGER.info("After Returing method:"+joinPoint.getSignature());  
//	System.out.println(employee);  
	LOGGER.info(employee);
	}

}
