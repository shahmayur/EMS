package com.flp4.ems.domain;

public class SubDepartment {
	
	public SubDept getSubDept() {
		return subDept;
	}
	public void setSubDept(SubDept subDept) {
		this.subDept = subDept;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	private SubDept subDept;
	private Department dept;

}
