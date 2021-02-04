package com.mmi.mmi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mmi.mmi.model.Position;

@Aspect  
@Component  
public class PositionAspect {
	@AfterReturning(value="execution(* com.mmi.mmi.service.serviceimpl.PositionServiceImpl.*(..))",returning="position")  
	public void afterReturningAdvice(JoinPoint joinPoint, Position position)  
	{  
	System.out.println("After Returing method:"+joinPoint.getSignature());  
	System.out.println(position);  
	}  
}
