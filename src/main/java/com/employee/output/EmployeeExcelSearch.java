package com.employee.output;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.employee.dto.EmployeeDTOSearch;

public class EmployeeExcelSearch {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<EmployeeDTOSearch> employees ;
	public EmployeeExcelSearch(List<EmployeeDTOSearch> employees) {
		this.employees = employees;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Employees");
	}
	private void writeHeaderRow() {
		Row  row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("ID");
		cell = row.createCell(1);
		cell.setCellValue("CODE");
		cell = row.createCell(2);
		cell.setCellValue("GMAIL");
		cell = row.createCell(3);
		cell.setCellValue("PHONE");
		cell = row.createCell(4);
		cell.setCellValue("AGE");
		cell = row.createCell(5);
		cell.setCellValue("NAME");
		
	}
	private void writeDatarows() {
		int rowCount = 1;
		for(EmployeeDTOSearch e:employees) {
			Row row = sheet.createRow(rowCount++);
			Cell cell = row.createCell(0);
			cell.setCellValue(e.getId());
			cell = row.createCell(1);
			cell.setCellValue(e.getCode());
			cell = row.createCell(2);
			cell.setCellValue(e.getEmail());
			cell = row.createCell(3);
			cell.setCellValue(e.getPhone());
			cell = row.createCell(4);
			cell.setCellValue(e.getAge());
			cell = row.createCell(5);
			cell.setCellValue(e.getName());
		
		
		
		
		
		
		}
	}
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderRow();
		writeDatarows();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
	}
	public List<EmployeeDTOSearch> getEmployee() {
		return employees;
	}
	public void setEmployee(List<EmployeeDTOSearch> employees) {
		this.employees = employees;
	}

}
