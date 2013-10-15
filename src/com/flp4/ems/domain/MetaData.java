package com.flp4.ems.domain;

import java.util.HashMap;
import java.util.Map;

public class MetaData {
	 public static Map<Integer,String> RoleMap=new HashMap<Integer,String>();
	 public static Map<Integer,String> DeptMap=new HashMap<Integer,String>();
	 public static Map<SubDept,Department> SubDeptMap=new HashMap<SubDept,Department>();
	 
	 /*public static Map<Integer,String> getRoles(){
		 if(!isRoleMapGenerated){
			 setRoles();
			 isRoleMapGenerated=true;
		 }
		 return RoleMap;
		 
	 }*/

	/*private static void setRoles() {
		// TODO Auto-generated method stub
		
	}*/
	
	static{
		RoleMap.put(1,"Software Developer");
		RoleMap.put(2,"Associate Software Consultant");
		RoleMap.put(3,"Senior Software Consultant");
		DeptMap.put(1,"Human Resources");
		DeptMap.put(2,"Accounts");
		DeptMap.put(3,"Marketing");
		DeptMap.put(4,"Development");
		
		SubDept[] subdept=new SubDept[4];
		
		Department[] dept=new Department[4];
		
		dept[0]=new Department();
		dept[0].setId(1);
		dept[0].setDept_name(DeptMap.get(1));
		
		dept[1]=new Department();
		dept[1].setId(2);
		dept[1].setDept_name(DeptMap.get(2));
		
		dept[2]=new Department();
		dept[2].setId(3);
		dept[2].setDept_name(DeptMap.get(3));
		
		dept[3]=new Department();
		dept[3].setId(4);
		dept[3].setDept_name(DeptMap.get(4));
		
		subdept[0]=new SubDept();
		subdept[0].setId(1);
		subdept[0].setSubdept_name("People Processes");
		SubDeptMap.put(subdept[0],dept[0]);

		subdept[1]=new SubDept();
		subdept[1].setId(2);
		subdept[1].setSubdept_name("Payroll");
		SubDeptMap.put(subdept[1],dept[1]);
		
		subdept[2]=new SubDept();
		subdept[2].setId(3);
		subdept[2].setSubdept_name("Sales");
		SubDeptMap.put(subdept[2],dept[2]);
		
		subdept[3]=new SubDept();
		subdept[3].setId(4);
		subdept[3].setSubdept_name("Java");
		SubDeptMap.put(subdept[3],dept[3]);
		
	}
}
