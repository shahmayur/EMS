package com.flp4.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Role;
import com.flp4.ems.domain.SubDepartment;
import com.flp4.ems.domain.SubDept;

public class Modify extends Action{

	@Override
	public String doAction(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		Connection connector=EMSConnector.createConnection("jdbc:mysql://localhost:3306/emsphase2","root","password");
		Statement stmt=null;
		Statement stmt1=null;
		Statement stmt2=null;
		String query=null;
		List<Role> role_list=new ArrayList<Role>();
		List<Department> dept_list=new ArrayList<Department>();
		List<SubDepartment> subdept_list=new ArrayList<SubDepartment>();
		ResultSet rs=null;
/*		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				connector=DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			 stmt=connector.createStatement();
			 stmt1=connector.createStatement();
			 stmt2=connector.createStatement();
			 query="Select * from role";
			 rs=stmt.executeQuery(query);
			 while(rs.next()){
				 Role temp=new Role();
				 temp.setId(rs.getInt("Role_id"));
				 temp.setR_name(rs.getString("Role_name"));
				 role_list.add(temp);
			 }
			query="select * from department";
			rs=stmt1.executeQuery(query);
			while(rs.next()){
				Department temp=new Department();
				temp.setId(rs.getInt("Department_id"));
				temp.setDept_name(rs.getString("Department_name"));
				dept_list.add(temp);
			}
			query="select * from subdepartment";
			rs=stmt2.executeQuery(query);
			int i=0;
			while(rs.next()){
				SubDept temp_subdept=new SubDept();
				SubDepartment temp=new SubDepartment();
				temp_subdept.setId(rs.getInt("SubDepartment_id"));
				temp_subdept.setSubdept_name(rs.getString("SubDepartment_name"));
				temp.setSubDept(temp_subdept);
				temp.setDept(dept_list.get(i));
				subdept_list.add(temp);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		HttpSession session=req.getSession(); 
		session.setAttribute("roles",role_list);
		session.setAttribute("departments",dept_list);
		session.setAttribute("subdepartments",subdept_list);
		
		try {
			stmt.close();
			stmt1.close();
			stmt2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connector.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		
		System.out.println(role_list);
		System.out.println(dept_list);
		System.out.println(subdept_list);*/
		
		return "modify.jsp";
	}
	

}
