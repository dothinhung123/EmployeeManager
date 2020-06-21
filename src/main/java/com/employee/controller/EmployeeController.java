package com.employee.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


import com.employee.dto.EmployeeDTO;
import com.employee.dto.EmployeeDTOSearch;
import com.employee.output.EmployeeExcel;
import com.employee.output.EmployeeExcelSearch;
import com.employee.output.EmployeeOutput;

import com.employee.service.EmployeeService;
import com.employee.service.impl.EmployeeServiceImpl;
@CrossOrigin
@RestController
@RequestMapping
public class EmployeeController {
	@Autowired
	private EmployeeService employee;
	
	@GetMapping(value="/employee")
	public EmployeeOutput ListEmployee(@RequestParam(value="page",required = false) Integer page,@RequestParam(value="limit", required = false) Integer limit) {
		EmployeeOutput output = new EmployeeOutput();
		if(page!=null && limit !=null) {
			Pageable pageable = new PageRequest(page-1,limit);
			output.setEmployee(employee.findAll(pageable));
			output.setPage(page);
			output.setLimit(limit);
		}
		else {
		output.setEmployee(employee.findAll());
		}
		return output;
	
		
		
		}
	@PostMapping(value="/employee")
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO e) {
		return employee.save(e);
	}
	@PutMapping(value="/employee/{id}")
	public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO e , @PathVariable int id) {
		e.setId(id);
		return employee.save(e);
	}
	@DeleteMapping(value="/employee/{id}")
	public void delete(@PathVariable int id) {
		employee.delete(id);
	}
	@DeleteMapping(value="employee")
	public void deleteAll() {
		employee.deleteAll();
	}
	@GetMapping(value="/employee/search")
	public List<EmployeeDTOSearch> findByName(@RequestParam(value="value",required = false) String value ) {
		return this.employee.findByNameOrCode(value);
	}
	
	@GetMapping(value="/employee/report")
	public void exportToExcel(HttpServletResponse response,HttpServletRequest  request,@RequestParam(value="value",required=false)String value  ) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey ="Content-Disposition";
		String filename="report.xlsx";
		String headerValue = "attachement; filename="+filename;
		response.setHeader(headerKey, headerValue);
		if(value!=null) {
			List<EmployeeDTOSearch>list = this.employee.findByNameOrCode(value);
			EmployeeExcelSearch excelExporter = new EmployeeExcelSearch(list);
			excelExporter.export(response);
		}
		else {
			List<EmployeeDTO>list = this.employee.findAll();
			EmployeeExcel excelExporter = new EmployeeExcel(list);
			excelExporter.export(response);
		}
		
		
	}
	
	

}
