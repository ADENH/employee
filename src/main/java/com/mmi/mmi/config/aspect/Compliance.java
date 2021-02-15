package com.mmi.mmi.config.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mmi.mmi.model.ComplianceAction;

/**
* The compliance annotation
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Compliance {
		/**
	   * Action.
	   * @return the compliance action enum value
	   */
	   ComplianceAction action();
}
