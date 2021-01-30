package com.mmi.mmi.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mmi.mmi.dto.PositionDTO;
import com.mmi.mmi.model.Position;
import com.mmi.mmi.repository.PositionRepository;
import com.mmi.mmi.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	private PositionRepository positionRepository;

	@Override
	public PositionDTO getPositionByCode(String code) {
		PositionDTO positionDTO = new PositionDTO();
		Position position = positionRepository.findByCode(code);
		if(position != null) {
			positionDTO.setCode(position.getCode());
			positionDTO.setName(position.getName());
		}
		
		return positionDTO;
	}

	@Override
	public Page<Position> getAllPosition(int page,int size) {
		Pageable paging = PageRequest.of(page, size);
		return positionRepository.findByIsDelete(paging, 0);
	}

	@Override
	public Position savePosition(PositionDTO positionDTO) {
		Position position = new Position();
		position.setCode(positionDTO.getCode());
		position.setName(positionDTO.getName());
		position.setIsDelete(0);
		
		return positionRepository.save(position);
	}
	

}
