package com.mmi.mmi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmi.mmi.dto.EmployeeDTO;
import com.mmi.mmi.model.Employee;
import com.mmi.mmi.service.EmployeeService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employee/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable int id) {
		return employeeService.getById(id);
	}
	
	@GetMapping("/employee/idNumber/{idNumber}")
	public EmployeeDTO getEmployeeByIdNumber(@PathVariable int idNumber) {
		return employeeService.getByIdNumber(idNumber);
	}
	
	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.saveEmployee(employeeDTO);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<Page<EmployeeDTO>> getAllEmployee(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size){
		return ResponseEntity.ok(employeeService.getAllEmployee(page,size));
	}
	
	@DeleteMapping("/employee/{idNumber}")
	public Employee deleteEmployee(@PathVariable int idNumber) {
		return employeeService.deleteEmployee(idNumber);
	}
	
	@PutMapping("/employee/{idNumber}")
	public Employee updateEmployee(@PathVariable int idNumber,@RequestBody EmployeeDTO employeeDTO) {
		System.out.println(employeeDTO);
		return employeeService.editEmployee(employeeDTO, idNumber);
	}

}
