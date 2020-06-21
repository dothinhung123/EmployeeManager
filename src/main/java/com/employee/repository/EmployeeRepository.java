package com.employee.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	@Query("select e from Employee e WHERE e.name LIKE %?1% or e.code Like %?1%")
	List<Employee> findByNameOrByCode(String value);

	

}
