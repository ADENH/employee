package com.mmi.mmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmi.mmi.dto.PositionDTO;
import com.mmi.mmi.model.entity.Position;
import com.mmi.mmi.service.PositionService;

import javassist.NotFoundException;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PositionController {
	
	@Autowired
	PositionService positionService;
	
	@GetMapping("/positions")
	public Page<Position> getAllPositions(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size){
		return positionService.getAllPosition(page,size);
	}
	
	@PostMapping("/positions")
	public Position savePosition(@RequestBody PositionDTO positionDTO) {
		return positionService.savePosition(positionDTO);
	}
	
	@GetMapping("/positions/{code}")
	public PositionDTO getPositionByCode(@PathVariable("code") String code) throws NotFoundException  {
		return positionService.getPositionByCode(code);
	}
	

}
