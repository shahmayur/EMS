package com.flp4.ems.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Create  extends Action{

	@Override
	public String doAction(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		System.out.println("create called");
		return "create.jsp";
	}

}
