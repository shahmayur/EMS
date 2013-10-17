package com.flp4.ems.domain;

import java.util.Date;

import com.flp4.ems.domain.Employee;;

public class Employee {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public SubDepartment getSub_dept() {
		return sub_dept;
	}
	public void setSub_dept(SubDepartment sub_dept) {
		this.sub_dept = sub_dept;
	}
	public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	private String name;
	private String kin;
	private String email;
	private String city;
	private Long contact;
	private Date doj;
	private Role role;
	private Department dept;
	private SubDepartment sub_dept;
	
	public boolean equals(Object obj){
		Employee emp;
		emp=(Employee)obj;
		/*System.out.println(this.kin_id);
		System.out.println(emp.kin_id);*/
		if(this.kin.equalsIgnoreCase(emp.getKin())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int hashCode(){
		
		return Integer.parseInt(this.kin.substring(0,4));
	}
	
	public String toString(){
		String temp ="KIN Id:";
		temp=temp.concat(this.getKin()+"\nName: "+this.getName()+"\nEmail ID: "+this.getEmail()+"\nContact No.: "+this.getContact()+"\nCity:"+this.getCity()+"\nDate Of Joining: "+this.getDoj().getDate()+"-"+(this.getDoj().getMonth()+1)+"-"+(this.getDoj().getYear()+1900)+"\nRole:"+(this.getRole().getR_name())+"\nSub-Department:"+(this.getSub_dept().getSubDept().getSubdept_name())+"\nDepartment:"+(this.getDept().getDept_name()));
		//temp=temp.concat(this.getKin()+"\nName: "+this.getName()+"\nDate of joining: "+this.doj+"\nContact No: "+this.getContact()+"\nEmail ID: "+this.getEmail());
		return temp;
	}
}
