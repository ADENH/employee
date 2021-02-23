package com.mmi.mmi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mmi.mmi.dto.PositionDTO;
import com.mmi.mmi.model.entity.Employee;
import com.mmi.mmi.model.entity.Position;
import com.mmi.mmi.model.entity.User;
import com.mmi.mmi.service.PositionService;

import javassist.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class PositionControllerTest {
	
//	@Autowired
//	MockMvc mockMvc;
	
//	@MockBean
//	PositionService positionServiceTest;
	
	@InjectMocks
	PositionController positionController;
	
	@Mock
	PositionService positionService;
	
	@Test
	void getAllPositionsTest() throws Exception {
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
		
		MockHttpServletRequest request =  new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Mockito.when(positionService.getAllPosition(Mockito.anyInt(), Mockito.anyInt())).thenReturn(dataPosiPage);
		
//		this.mockMvc
//			.perform(get("/api/positions"))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$content.[0].id", is(1)));
//		
//		Page<Position> dataPositions = positionService.getAllPosition(0, 5);
		Page<Position> dataPositions = positionController.getAllPositions(0,5);
		
		Assertions.assertEquals(false, dataPositions.getContent().isEmpty());
		Assertions.assertEquals(1, dataPositions.getContent().size());
		Assertions.assertEquals(position.getCode(), dataPositions.getContent().get(0).getCode());
	}
	
	@Test
	void savePositionTest() {
		Employee employee = new Employee();
		Set<Employee> dataEmployee = new HashSet<Employee>();
		dataEmployee.add(employee);
		
		User user = new User();
		Set<User> dataUser = new HashSet<User>();
		dataUser.add(user);
		
		Position position = new Position(1,"MNG","Manager",0,dataEmployee,dataUser);
		PositionDTO positionDTO = new PositionDTO("MNG", "Manager");
		
		Mockito.when(positionService.savePosition(Mockito.any(PositionDTO.class))).thenReturn(position);
		Position result = positionController.savePosition(positionDTO);
		
		Assertions.assertNotNull(result);
		Assertions.assertNotNull(result.getId());
		Assertions.assertEquals(positionDTO.getCode(), result.getCode());
		Assertions.assertEquals(positionDTO.getName(), result.getName());
		
	}
	
	@Test
	void getPositionByCode() throws NotFoundException {
		PositionDTO positionDTO = new PositionDTO("MNG", "Manager");
		Mockito.when(positionService.getPositionByCode(Mockito.anyString())).thenReturn(positionDTO);
		PositionDTO result = positionController.getPositionByCode(positionDTO.getCode());
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(positionDTO.getCode(), result.getCode());
		Assertions.assertEquals(positionDTO.getName(), result.getName());
		
	}
	
	@Test
	void getPositionByCodeNotFound() {
		
		Mockito.when(positionService.getPositionByCode(Mockito.anyString())).thenThrow(EntityNotFoundException.class);
		assertThrows(EntityNotFoundException.class,()-> {
			positionController.getPositionByCode("man tap");
		});
		
	}
}
