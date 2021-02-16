package com.mmi.mmi.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.mmi.mmi.model.entity.Position;

@DataJpaTest
public class PositionRepositoryTest {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Test
	void whenFindByCode_thenReturnPosition() {
		Position position = new Position("MNG","Manager");
		entityManager.persist(position);
		entityManager.flush();
		
		Position result = positionRepository.findByCode(position.getCode());
		Assertions.assertEquals(position.getCode(), result.getCode());
	}
	
	@Test
	void whenSave_thenReturnPosition() {
		Position position = new Position("MNG","Manager");
		
		Position result = positionRepository.save(position);
		
		Assertions.assertNotNull(result);
		Assertions.assertNotNull(result.getId());
		
	}
	
	@Test
	void whenFindByIsDelete_thenReturnAllPositionWithIsDeleteValueZero() {
		Position position = new Position("MNG","Manager",0);
		
		positionRepository.save(position);
		Pageable pageable = PageRequest.of(0, 5);
		Page<Position> dataPage = positionRepository.findByIsDelete(pageable, 0);
		
		Assertions.assertNotNull(dataPage.getContent());
		Assertions.assertNotNull(dataPage.getContent().get(0).getId());
		Assertions.assertEquals(position.getCode(), dataPage.getContent().get(0).getCode());
		
	}
}
