package com.flp4.ems.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp4.ems.domain.Employee;
import com.flp4.ems.service.EmployeeServiceImpl;

public class AfterActionSearch extends AfterAction{

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeServiceImpl emp_service=new EmployeeServiceImpl();
		Employee emp=emp_service.searchEmployeeByKin(request.getParameter("search"));
		request.setAttribute("employee",emp);
		return "actual_search.jsp";
	}

}
