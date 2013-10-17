package com.flp4.ems.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Employee;
import com.flp4.ems.domain.SubDepartment;
//import com.flp4.ems.dao.EmployeeDAOImplForCollection;
import com.flp4.ems.dao.EmployeeDAOImplForDatabase;
import com.flp4.ems.dao.IEmployee;
import com.flp4.ems.util.EMSConstants;
import com.flp4.ems.domain.Role;
import java.util.Date;

public class EmployeeServiceImpl implements IEmployeeService {
	
	private static IEmployee emp_dao= new EmployeeDAOImplForDatabase();
	private List<Role> role_list=new ArrayList<Role>();
	private List<Department> dept_list=new ArrayList<Department>();
	
	@Override
	public boolean addEmployee(Map<String,?>emp) {
		// TODO Auto-generated method stub
		Employee curr_emp=new Employee();
		curr_emp.setKin((String)emp.get(EMSConstants.kin));
		curr_emp.setName((String)emp.get(EMSConstants.name));
		Long contact=Long.parseLong((String)emp.get(EMSConstants.contact));
		curr_emp.setContact(contact);
		curr_emp.setDoj((Date)emp.get(EMSConstants.doj));
		curr_emp.setEmail((String)emp.get(EMSConstants.email));
		curr_emp.setRole((Role)emp.get(EMSConstants.role));
		curr_emp.setDept((Department)emp.get(EMSConstants.dept));
		curr_emp.setDept((Department)emp.get(EMSConstants.dept));
		curr_emp.setSub_dept((SubDepartment)emp.get(EMSConstants.sub_dept));
		curr_emp.setCity((String)emp.get(EMSConstants.city));
		
		if(emp_dao.addEmployee(curr_emp)){
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyEmployee(Employee emp) {
		// TODO Auto-generated method stub
		boolean flag=false;
		flag=emp_dao.modifyEmployee(emp);
		return flag;
	}

	@Override
	public boolean deleteEmployee(String kin) {
		// TODO Auto-generated method stub
		if(emp_dao.deleteEmployee(kin)){
			return true;
		}
		return false;
	}

	@Override
	public Collection<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return emp_dao.getAllEmployee();
	}

	@Override
	public Employee searchEmployeeByKin(String kin) {
		// TODO Auto-generated method stub
		Employee temp;
		temp=emp_dao.searchEmployeeByKin(kin);
		return temp;
	}

	@Override
	public List<Employee> searchEmployeeByAnyValue(String value) {
		// TODO Auto-generated method stub
		List<Employee> emp_list;
		emp_list=emp_dao.searchEmployeeByAnyValue(value);
		return emp_list;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
	//	if(!EmployeeDAOImplForCollection.isRoleGenerated){
			role_list=emp_dao.getRoles();
	//	}
		return role_list;
	}

	@Override
	public List<Department> getDepartment() {
		// TODO Auto-generated method stub
	//	if(!EmployeeDAOImplForCollection.isDepartmentGenerated){
			dept_list=emp_dao.getDepartments();
	//	}
		return dept_list;
	}

	@Override
	public List<SubDepartment> getSubDepartment(Department department) {
		// TODO Auto-generated method stub
		List<SubDepartment> sub_dept_list=new ArrayList<SubDepartment>();
		sub_dept_list=emp_dao.getSubDepartment(department);
		return sub_dept_list;
	}

}
