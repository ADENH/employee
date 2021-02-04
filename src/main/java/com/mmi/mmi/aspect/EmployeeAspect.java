package com.mmi.mmi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mmi.mmi.model.Employee;

@Aspect
@Component
public class EmployeeAspect {
	@AfterReturning(value="execution(* com.mmi.mmi.service.serviceimpl.EmployeeServiceImpl.*(..))",returning="employee")  
	public void afterReturningAdvice(JoinPoint joinPoint, Employee employee)  
	{  
	System.out.println("After Returing method:"+joinPoint.getSignature());  
	System.out.println(employee);  
	}

}
