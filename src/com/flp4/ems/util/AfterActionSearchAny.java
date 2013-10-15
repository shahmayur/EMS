package com.flp4.ems.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp4.ems.domain.Employee;
import com.flp4.ems.service.EmployeeServiceImpl;

public class AfterActionSearchAny extends AfterAction {

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeServiceImpl service=new EmployeeServiceImpl();
		List<Employee> employees=service.searchEmployeeByAnyValue(request.getParameter("string"));
		request.setAttribute("empl_list",employees);
		return "display.jsp";
	}

}
