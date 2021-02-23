package com.mmi.mmi.service;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.mmi.mmi.dto.EmployeeDTO;
import com.mmi.mmi.model.entity.Employee;

public interface EmployeeService {
	Optional<Employee> getById(int id);
	EmployeeDTO getEmployeeByName(String name);
	Page<EmployeeDTO> getAllEmployee(int page, int size);
	Employee saveEmployee(EmployeeDTO employeeDTO) throws ParseException;
	Employee deleteEmployee(int idNumber);
	EmployeeDTO getByIdNumber(int idNumber);
	Employee editEmployee(EmployeeDTO employeeDTO, Integer idNumber) throws ParseException;

}
