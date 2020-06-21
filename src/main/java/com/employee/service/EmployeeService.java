package com.employee.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.EmployeeDTOSearch;

public interface EmployeeService {
List<EmployeeDTO> findAll();
List<EmployeeDTO> findAll(Pageable pageable);
EmployeeDTO save(EmployeeDTO employee);
void delete(int id);
void deleteAll();
List<EmployeeDTOSearch> findByNameOrCode(String value);

}
