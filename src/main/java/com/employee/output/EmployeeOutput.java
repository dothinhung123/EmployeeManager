package com.employee.output;

import java.util.ArrayList;
import java.util.List;

import com.employee.dto.EmployeeDTO;

public class EmployeeOutput {
private int page;
private int limit;
private List<EmployeeDTO> employee = new ArrayList<>();
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getLimit() {
	return limit;
}
public void setLimit(int limit) {
	this.limit = limit;
}
public List<EmployeeDTO> getEmployee() {
	return employee;
}
public void setEmployee(List<EmployeeDTO> employee) {
	this.employee = employee;
}
}
