package com.flp4.ems.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Employee;
import com.flp4.ems.domain.Role;
import com.flp4.ems.domain.SubDepartment;
import com.flp4.ems.service.EmployeeServiceImpl;
import com.flp4.ems.service.IEmployeeService;
import com.flp4.ems.util.RegexValidator;
import com.flp4.ems.util.EMSConstants;

public class UserInteraction {

	/**
	 * @param args
	 */
	
	private IEmployeeService emp=new EmployeeServiceImpl();
	private Scanner input=new Scanner(System.in);
	public static void main(String[] args) {
		UserInteraction ui=new UserInteraction();
		ui.menu();
		
	}

	private void menu() {
		// TODO Auto-generated method stub
		//Scanner input=new Scanner(System.in);
		int ch;
		String opt;
		do{
			System.out.println("1.Add Employee");
			System.out.println("2.Modify Employee Details");
			System.out.println("3.Delete Employee");
			System.out.println("4.Search Employee Record");
			System.out.println("5.Display Record(s)");
			System.out.print("Enter your choice:");
			ch=input.nextInt();
			switch(ch){
				case 1:
						System.out.println();
						add();
						break;
				case 2:
						System.out.println();
						modify();
						break;
				case 3:
						System.out.println();
						del();
						break;
				case 4:
						System.out.println();
						search();
						break;
				case 5:
						System.out.println();
						display();
						break;
			}
			System.out.println();
			System.out.print("Do you wish to continue?(y/n)");
			opt=input.next();
			System.out.println();
		}while(opt.equalsIgnoreCase("y"));
		input.close();
	}

	private void display() {
		// TODO Auto-generated method stub
		ArrayList<Employee> emps=(ArrayList<Employee>) emp.getAllEmployee();
		if(emps.size()!=0){
			int i=1;
			for(Employee e:emps){
				System.out.println("Record No."+(i++));
				System.out.println(e);
				System.out.println();
			}	
		}
		else{
			System.out.println("No records found in the database...!");
		}
		
	}

	private void search() {
		// TODO Auto-generated method stub
		int ch;
		System.out.println("1.Search by KIN ID");
		System.out.println("2.Search by any value");
		System.out.print("Enter your choice:");
		ch=input.nextInt();
		System.out.println();
		boolean isValid=false;
		String temp;
		Employee s_emp;
		temp=input.nextLine();
		switch(ch){
			case 1:
					do{
						System.out.print("Enter KIN ID:");
						temp=input.nextLine();
						isValid=RegexValidator.isValidEID(temp);
						/*if(isValid){
							curr_emp.setKin_id(temp);
						}*/	
					}while(!isValid);
					s_emp=emp.searchEmployeeByKin(temp);
					if(s_emp!=null){
						System.out.println(s_emp);
					}
					else{
						System.out.println("Record doesn't exist!");
					}
					break;
			case 2:
					searchByAny();
					break;
			default:
					System.out.println("Invalid Choice!");
					break;
		}
	}

	private void searchByAny() {
		// TODO Auto-generated method stub
		String value;
		System.out.println("Enter the value :");
		value=input.next();
		List<Employee> search_list;
		search_list=emp.searchEmployeeByAnyValue(value);
		if(search_list!=null){
			for(int i=0;i<search_list.size();i++){
				System.out.println(search_list.get(i));
				System.out.println();
			}
			
		}
		else{
			System.out.println("No records found in the database...!");
		}
	}

	private void del() {
		// TODO Auto-generated method stub
		String temp;
		boolean isValid=false;
		do{
			System.out.print("Enter KIN ID of the record to be deleted: ");
			temp=input.next();
			isValid=RegexValidator.isValidEID(temp);
		}while(!isValid);
		if(emp.deleteEmployee(temp)){
			System.out.println("Record deleted successfully!");
		}
		else{
			System.out.println("No existing record found that matches the given KIN ID!");
		}
		
		
	}

