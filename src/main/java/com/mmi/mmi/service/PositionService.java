package com.mmi.mmi.service;

import org.springframework.data.domain.Page;

import com.mmi.mmi.dto.PositionDTO;
import com.mmi.mmi.model.Position;

public interface PositionService {
	PositionDTO getPositionByCode(String code);
	Page<Position> getAllPosition(int page, int size);
	Position savePosition(PositionDTO positionDTO);
}
