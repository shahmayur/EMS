package com.flp4.ems.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Employee;
import com.flp4.ems.domain.Role;
import com.flp4.ems.domain.SubDepartment;
import com.flp4.ems.domain.SubDept;
import com.flp4.ems.util.EMSConstants;
import com.flp4.ems.domain.MetaData;;

public class EmployeeDAOImplForCollection implements IEmployee{
	
	private List<Employee> emp_rec=new ArrayList<Employee>();
	
	public static boolean isRoleGenerated=false;
	public static boolean isDepartmentGenerated=false;
	public static boolean isSubDeptGenerated=false;
	
	@Override
	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		if(emp_rec.size()==0){
			emp_rec.add(emp);
			return true;
		}
		else{
			if(!checkDuplicate(emp)){
				emp_rec.add(emp);
				return true;
			}
		}
		return false;
	}

	private boolean checkDuplicate(Employee emp) {
		// TODO Auto-generated method stub
		Iterator<Employee> itr_emp_rec=emp_rec.iterator();
		Employee emp_temp;
		while(itr_emp_rec.hasNext()){
			emp_temp=itr_emp_rec.next();
			if((emp_temp.equals(emp))&&(emp_temp.hashCode()==emp.hashCode())){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean modifyEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean deleteEmployee(String kin) {
		// TODO Auto-generated method stub
		Employee s_emp;
		s_emp=searchEmployeeByKin(kin);
		if(s_emp!=null){
			emp_rec.remove(s_emp);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return emp_rec;
	}

	@Override
	public Employee searchEmployeeByKin(String kin) {
		// TODO Auto-generated method stub
		Iterator<Employee> itr_emp_rec=emp_rec.iterator();
		Employee temp;
		while(itr_emp_rec.hasNext()){
			temp=itr_emp_rec.next();
			if(temp.getKin().equalsIgnoreCase(kin)){
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<Employee> searchEmployeeByAnyValue(String value) {
		// TODO Auto-generated method stub
		if(emp_rec.size()!=0){
			Employee temp_emp;
			String temp_date="";
			List<Employee> emp_list=new ArrayList<Employee>();
			Iterator<Employee> emp_rec_itr=emp_rec.iterator();
			while(emp_rec_itr.hasNext()){
				temp_emp=emp_rec_itr.next();
				if(temp_emp.getKin().equalsIgnoreCase(value)){
					emp_list.add(temp_emp);
					continue;
				}
				else if(temp_emp.getContact().toString().equalsIgnoreCase(value)){
					emp_list.add(temp_emp);
					continue;					
				}
				else if(temp_emp.getEmail().equalsIgnoreCase(value)){
					emp_list.add(temp_emp);
					continue;
				}
				else if(temp_emp.getRole().getR_name().equalsIgnoreCase(value)){
					emp_list.add(temp_emp);
					continue;
				}
				else if(temp_emp.getDept().getDept_name().equalsIgnoreCase(value)){
					emp_list.add(temp_emp);
					continue;
				}
				else if(temp_emp.getSub_dept().getSubDept().getSubdept_name().equalsIgnoreCase(value)){
					emp_list.add(temp_emp);
					continue;
				}
				else if(temp_emp.getName().equalsIgnoreCase(value)){
					emp_list.add(temp_emp);
					continue;
				}
				else{
					temp_date=temp_date.concat(temp_emp.getDoj().getDate()+"-"+temp_emp.getDoj().getMonth()+"-"+temp_emp.getDoj().getYear());
					if(temp_date.equalsIgnoreCase(value)){
						emp_list.add(temp_emp);
						continue;
					}
				}
			}
			return emp_list;
		}
		return null;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		List<Role> role_list=new ArrayList<Role>();
		//if(!isRoleGenerated){
			Map<Integer,String> roles_map;//new HashMap<Integer,String>();
			roles_map=MetaData.RoleMap;
			Set<Integer> id_list=new LinkedHashSet<Integer>();
			int id;
	//		Set<String> role_name_list=new LinkedHashSet<String>();
			id_list=roles_map.keySet();
//			role_name_list=roles_map.values();
			Iterator<Integer> id_list_itr=id_list.iterator();
//			Iterator<String> role_name_itr=role_name_list.iterator();
			for(int i=0;i<roles_map.size();i++){
				Role temp=new Role();
				id=id_list_itr.next();
				temp.setId(id);
				temp.setR_name(roles_map.get(id));
				role_list.add(temp);
		//	}	
			isRoleGenerated=true;
		}
		
		return role_list;
	}

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		 List<Department> dept_list=new ArrayList<Department>();
		//if(!isDepartmentGenerated){
			Map<Integer,String> dept_map=new HashMap<Integer,String>();
			dept_map=MetaData.DeptMap;
			Set<Integer> id_list=new LinkedHashSet<Integer>();
			int id;
			id_list=dept_map.keySet();
			Iterator<Integer> id_list_itr=id_list.iterator();
			for(int i=0;i<dept_map.size();i++){
				Department temp=new Department();
				id=id_list_itr.next();
				temp.setId(id);
				temp.setDept_name(dept_map.get(id));
				dept_list.add(temp);
			}
			isDepartmentGenerated=true;
		//}
		return dept_list;
	}

	@Override
	public List<SubDepartment> getSubDepartment(Department department) {
		List<SubDepartment> sub_dept_list=new ArrayList<SubDepartment>();
		Map<SubDept,Department> sub_dept_map=new HashMap<SubDept,Department>();
		sub_dept_map=MetaData.SubDeptMap;
		Set<SubDept> subdept_key_list=new LinkedHashSet<SubDept>();
		subdept_key_list=sub_dept_map.keySet();
		Iterator<SubDept> sub_dept_list_itr=subdept_key_list.iterator();
		
		while(sub_dept_list_itr.hasNext()){
			Department temp=new Department();
			SubDept sub_temp=new SubDept(); 
			sub_temp=sub_dept_list_itr.next();
			temp=sub_dept_map.get(sub_temp);
			if(temp.getDept_name().equalsIgnoreCase(department.getDept_name())){
				SubDepartment subdept_temp=new SubDepartment();
				subdept_temp.setSubDept(sub_temp);
				subdept_temp.setDept(temp);
				sub_dept_list.add(subdept_temp);
			}
		}
		
		return sub_dept_list;
	}

}
