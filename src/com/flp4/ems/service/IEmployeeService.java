package com.flp4.ems.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Employee;
import com.flp4.ems.domain.Role;
import com.flp4.ems.domain.SubDepartment;


public interface IEmployeeService {
	boolean addEmployee(Map<String,?> emp);
	boolean modifyEmployee(Employee emp);
	boolean deleteEmployee(String kin);
	Collection<Employee> getAllEmployee();
	Employee searchEmployeeByKin(String kin);
	List<Employee> searchEmployeeByAnyValue(String value);
	List<Role> getRoles();
	List<Department> getDepartment();
	List<SubDepartment> getSubDepartment(Department department);
}
