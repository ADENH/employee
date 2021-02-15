package com.mmi.mmi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.mmi.mmi.model.entity.Position;
import com.mmi.mmi.repository.PositionRepository;
import com.mmi.mmi.service.serviceimpl.PositionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTest {
	
	@Mock
	private PositionRepository positionRepository;
	
	@InjectMocks
	private PositionService positionService = new PositionServiceImpl();
	
	@BeforeEach
	void setUp() {
		Position position = new Position();
		position.setCode("MNG");
		position.setIsDelete(0);
		position.setName("Manager");
		Mockito.when(positionRepository.findByCode(position.getCode())).thenReturn(position);
	}
	
	@Test
	@Disabled
	void getPositionByCodeTest() {
//		String code = "MNG";
//		PositionDTO found = positionService.getPositionByCode(code);
//		assertThat(found.getCode()).isEqualTo(code);
	}
	
	@Test
	@Disabled
	void getPositionByCodeNotFoundTest(){
		Assertions.assertThrows(NotFoundException.class, () ->{
			positionService.getPositionByCode("not found");
		});
	}
	
	
	
}
