package com.flp4.ems.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp4.ems.service.EmployeeServiceImpl;

public class AfterActionDelete extends AfterAction {

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeServiceImpl service=new EmployeeServiceImpl();
		boolean flag=service.deleteEmployee(request.getParameter("kin"));
		if(flag){
			return "actual_delete.jsp";
		}
		return "delete_error.jsp";
	}

}
