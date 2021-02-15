package com.mmi.mmi.config.aspect;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
	
		private ObjectMapper objectMapper;
		private MessageSource messageSource;
		
		public AspectConfig(ObjectMapper objectMapper, MessageSource messageSource) {
	        this.objectMapper = objectMapper;
	        this.messageSource = messageSource;
	   }
		
			/**
		   * Logger aspect bean.
		   * @param environment the environment
		   * @return the logger aspect
		   */
		   @Bean
		   public LoggingAspect loggingAspect(@Lazy Environment environment) {
		      return new LoggingAspect(objectMapper);
		   }
		   
		   /**
		    * Repository aspect bean.
		    * @param environment the environment
		    * @return the repository aspect
		    */
		    @Bean
		    public RepositoryAspect repositoryAspect(Environment environment){
		         return new RepositoryAspect(messageSource);
		    }
		    
		    /**
		     * Compliance event bean.
		     * @param environment the environment
		     * @return the compliance event aspect
		     */
		     @Bean
		     public ComplianceEventAspect complianceEvent(Environment environment) {
		          return new ComplianceEventAspect();
		     }
}
