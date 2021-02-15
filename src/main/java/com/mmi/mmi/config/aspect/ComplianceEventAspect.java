package com.mmi.mmi.config.aspect;

import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import com.mmi.mmi.dto.ComplianceEvent;

@Aspect
public class ComplianceEventAspect {
		private static final Logger LOGGER = LogManager.getLogger(ComplianceEventAspect.class);
		/**
	   * Log compliance event from a new thread
	   * @param joinPoint the join point
	   * @param compliance the compliance
	   */
	   @After("@annotation(compliance)")
	   public void logComplianceEvent(JoinPoint joinPoint, Compliance compliance) {
	        CompletableFuture.runAsync(() -> {
	             ComplianceEvent complianceEvent = buildComplianceEvent(joinPoint, compliance);
	             LOGGER.info(complianceEvent);
	            // TODO: go do something to record the compliance event
	        });
	   }
	   
	   /**
		 * Builds the compliance event.
		 *
		 * @param joinPoint the join point
		 * @param compliance the compliance
		 * @return the compliance event
		 */
		private ComplianceEvent buildComplianceEvent(JoinPoint joinPoint, Compliance compliance) {
			String className = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			
			ComplianceEvent complianceEvent = new ComplianceEvent();
			complianceEvent.setAction(compliance.action());
			complianceEvent.setResource(className);
			complianceEvent.setEventSource(className + "." + methodName);
			return complianceEvent;
		}
}
