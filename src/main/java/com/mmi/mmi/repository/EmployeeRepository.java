package com.mmi.mmi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmi.mmi.model.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByIdNumber(int idNumber);
	List<Employee> findByName(String name);
	Page<Employee> findAll(Pageable pageable);
	Page<Employee> findByIsDelete(Pageable pageable,int status);
	List<Employee> findByIdNumber(Integer idNumber);

}
