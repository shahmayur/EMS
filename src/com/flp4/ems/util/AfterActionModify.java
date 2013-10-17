package com.flp4.ems.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Employee;
import com.flp4.ems.domain.Role;
import com.flp4.ems.domain.SubDepartment;
import com.flp4.ems.service.EmployeeServiceImpl;

public class AfterActionModify extends AfterAction{

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		EmployeeServiceImpl emp_service=new EmployeeServiceImpl();
		String kin=null;
		kin=request.getParameter("kin");
		HttpSession session=request.getSession();
		
		Employee emp=null;
		emp=emp_service.searchEmployeeByKin(kin);
		if(emp!=null){
			emp.setCity(request.getParameter("city"));
			emp.setContact(Long.parseLong(request.getParameter("contact")));
			emp.setEmail(request.getParameter("email"));
			emp.setName(request.getParameter("name"));
			List<Role> role_list=(List<Role>) session.getAttribute("roles");
			for(Role r:role_list){
				if(r.getR_name().equalsIgnoreCase(request.getParameter("role"))){
					emp.setRole(r);
					break;
				}
			}
			
			List<Department> dept_list=(List<Department>) session.getAttribute("departments");
			for(Department d:dept_list){
				if(d.getDept_name().equalsIgnoreCase(request.getParameter("dept"))){
					emp.setDept(d);
					break;
				}
			}
			List<SubDepartment> subdept_list=(List<SubDepartment>) session.getAttribute("subdepartments");
			for(SubDepartment s:subdept_list){
				if(s.getSubDept().getSubdept_name().equalsIgnoreCase(request.getParameter("subdept"))){
					emp.setSub_dept(s);
					break;
				}
				
			}
			System.out.println(emp);
			emp_service.modifyEmployee(emp);
			return "actual_modify.jsp";	
		}
		
		return "error.jsp";
	}

}
