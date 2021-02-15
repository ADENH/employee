package com.mmi.mmi.service.serviceimpl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mmi.mmi.config.aspect.Compliance;
import com.mmi.mmi.dto.PositionDTO;
import com.mmi.mmi.model.ComplianceAction;
import com.mmi.mmi.model.entity.Position;
import com.mmi.mmi.repository.PositionRepository;
import com.mmi.mmi.service.PositionService;


@Service
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	private PositionRepository positionRepository;

	public PositionServiceImpl(PositionRepository positionRepository) {
		super();
		this.positionRepository = positionRepository;
	}

	@Override
	@Compliance(action = ComplianceAction.read)
	public PositionDTO getPositionByCode(String code) {
		
		PositionDTO positionDTO = new PositionDTO();
		Position position = positionRepository.findByCode(code);
		
		if(position == null) throw new EntityNotFoundException("Jabatan tidak ditemukan");
		
		positionDTO.setCode(position.getCode());
		positionDTO.setName(position.getName());
		return positionDTO;
	}

	@Override
	@Compliance(action = ComplianceAction.read)
	public Page<Position> getAllPosition(int page,int size) {
		Pageable paging = PageRequest.of(page, size);
		return positionRepository.findByIsDelete(paging, 0);
	}

	@Override
	@Compliance(action = ComplianceAction.create)
	public Position savePosition(PositionDTO positionDTO) {
		Position position = new Position();
		position.setDataPositionDto(positionDTO);
		position = positionRepository.save(position);
		return position;
	}
	

}
