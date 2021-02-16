package com.mmi.mmi.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.hibernate.UnresolvableObjectException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.mmi.mmi.dto.EmployeeDTO;
import com.mmi.mmi.dto.PositionDTO;
import com.mmi.mmi.model.entity.Employee;
import com.mmi.mmi.model.entity.Position;
import com.mmi.mmi.model.entity.User;
import com.mmi.mmi.repository.PositionRepository;
import com.mmi.mmi.service.converter.GenderConverter;
import com.mmi.mmi.service.serviceimpl.PositionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PositionServiceImplTest {
	@Mock
	private static PositionRepository positionRepository;
	@InjectMocks
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
		
		Assertions.assertNotNull(found);
		Assertions.assertEquals("MNG", found.getCode());
		Assertions.assertEquals("Manager", found.getName());
	}
	
	@Test
	void getPositionByCodeNotFoundTest(){
		Assertions.assertThrows(EntityNotFoundException.class, () ->{
			positionServiceImpl.getPositionByCode("not found");
		});
	}
	
	@Test
	void savePositionTest() {
		Employee employee = new Employee();
		Set<Employee> dataEmployee = new HashSet<Employee>();
		dataEmployee.add(employee);
		
		User user = new User();
		Set<User> dataUser = new HashSet<User>();
		dataUser.add(user);

		Mockito.when(positionRepository.save(Mockito.any(Position.class))).thenReturn(new Position(1,"MNG","Manager",0,dataEmployee,dataUser));
		
		Position position = positionServiceImpl.savePosition(new PositionDTO("MNG", "Manager"));
		
		Mockito.verify(positionRepository,Mockito.times(1))
			.save(Mockito.any(Position.class));
		
		Assertions.assertNotNull(position);
		Assertions.assertNotNull(position.getId());
		Assertions.assertEquals("MNG", position.getCode());
		Assertions.assertEquals("Manager", position.getName());
		Assertions.assertEquals(0, position.getIsDelete());
//		Assertions.assertEquals(position.getCode(), mockPosition.getCode());
	}
	
	@Test
	void savePositionFailedTest() {
		
		Employee employee = new Employee();
		Set<Employee> dataEmployee = new HashSet<Employee>();
		dataEmployee.add(employee);
		
		User user = new User();
		Set<User> dataUser = new HashSet<User>();
		dataUser.add(user);

		Mockito.when(positionRepository.save(Mockito.any(Position.class))).thenReturn(new Position(null,"MNG","Manager",0,dataEmployee,dataUser));
		
		Assertions.assertThrows(UnresolvableObjectException.class , () -> {
			positionServiceImpl.savePosition(new PositionDTO("MNG", "Manager"));
		});
	}
	
	@Test 
	void getAllPositionTest(){
		
		Employee employee = new Employee();
		Set<Employee> dataEmployee = new HashSet<Employee>();
		dataEmployee.add(employee);
		
		User user = new User();
		Set<User> dataUser = new HashSet<User>();
		dataUser.add(user);
		
		Position position = new Position(1,"MNG","Manager",0,dataEmployee,dataUser);
		List<Position> listPositions = new ArrayList<Position>();
		listPositions.add(position);
		
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Position> dataPosiPage = new PageImpl<Position>(listPositions
				.stream()
				.map(positions -> new Position(
						positions.getId(),
						positions.getCode(),
						positions.getName(),
						positions.getIsDelete(),
						positions.getEmployees(),
						positions.getUser()))
				.collect(Collectors.toList()), paging, 1);
		
		Mockito.when(positionRepository.findByIsDelete(Mockito.any(Pageable.class),Mockito.any(int.class))).thenReturn(dataPosiPage);
		
		Page<Position> results = positionServiceImpl.getAllPosition(0, 5);
		
		Mockito.verify(positionRepository,Mockito.times(1))
		.findByIsDelete(Mockito.any(Pageable.class),Mockito.any(int.class));
		
		Assertions.assertNotNull(results);
		Assertions.assertEquals("MNG", results.getContent().get(0).getCode());
		Assertions.assertEquals("Manager", results.getContent().get(0).getName());
	}
}
