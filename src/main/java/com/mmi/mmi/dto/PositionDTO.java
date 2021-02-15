package com.mmi.mmi.dto;

import com.mmi.mmi.model.entity.Position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
	private String code;
	private String name;
	
	public void setDataPosition(Position position) {
		this.code = position.getCode();
		this.name = position.getName();
	}
	
}
