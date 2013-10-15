package com.flp4.ems.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Employee;
import com.flp4.ems.domain.Role;
import com.flp4.ems.domain.SubDepartment;
import com.flp4.ems.domain.SubDept;
import com.flp4.ems.util.EMSConnector;

public class EmployeeDAOImplForDatabase implements IEmployee{
	
	Connection connect;
	//private static Calendar cal=Calendar.getInstance();
	public EmployeeDAOImplForDatabase(){
		connect=EMSConnector.createConnection("jdbc:mysql://localhost:3306/emsphase2","root","password");
	}

	private boolean createStatement(String query,Employee emp,int opt){
		java.sql.Date sql_date=null;
			try(PreparedStatement insert=connect.prepareStatement(query)) {
				
				insert.setString(1,emp.getKin());
				insert.setString(2,emp.getName());
				insert.setString(3,emp.getEmail());
				insert.setLong(4,emp.getContact());
				insert.setString(5, emp.getCity());
				sql_date=new java.sql.Date(emp.getDoj().getTime());
				insert.setDate(6,sql_date);
				insert.setInt(7,emp.getRole().getId());
				insert.setInt(8,emp.getSub_dept().getSubDept().getId());
				insert.setInt(9,emp.getDept().getId());
				if(opt==1){
					insert.setString(10,emp.getKin());
				}
				insert.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return false;
			}	
		
		return true;
	} 
	
