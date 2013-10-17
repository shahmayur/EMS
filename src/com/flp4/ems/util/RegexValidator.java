package com.flp4.ems.util;

import java.util.Date;

public class RegexValidator {
	private RegexValidator(){}
	
	public static boolean isValidAge(Byte age){
		String temp;
		temp=age.toString();
		if(temp.matches("[0-9]+")){
		return true;
		}
		else{
			return false;
		}
	}
	public static boolean isValidName(String name){
		if(name.matches("[[A-Z][a-z ]*[A-Z]]*")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isValidDOJ(Date doj){
		//if(dob.matches("[0-9]{2}[-][0-9]{2}-[0-9]{4}")){
		/*if(dob.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|[1][0-2])/((19)[5-9][0-9]|(20)[0-9]{2})")){
			return true;	
		}
		
		else
			return false;*/
		String temp="";
		temp=temp.concat(doj.getDate()+"-"+(doj.getMonth()+1)+"-"+(doj.getYear()+1900));
		if(temp.matches("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|[1][0-2])-((19)[5-9][0-9]|(20)[0-9]{2})")){
			return true;
		}
		else{
			return false;	
		}
		
	}
	
	public static boolean isValidCity(String city){
		if(city.matches("[A-z ]*")){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	public static boolean isValidContactNo(String contact){
		if(contact.matches("[0-9]{10}")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isValidEmail(String email){
		if(email.matches("[A-z_$0-9][A-z_$0-9.]*[@][A-z0-9]+[.][A-z]*")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isValidEID(String empid){
		if(empid.matches("[0-9]{5}[_][A-Z]{2}")){
			return true;
		}
		else{
			return false;
		}
			
	}
}
