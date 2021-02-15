package com.mmi.mmi.config.aspect;

import javax.persistence.PersistenceException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Aspect
public class RepositoryAspect {
		/**
	   * Constructor
	   */
	   public RepositoryAspect(MessageSource messageSource) {
	        this.messageSource = messageSource;
	   }
	   private MessageSource messageSource;
	   
	   @Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))")
	   public void repositoryPointCut() {
	   }
	   
	   @AfterThrowing(pointcut = "repositoryPointCut()", throwing = "e")
	   public void convertToPersistenceException(JoinPoint joinPoint, Throwable e) {
	        String message = messageSource.getMessage("repositoryaspect.persistenceexception.message", null, LocaleContextHolder.getLocale());
	        throw new PersistenceException(message, e);
	   }
}