	@Override
	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
		boolean isQueryExecuted=false;
		String insert_query="INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?)";
		isQueryExecuted=createStatement(insert_query,emp,0);
		return isQueryExecuted;
	}

	@Override
	public boolean modifyEmployee(Employee emp) {
		// TODO Auto-generated method stub
		boolean isQueryExecuted=false;
		String modify_query="UPDATE EMPLOYEE SET KIN_ID=?,NAME=?,EMAIL=?,CONTACT=?,CITY=?,DATEOFJOINING=?,ROLE_ID=?,SUBDEPARTMENT_ID=?,DEPARTMENT_ID=? where KIN_ID=?";
		isQueryExecuted=createStatement(modify_query,emp,1);
		return isQueryExecuted;
	}

	@Override
	public boolean deleteEmployee(String kin) {
		// TODO Auto-generated method stub
		String query="DELETE FROM EMPLOYEE WHERE KIN_ID=?";
		int count;
		try(PreparedStatement del_stmt=connect.prepareStatement(query)) {
			del_stmt.setString(1,kin);
			count=del_stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(count!=0){
			return true;	
		}
		else{
			return false;
		}
	}

	private Employee createEmployee(ResultSet emp_ResultSet) throws SQLException{
		while(emp_ResultSet.next()){
			
			Employee temp_emp=new Employee();
			Role temp_role=new Role();
			SubDept temp_sdept=new SubDept();
			Department temp_dept=new Department();
			SubDepartment temp_subdept=new SubDepartment();
			
			temp_role.setId(emp_ResultSet.getInt("Role_id"));
			temp_role.setR_name(emp_ResultSet.getString("Role_name"));
			
			temp_dept.setDept_name(emp_ResultSet.getString("Department_name"));
			temp_dept.setId(emp_ResultSet.getInt("Department_id"));
			
			temp_sdept.setSubdept_name(emp_ResultSet.getString("SubDepartment_name"));
			temp_sdept.setId(emp_ResultSet.getInt("SubDepartment_id"));
			
			temp_subdept.setDept(temp_dept);
			temp_subdept.setSubDept(temp_sdept);
			
			temp_emp.setKin(emp_ResultSet.getString("KIN_ID"));
			temp_emp.setName(emp_ResultSet.getString("Name"));
			temp_emp.setEmail(emp_ResultSet.getString("Email"));
			temp_emp.setContact(emp_ResultSet.getLong("Contact"));
			temp_emp.setCity(emp_ResultSet.getString("City"));
			
			temp_emp.setRole(temp_role);
			temp_emp.setDept(temp_dept);
			temp_emp.setSub_dept(temp_subdept);

			java.util.Date date_temp=new Date(emp_ResultSet.getDate("DateOfJoining").getTime());
			temp_emp.setDoj(date_temp);
			return temp_emp;
		}
		return null;
	}
	@Override
	public Collection<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		List<Employee> emp_list=new ArrayList<Employee>();
		ResultSet display_rs=null;
		String query="SELECT EMPLOYEE.*,Role_name,Department_name,SubDepartment_name FROM EMPLOYEE,ROLE,DEPARTMENT,SUBDEPARTMENT WHERE Employee.Role_id=Role.Role_id AND Employee.Department_id=department.Department_id AND Employee.SubDepartment_id=SubDepartment.SubDepartment_id";
		try(Statement display_stmt=connect.createStatement()) {
			display_rs=display_stmt.executeQuery(query);	
			Employee temp_emp;
			temp_emp=createEmployee(display_rs);
			while(temp_emp!=null){
				emp_list.add(temp_emp);
				temp_emp=createEmployee(display_rs);
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(display_rs!=null){
				try {
					display_rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return emp_list;
	}

	@Override
	public Employee searchEmployeeByKin(String kin) {
		// TODO Auto-generated method stub
		String query="SELECT EMPLOYEE.*,Role_name,Department_name,SubDepartment_name FROM EMPLOYEE,ROLE,DEPARTMENT,SUBDEPARTMENT WHERE KIN_ID=? AND Employee.Role_id=Role.Role_id AND Employee.Department_id=department.Department_id AND Employee.SubDepartment_id=SubDepartment.SubDepartment_id";
		ResultSet search_rs=null;
		Employee temp_emp=null;
		try (PreparedStatement search_stmt=connect.prepareStatement(query)){
			search_stmt.setString(1,kin);
			search_rs=search_stmt.executeQuery();
			temp_emp=createEmployee(search_rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(search_rs!=null){
					search_rs.close();	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temp_emp;
	}

	@Override
	public List<Employee> searchEmployeeByAnyValue(String value) {
		// TODO Auto-generated method stub
		String query="SELECT EMPLOYEE.*,Role_name,Department_name,SubDepartment_name FROM EMPLOYEE,ROLE,DEPARTMENT,SUBDEPARTMENT WHERE(KIN_ID=? OR NAME=? OR EMAIL=? OR CONTACT=? OR CITY=? OR DATEOFJOINING=? OR ROLE.ROLE_NAME=? OR DEPARTMENT.DEPARTMENT_NAME=? OR SUBDEPARTMENT.SUBDEPARTMENT_NAME=?)AND(Employee.Role_id=Role.Role_id AND Employee.Department_id=department.Department_id AND Employee.SubDepartment_id=SubDepartment.SubDepartment_id) ";
		Employee temp_emp;
		ResultSet search_rs=null;
		List<Employee> emp_list=new ArrayList<Employee>();
		try(PreparedStatement search_any=connect.prepareStatement(query)){
			
			for(int i=1;i<=9;i++){
				search_any.setString(i,value);
			}
			search_rs=search_any.executeQuery();
			temp_emp=createEmployee(search_rs);
			while(temp_emp!=null){
				emp_list.add(temp_emp);
				temp_emp=createEmployee(search_rs);
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(search_rs!=null){
				try {
					search_rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(emp_list.size()!=0){
			return emp_list;	
		}
		else{
			return null;
		}
		
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		List<Role> role_list=new ArrayList<Role>();
		ResultSet role_rs=null;
		try(Statement get_role=connect.createStatement()) {
			
			
			String query="SELECT * FROM ROLE";
			role_rs=get_role.executeQuery(query);
			while(role_rs.next()){
				Role role_temp=new Role();
				role_temp.setId(role_rs.getInt("Role_id"));
				role_temp.setR_name(role_rs.getString("Role_name"));
				role_list.add(role_temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(role_rs!=null){
				try {
					role_rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return role_list;
	}

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		List<Department> dept_list=new ArrayList<Department>();
		ResultSet dept_rs=null;
		try(Statement get_dept=connect.createStatement()) {
			
			
			String query="SELECT * FROM DEPARTMENT";
			dept_rs=get_dept.executeQuery(query);
			while(dept_rs.next()){
				Department dept_temp=new Department();
				dept_temp.setId(dept_rs.getInt("Department_id"));
				dept_temp.setDept_name(dept_rs.getString("Department_name"));
				dept_list.add(dept_temp);
			}
			dept_rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dept_rs!=null){
				try {
					dept_rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		}
		return dept_list;
	}

	@Override
	public List<SubDepartment> getSubDepartment(Department department) {
		// TODO Auto-generated method stub
		List<SubDepartment> subdept_list=new ArrayList<SubDepartment>();
		String query="SELECT SubDepartment_id,SubDepartment_name FROM SUBDEPARTMENT WHERE Department_id=?";
		ResultSet subdept_rs=null;
		try(PreparedStatement get_subdept=connect.prepareStatement(query)) {
			get_subdept.setInt(1,department.getId());
			subdept_rs=get_subdept.executeQuery();
			while(subdept_rs.next()){
				SubDepartment subdept_temp=new SubDepartment();
				SubDept subd_temp=new SubDept();
				subd_temp.setId(subdept_rs.getInt("SubDepartment_id"));
				subd_temp.setSubdept_name(subdept_rs.getString("SubDepartment_name"));
				subdept_temp.setSubDept(subd_temp);
				subdept_temp.setDept(department);
				subdept_list.add(subdept_temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(subdept_rs!=null){
				try {
					subdept_rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		return subdept_list;
	}
	
	protected void finalize(){
		EMSConnector.disconnect(connect);
	}

}