	private void modify() {
		// TODO Auto-generated method stub
		boolean isValid=false;
		//boolean isModified=false;
		String temp;
		Employee s_emp;
		String ch;
		boolean isDepartmentModified=false;
		int choice=0;
		temp=input.nextLine();
		do{
			System.out.print("Enter KIN ID:");
			temp=input.nextLine();
			isValid=RegexValidator.isValidEID(temp);
			/*if(isValid){
				curr_emp.setKin_id(temp);
			}*/	
		}while(!isValid);
		s_emp=emp.searchEmployeeByKin(temp);
		if(s_emp!=null){
			System.out.print("Do you want to change the name?(y/n)");
			ch=input.nextLine();
			if(ch.equalsIgnoreCase("y")){
				do{
					System.out.print("Enter name:");
					temp=input.nextLine();
					isValid=RegexValidator.isValidName(temp);
					/*if(isValid){
					curr_emp.setName(temp);
					}*/
				}while(!isValid);
				s_emp.setName(temp);
			}
			System.out.print("Do you want to change contact number?(y/n)");
			ch=input.nextLine();
			if(ch.equalsIgnoreCase("y")){
				do{
					System.out.print("Enter contact number: ");
					temp=input.nextLine();
					isValid=RegexValidator.isValidContactNo(temp);
					/*if(isValid){
						curr_emp.setContact_no(temp);
					}*/
				}while(!isValid);
				s_emp.setContact(Long.parseLong(temp));
			}
			System.out.print("Do you want to change E-mail ID?(y/n) ");
			ch=input.nextLine();
			if(ch.equalsIgnoreCase("y")){
				do{
					System.out.print("Enter E-mail ID:");
					temp=input.nextLine();
					isValid=RegexValidator.isValidEmail(temp);
					/*if(isValid){
						curr_emp.setEmail_id(temp);
					}*/
				}while(!isValid);
				s_emp.setEmail(temp);
			}
			System.out.print("Do you want to change Role?(y/n)");
			ch=input.next();
			if(ch.equalsIgnoreCase("y")){
				
				System.out.println("Select a role...");
				List<Role> role_list=emp.getRoles();
				Iterator<Role> role_itr=role_list.iterator();
				
				while(role_itr.hasNext()){
					Role role_temp=role_itr.next();
					System.out.println(role_temp.getId()+"."+role_temp.getR_name());
				}
				System.out.print("Enter your choice:");
				choice=input.nextInt();
				s_emp.setRole(role_list.get(choice-1));
			}
			//input.nextLine();
			System.out.println();
			System.out.print("Do you want to change Department?(y/n)");
			ch=input.next();
			if(ch.equalsIgnoreCase("y")){
				isDepartmentModified=true;
				System.out.println("Select a department...");
				List<Department> dept_list=emp.getDepartment();
				Iterator<Department> dept_itr=dept_list.iterator();
				while(dept_itr.hasNext()){
					Department dept_temp=dept_itr.next();
					System.out.println(dept_temp.getId()+"."+dept_temp.getDept_name());
				}
				System.out.print("Enter your choice: ");
				choice=input.nextInt();
				s_emp.setDept(dept_list.get(choice-1));
			}
			
			System.out.print("Do you want to change Sub-Department?(y/n)");
			ch=input.next();
			if(ch.equalsIgnoreCase("y")){
				System.out.println("Select a sub-department...");
				List<Department> dept_list=emp.getDepartment();
				List<SubDepartment> subdept_list=new ArrayList<SubDepartment>();
				if(isDepartmentModified){
					subdept_list=emp.getSubDepartment(dept_list.get(choice-1));	
				}
				else{
					subdept_list=emp.getSubDepartment(s_emp.getDept());
				}
				Iterator<SubDepartment> sub_dept_itr=subdept_list.iterator();
				choice=1;
				while(sub_dept_itr.hasNext()){
					SubDepartment sub_dept_temp=sub_dept_itr.next();
					System.out.println(choice+"."+sub_dept_temp.getSubDept().getSubdept_name());
					choice++;
				}
				System.out.print("Enter your choice:");
				choice=input.nextInt();
				s_emp.setSub_dept(subdept_list.get(choice-1));
			}
			if(emp.modifyEmployee(s_emp)){
				System.out.println("Record modified successfully!");
			}
			else{
				System.out.println("Error!");
			}
			
		}
		else{
			System.out.println("Record doesn't exist!");
		}
	}

