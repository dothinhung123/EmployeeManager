package com.employee.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.EmployeeDTOSearch;
import com.employee.entity.Employee;

@Component
public class EmployeeConverter {
public EmployeeDTO entityToDto(Employee e) {
	EmployeeDTO dto = new EmployeeDTO();
	dto.setId(e.getId());
	dto.setCode(e.getCode());
	dto.setEmail(e.getEmail());
	dto.setName(e.getName());
	dto.setPhone(e.getPhone());
	dto.setAge(e.getAge());
    return dto;
}
public List<EmployeeDTO> entityToDto(List<Employee> e){
	return e.stream().map(x->entityToDto(x)).collect(Collectors.toList());
}
public Employee dtoToEntity(EmployeeDTO e) {
	Employee empl = new Employee();
	empl.setId(e.getId());
	empl.setAge(e.getAge());
	empl.setCode(e.getCode());
	empl.setEmail(e.getEmail());
	empl.setName(e.getName());
	empl.setPhone(e.getPhone());
	return empl;
}
public List<Employee> dtoToEntity(List<EmployeeDTO> e){
	return e.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
}
public Employee dtoToEntity(EmployeeDTO dto, Employee e) {

	e.setId(dto.getId());
	e.setAge(dto.getAge());
	e.setCode(dto.getCode());
	e.setEmail(dto.getEmail());
	e.setName(dto.getName());
	e.setPhone(dto.getPhone());
	return e;
}
public EmployeeDTOSearch entityToDtoSearch(Employee e) {
	EmployeeDTOSearch dto = new EmployeeDTOSearch();
	dto.setId(e.getId());
	dto.setCode(e.getCode());
	dto.setEmail(e.getEmail());
	dto.setName(e.getName());
	dto.setPhone(e.getPhone());
	dto.setAge(e.getAge());
    return dto;
}
public List<EmployeeDTOSearch> entityToDtoSearch(List<Employee> e){
	return e.stream().map(x->entityToDtoSearch(x)).collect(Collectors.toList());
}
public Employee dtoToEntitySearch(EmployeeDTOSearch e) {
	Employee empl = new Employee();
	empl.setId(e.getId());
	empl.setAge(e.getAge());
	empl.setCode(e.getCode());
	empl.setEmail(e.getEmail());
	empl.setName(e.getName());
	empl.setPhone(e.getPhone());
	return empl;
}
public List<Employee> dtoToEntitySearch(List<EmployeeDTOSearch> e){
	return e.stream().map(x->dtoToEntitySearch(x)).collect(Collectors.toList());
}

}
