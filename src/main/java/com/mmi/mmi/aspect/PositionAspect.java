package com.mmi.mmi.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mmi.mmi.model.entity.Position;

@Aspect  
@Component  
public class PositionAspect {
	
	private static final Logger LOGGER = LogManager.getLogger(PositionAspect.class);
	
	@AfterReturning(value="execution(* com.mmi.mmi.service.serviceimpl.PositionServiceImpl.*(..))",returning="position")  
	public void afterReturningAdvice(JoinPoint joinPoint, Position position)  
	{  
	LOGGER.info("After Returing method:"+joinPoint.getSignature());  
	LOGGER.info(position);  
	}  
}
