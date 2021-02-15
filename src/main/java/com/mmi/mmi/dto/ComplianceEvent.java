package com.mmi.mmi.dto;

import com.mmi.mmi.model.ComplianceAction;

import lombok.Data;

@Data
public class ComplianceEvent {
	/** The action. */
	private ComplianceAction action;
	
	/** The resource. */
	private String resource;
	
	/** The event source. */
	private String eventSource;
}
