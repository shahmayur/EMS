package com.flp4.ems.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Role;
import com.flp4.ems.domain.SubDepartment;
import com.flp4.ems.service.EmployeeServiceImpl;
import com.flp4.ems.service.IEmployeeService;

public class AfterActionCreate extends AfterAction {

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		IEmployeeService emp=new EmployeeServiceImpl();
		Map<String,Object> emp_map=new HashMap<String,Object>();
		String temp;
		Role temp_role=null;
		Department temp_dept=null;
		SubDepartment temp_subdept=null;
		
		temp=request.getParameter("kinid");
		emp_map.put(EMSConstants.kin,temp);
		
		temp=request.getParameter("name");
		emp_map.put(EMSConstants.name,temp);
		
		temp=request.getParameter("emailid");
		emp_map.put(EMSConstants.email,temp);
		
		temp=request.getParameter("contact");
		emp_map.put(EMSConstants.contact,temp);
		
		temp=request.getParameter("city");
		emp_map.put(EMSConstants.city,temp);
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		temp=request.getParameter("doj");
		Date d=new Date();
		try {
			d=sdf.parse(temp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		emp_map.put(EMSConstants.doj,d);
		
/*		temp=request.getParameter("role");
		emp_map.put(EMSConstants.role,temp);
		
		temp=request.getParameter("department");
		emp_map.put(EMSConstants.dept,temp);
		
		temp=request.getParameter("subdept");
		emp_map.put(EMSConstants.sub_dept,temp);
*/
		List<Role> role_list=emp.getRoles();
		Iterator<Role> role_itr=role_list.iterator();
		temp=request.getParameter("role");	
		while(role_itr.hasNext()){
			temp_role=role_itr.next();
			if(temp_role.getR_name().equalsIgnoreCase(temp)){
				break;
			}
		}
		emp_map.put(EMSConstants.role,temp_role);

		temp=request.getParameter("department");
//Apply Some Better Logic Here		
		int ch=0;
		emp_map.put(EMSConstants.dept,temp);		
		List<Department> dept_list=emp.getDepartment();
		Iterator<Department> dept_itr=dept_list.iterator();
		while(dept_itr.hasNext()){
			ch++;
			temp_dept=dept_itr.next();
			if(temp_dept.getDept_name().equalsIgnoreCase(temp)){
				break;
			}
			
		}
		emp_map.put(EMSConstants.dept,temp_dept);		
		
		List<SubDepartment> sub_dept_list=emp.getSubDepartment(dept_list.get(ch-1));
		Iterator<SubDepartment> sub_dept_itr=sub_dept_list.iterator();
		while(sub_dept_itr.hasNext()){
			temp_subdept=sub_dept_itr.next();
			if(temp_subdept.getSubDept().getSubdept_name().equalsIgnoreCase(temp)){
				break;
			}
		}
		emp_map.put(EMSConstants.sub_dept,temp_subdept);		
		emp.addEmployee(emp_map);
		return "actual_create.jsp";
	}

}
