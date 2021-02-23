package com.mmi.mmi.service.serviceimpl;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.UnresolvableObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mmi.mmi.config.aspect.Compliance;
import com.mmi.mmi.config.util.DateFormatConverter;
import com.mmi.mmi.dto.EmployeeDTO;
import com.mmi.mmi.model.ComplianceAction;
import com.mmi.mmi.model.entity.Employee;
import com.mmi.mmi.repository.EmployeeRepository;
import com.mmi.mmi.repository.PositionRepository;
import com.mmi.mmi.service.EmployeeService;
import com.mmi.mmi.service.converter.GenderConverter;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);
	
	@Override
	@Compliance(action = ComplianceAction.read)
	public Optional<Employee> getById(int id) {
		return employeeRepository.findById(id);
	}

	@Override
	public EmployeeDTO getEmployeeByName(String name) {
		return null;
	}

	@Override
	@Compliance(action = ComplianceAction.create)
	public Employee saveEmployee(EmployeeDTO employeeDTO) throws ParseException {
		DateFormatConverter dateFormatConverter = new DateFormatConverter();
		LOGGER.info("return employee DTO "+employeeDTO);
		Employee employee = new Employee();
		employee.setBirthDate(dateFormatConverter.dateConverter(employeeDTO.getTanggalLahir()));
		employee.setGender(employeeDTO.getJenisKelamin() == GenderConverter.gender.Pria ? 1 : 2);
		employee.setIdNumber(employeeDTO.getNip());
		employee.setIsDelete(0);
		employee.setName(employeeDTO.getNama());
		employee.setPosition(positionRepository.findByCode(employeeDTO.getCodeJabatan()));
		LOGGER.info("return employee sebelum disave "+employee);
		employee = employeeRepository.save(employee);
		if(employee.getId() == null ) throw new UnresolvableObjectException(Employee.class,"Gagal save data employee");
		employee.setBirthDate(dateFormatConverter.dateConverter(employeeDTO.getTanggalLahir()));
		return employee;
	}

	@Override
	@Compliance(action = ComplianceAction.update)
	public Employee editEmployee(EmployeeDTO employeeDTO,Integer idNumber) throws ParseException {
		Employee employee = new Employee();
		System.out.println(employeeDTO);
		List<Employee> employees = employeeRepository.findByIdNumber(idNumber);
		if(!employees.isEmpty()) {
			employee = setEmployeeData(employeeDTO, employees);
			return employeeRepository.save(employee);
		}
//		Position position = positionRepository.findByCode(employeeDTO.getCodeJabatan());
//		System.out.println(employees.get(0));
//		if(employee.getIdNumber() != null) {
//			System.out.println(position);
//			employee.setBirthDate(employeeDTO.getTanggalLahir());
//			employee.setGender(employeeDTO.getJenisKelamin() == GenderConverter.gender.Pria ? 1 : 2);
//			employee.setIdNumber(employeeDTO.getNip());
//			employee.setIsDelete(0);
//			employee.setName(employeeDTO.getNama());
//			employee.setPosition(positionRepository.findByCode(employeeDTO.getCodeJabatan()));
//			System.out.println(employee);
//			return employeeRepository.save(employee);
//		}
//		return employee;
		return employee;
	}

	private Employee setEmployeeData(EmployeeDTO employeeDTO, List<Employee> employees) throws ParseException {
		DateFormatConverter dateFormatConverter= new DateFormatConverter();
		Employee employee;
		employee = employees.get(0);
		employee.setBirthDate(dateFormatConverter.dateConverter(employeeDTO.getTanggalLahir()));
		employee.setGender(employeeDTO.getJenisKelamin() == GenderConverter.gender.Pria ? 1 : 2);
		employee.setIdNumber(employeeDTO.getNip());
		employee.setIsDelete(0);
		employee.setName(employeeDTO.getNama());
		employee.setPosition(positionRepository.findByCode(employeeDTO.getCodeJabatan()));
		return employee;
	}

	@Override
	@Compliance(action = ComplianceAction.delete)
	public Employee deleteEmployee(int idNumber) {
		Employee employee = employeeRepository.findByIdNumber(idNumber);
		if(employee.getIdNumber() != null) {
			employee.setIsDelete(1);
			return employeeRepository.save(employee);
		}
		return employee;
	}

	@Override
	public Page<EmployeeDTO> getAllEmployee(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		
		Page<EmployeeDTO> data = findAllEmployeeConverter(paging);
		
		return data;
	}
	
	public Page<EmployeeDTO> findAllEmployeeConverter(Pageable pageable){
		Page<Employee> paging = employeeRepository.findByIsDelete(pageable, 0);
		int totalElements = (int) paging.getTotalElements();
		return new PageImpl<EmployeeDTO>(paging.getContent()
				.stream()
				.map(employee -> new EmployeeDTO(
						employee.getName(),
						employee.getBirthDate().toString(),
						employee.getPosition().getCode(),
						employee.getPosition().getName(),
						employee.getIdNumber(),
						GenderConverter.convert(employee.getGender())))
				.collect(Collectors.toList()), pageable, totalElements);
	}

	@Override
	public EmployeeDTO getByIdNumber(int idNumber) {
		Employee employee = employeeRepository.findByIdNumber(idNumber);
		EmployeeDTO employeeDTO = new EmployeeDTO();
		if(employee.getIdNumber() != null) {
			employeeDTO.setDataEmployee(employee);
			return employeeDTO;
		}
		return employeeDTO;
	}

}
