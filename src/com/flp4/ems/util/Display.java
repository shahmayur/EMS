package com.flp4.ems.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp4.ems.domain.Employee;
import com.flp4.ems.service.EmployeeServiceImpl;

public class Display extends Action {

	@Override
	public String doAction(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		List<Employee> emp_list;
/*		List<String> role_list=new ArrayList<String>();
		List<String> dept_list=new ArrayList<String>();;
		List<String> subdept_list=new ArrayList<String>();*/
		EmployeeServiceImpl emp_service=new EmployeeServiceImpl();
		emp_list=(List<Employee>)emp_service.getAllEmployee();
/*		for(Employee e:emp_list){
			role_list.add(e.getRole().getR_name());
			dept_list.add(e.getDept().getDept_name());
			subdept_list.add(e.getSub_dept().getSubDept().getSubdept_name());
		}*/
		System.out.println(emp_list);
		req.setAttribute("empl_list",emp_list);
/*		req.setAttribute("rl_list",role_list);
		req.setAttribute("dpt_list",dept_list);
		req.setAttribute("subdpt_list",subdept_list);*/
		return "display.jsp";
	}

}
