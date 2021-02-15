package com.mmi.mmi.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mmi.mmi.dto.PositionDTO;
import com.mmi.mmi.model.entity.Employee;
import com.mmi.mmi.model.entity.Position;
import com.mmi.mmi.model.entity.User;
import com.mmi.mmi.repository.PositionRepository;
import com.mmi.mmi.service.serviceimpl.PositionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTest {
	
	@Mock
	private PositionRepository positionRepository;
	
	private PositionServiceImpl positionServiceImpl;
	
	@BeforeEach
	void setUp() {
		positionServiceImpl = new PositionServiceImpl(positionRepository);
	}
	
	@Test
	void getPositionByCodeTest() {
		Employee employee = new Employee();
		Set<Employee> dataEmployee = new HashSet<Employee>();
		dataEmployee.add(employee);
		
		User user = new User();
		Set<User> dataUser = new HashSet<User>();
		dataUser.add(user);
		
		Mockito.when(positionRepository.findByCode("MNG")).thenReturn(new Position(1,"MNG","Manager",0,dataEmployee,dataUser));
		PositionDTO found = positionServiceImpl.getPositionByCode("MNG");
		
		Assertions.assertNotNull(found);;
		Assertions.assertEquals("MNG", found.getCode());
		Assertions.assertEquals("Manager", found.getName());
	}
	
	@Test
	void getPositionByCodeNotFoundTest(){
		Assertions.assertThrows(EntityNotFoundException.class, () ->{
			positionServiceImpl.getPositionByCode("not found");
		});
	}
	
	
	
}
