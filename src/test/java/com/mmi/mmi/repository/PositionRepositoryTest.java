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
	void whenFindByCode_thenReturnEmptyPosition() {
		Position position = new Position("MNG","Manager");
		Position result = positionRepository.findByCode(position.getCode());
		Assertions.assertNull(result);
	}
	
	@Test
	void whenSave_thenReturnPosition() {
		Position position = new Position("MNG","Manager",0);
		
		Position result = positionRepository.save(position);
		
		Assertions.assertNotNull(result);
		Assertions.assertNotNull(result.getId());
		Assertions.assertEquals(position.getCode(), result.getCode());
		Assertions.assertEquals(position.getName(), result.getName());
		Assertions.assertEquals(position.getIsDelete(), result.getIsDelete());
		
	}
	
	@Test
	void whenFindByIsDelete_thenReturnAllPositionWithIsDeleteValueZero() {
		Position position = new Position("MNG","Manager",0);
		Position position2 = new Position("PGRM","Programmer",1);
		
		positionRepository.save(position);
		positionRepository.save(position2);
		Pageable pageable = PageRequest.of(0, 5);
		Page<Position> dataPage = positionRepository.findByIsDelete(pageable, 0);
		
		Assertions.assertNotNull(dataPage.getContent());
		Assertions.assertEquals(1, dataPage.getContent().size());
		Assertions.assertNotNull(dataPage.getContent().get(0).getId());
		Assertions.assertEquals("MNG", dataPage.getContent().get(0).getCode());
		
		
	}
	
	@Test
	void whenFindByIsDelete_thenReturnAllPositionWithIsDeleteValueOne() {
		Position position = new Position("MNG","Manager",0);
		Position position2 = new Position("PGRM","Programmer",1);
		
		positionRepository.save(position);
		positionRepository.save(position2);
		Pageable pageable = PageRequest.of(0, 5);
		Page<Position> dataPage = positionRepository.findByIsDelete(pageable, 1);
		
		Assertions.assertNotNull(dataPage.getContent());
		Assertions.assertNotNull(dataPage.getContent().get(0).getId());
		Assertions.assertEquals("PGRM", dataPage.getContent().get(0).getCode());
		Assertions.assertEquals(1, dataPage.getContent().size());
		
	}
	
	@Test
	void whenFindByIsDelete_thenReturnEmpty() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Position> dataPage = positionRepository.findByIsDelete(pageable, 0);
		Assertions.assertEquals(true, dataPage.getContent().isEmpty());
		
	}
}
