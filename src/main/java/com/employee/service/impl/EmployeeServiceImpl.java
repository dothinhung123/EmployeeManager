package com.employee.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee.converter.EmployeeConverter;
import com.employee.dto.EmployeeDTO;
import com.employee.dto.EmployeeDTOSearch;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository  employeeRepository;
	@Autowired
	private EmployeeConverter converter;

	
	@Override
	public List<EmployeeDTO> findAll() {
		List<Employee> list = this.employeeRepository.findAll();
		return converter.entityToDto(list);
	}

	@Override
	public List<EmployeeDTO> findAll(Pageable pageable) {
		List<EmployeeDTO> results = new ArrayList<>();
	    List<Employee> list = this.employeeRepository.findAll(pageable).getContent();
	    for ( Employee employee: list) {
	    	EmployeeDTO employeeDTO = converter.entityToDto(employee);
			results.add(employeeDTO);
		}
		return results;
	    
		
	}


	@Override
	public EmployeeDTO save(EmployeeDTO e) {
		Employee newEmployee = new Employee();
		if(e.getId()>0) {
			Employee oldNewEntity = employeeRepository.findOne(e.getId());
			newEmployee = converter.dtoToEntity(e, oldNewEntity);
			
			
		}else {
			newEmployee = converter.dtoToEntity(e);
		}
		
		
		this.employeeRepository.save(newEmployee);
		return converter.entityToDto(newEmployee);
		
		}

	@Override
	public void delete(int id) {
		this.employeeRepository.delete(id);
		
	}

	@Override
	public List<EmployeeDTOSearch> findByNameOrCode(String value) {
	
		List<Employee> employee = this.employeeRepository.findByNameOrByCode(value);
		return converter.entityToDtoSearch(employee);
	}

	@Override
	public void deleteAll() {
		this.employeeRepository.deleteAll();
		
	}


}
