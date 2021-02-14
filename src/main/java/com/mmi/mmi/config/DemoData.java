package com.mmi.mmi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mmi.mmi.dto.UserDto;
import com.mmi.mmi.model.Position;
import com.mmi.mmi.repository.PositionRepository;
import com.mmi.mmi.service.UserService;

@Component
public class DemoData {
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	UserService userService;
	
	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		
		Position position = new Position();
		position.setCode("PRGR");
		position.setName("Programmer");
		position.setIsDelete(0);
		positionRepository.save(position);
		
		Position position2 = new Position();
		position2.setCode("MNGR");
		position2.setName("Manager");
		position2.setIsDelete(0);
		positionRepository.save(position2);
		
		UserDto user = new UserDto();
		user.setCodeJabatan("PRGR");
		user.setEmail("adenurhidayat@gmail.com");
		user.setFirstName("ade");
		user.setLastName("nur hidayat");
		user.setPassword("123456789");
		user.setUserName("enhaa");
		userService.add(user);
		
	}

}