	private void add() {
		// TODO Auto-generated method stub
		Map<String,Object> emp_map=new HashMap<String,Object>();
		
		boolean isValid=false;
		String temp;
		Date d=new Date();
		int ch;
		//System.out.println();
		temp=input.nextLine();
		do{
			System.out.print("Enter your KIN ID:");
			temp=input.nextLine();
			isValid=RegexValidator.isValidEID(temp);
	
		}while(!isValid);
		emp_map.put(EMSConstants.kin,temp);
		
		System.out.println();
		do{
			System.out.print("Enter your name:");
			temp=input.nextLine();
			isValid=RegexValidator.isValidName(temp);
		
		}while(!isValid);
		emp_map.put(EMSConstants.name,temp);
		
		System.out.println();
		do{
			System.out.print("Enter your phone number: ");
			temp=input.nextLine();
			isValid=RegexValidator.isValidContactNo(temp);
	
		}while(!isValid);
		emp_map.put(EMSConstants.contact,temp);
		
		System.out.println();
		do{
			System.out.print("Enter your e-mail ID:");
			temp=input.nextLine();
			isValid=RegexValidator.isValidEmail(temp);
		
		}while(!isValid);
		emp_map.put(EMSConstants.email,temp);
		
		System.out.println();
		do{
			System.out.print("Enter your city:");
			temp=input.nextLine();
			isValid=RegexValidator.isValidCity(temp);
		}while(!isValid);
		emp_map.put(EMSConstants.city,temp);
		
		System.out.println();
		do{
			System.out.print("Enter your date of joining(dd-MM-yyyy):");
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			temp=input.nextLine();
			try {
				d=sdf.parse(temp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				isValid=false;
			}
			isValid=RegexValidator.isValidDOJ(d);

		}while(!isValid);
		emp_map.put(EMSConstants.doj,d);
		System.out.println();
		
		System.out.println("Select a role...");
		List<Role> role_list=emp.getRoles();
		Iterator<Role> role_itr=role_list.iterator();
		
		while(role_itr.hasNext()){
			Role role_temp=role_itr.next();
			System.out.println(role_temp.getId()+"."+role_temp.getR_name());
		}
		System.out.print("Enter your choice:");
		ch=input.nextInt();
		emp_map.put(EMSConstants.role,role_list.get(ch-1));
		
		System.out.println();
		
		System.out.println("Select a department...");
		List<Department> dept_list=emp.getDepartment();
		Iterator<Department> dept_itr=dept_list.iterator();
		while(dept_itr.hasNext()){
			Department dept_temp=dept_itr.next();
			System.out.println(dept_temp.getId()+"."+dept_temp.getDept_name());
		}
		System.out.print("Enter your choice: ");
		ch=input.nextInt();
		emp_map.put(EMSConstants.dept,dept_list.get(ch-1));
		
		System.out.println();
		System.out.println("Select a sub-department...");
		List<SubDepartment> sub_dept_list=emp.getSubDepartment(dept_list.get(ch-1));
		Iterator<SubDepartment> sub_dept_itr=sub_dept_list.iterator();
		ch=1;
		while(sub_dept_itr.hasNext()){
			SubDepartment sub_dept_temp=sub_dept_itr.next();
			System.out.println(ch+"."+sub_dept_temp.getSubDept().getSubdept_name());
			ch++;
		}
		System.out.print("Enter your choice:");
		ch=input.nextInt();
		emp_map.put(EMSConstants.sub_dept,sub_dept_list.get(ch-1));
		if(emp.addEmployee(emp_map)){
			System.out.println("Record added succesfully!");
		}
		else{
			System.out.println("Duplicate data entered!");
		}
		//input.close();
	}

}
