package com.mmi.mmi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.mmi.mmi.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	Employee findByIdNumber(int idNumber);
	List<Employee> findByName(String name);
	Page<Employee> findAll(Pageable pageable);
	Page<Employee> findByIsDelete(Pageable pageable,int status);
	List<Employee> findByIdNumber(Integer idNumber);

}
