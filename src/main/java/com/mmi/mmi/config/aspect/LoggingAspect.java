package com.mmi.mmi.config.aspect;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
public class LoggingAspect {

	/**
	 * Gets the logger package protected to facilitate override in unit tests
	 * 
	 * @param joinPoint
	 * @return
	 */
	Logger getLogger(JoinPoint joinPoint) {
		return LogManager.getLogger(joinPoint.getSignature().getDeclaringTypeName());
	}

	public LoggingAspect(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	private ObjectMapper objectMapper;

	@Pointcut("within(com.mmi.mmi..controller..*) ||  within(com.mmi.mmi..service..*)")
	public void applicationPointCut() {
	}

//	@Around("execution(* com.mmi.mmi..*(..)))")
//    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
//    {
//        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//          
//        //Get intercepted method details
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//          
//        final StopWatch stopWatch = new StopWatch();
//          
//        //Measure method execution time
//        stopWatch.start();
//        Object result = proceedingJoinPoint.proceed();
//        stopWatch.stop();
//  
//        //Log method execution time
//        LOGGER.info("Execution time of " + className + "." + methodName + " "
//                            + ":: " + stopWatch.getTotalTimeMillis() + " ms");
//  
//        return result;
//    }

	/**
	 * After Throwing - Advice to take when exceptions are thrown
	 *
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "applicationPointCut()", throwing = "e")
	public void logException(JoinPoint joinPoint, Throwable e) {
		this.getLogger(joinPoint).error("Exception in {}() with Cause =    {} and message {} ",
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "Null Cause", e.getMessage(),
				e);
	}

	/**
	 * Log Advice - Around advice to take when entering and exiting a method
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("applicationPointCut()")
	public Object logAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = this.getLogger(joinPoint);
		logger.trace("Entered: {} () with arguments = {}", joinPoint.getSignature().getName(),
				Arrays.deepToString(joinPoint.getArgs()));
		try {
			MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
			// Get intercepted method details
			String className = methodSignature.getDeclaringType().getSimpleName();
			String methodName = methodSignature.getName();

			final StopWatch stopWatch = new StopWatch();
			// Measure method execution time
			stopWatch.start();
			Object object = joinPoint.proceed();
			stopWatch.stop();
			
			// Log method execution time
			logger.info("Execution time of " + className + "." + methodName + " " + ":: "
					+ stopWatch.getTotalTimeMillis() + " ms");
			
			logger.trace("Exited: {} () with result = {}", joinPoint.getSignature().getName(),
					objectMapper.writeValueAsString(object));
			return object;
		} catch (Exception e) {
			logger.error("Exception {} in {}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getName());
			throw e;
		}
	}

}
