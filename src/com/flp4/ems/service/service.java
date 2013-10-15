package com.flp4.ems.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp4.ems.domain.Department;
import com.flp4.ems.domain.Role;
import com.flp4.ems.domain.SubDepartment;
import com.flp4.ems.util.Action;
import com.flp4.ems.util.AfterAction;
import com.flp4.ems.util.EMSConstants;
//import com.flp4.ems.util.RegexValidator;

/**
 * Servlet implementation class service
 */
public class service extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public service() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	ServletContext context=this.getServletContext();
    	Properties property=null; 
    	property=(Properties) context.getAttribute("properties");
    	try {
			Action act =(Action) Class.forName(property.getProperty(request.getParameter("action"))).newInstance();
			String resource = act.doAction(request, response);
			RequestDispatcher dispatcher=request.getRequestDispatcher(resource);
			// forward to resource
			dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	Properties property=null;
    	ServletContext context=this.getServletContext();
    	property=(Properties) context.getAttribute("properties");
		try {
			AfterAction aftAct=(AfterAction)Class.forName(property.getProperty(request.getParameter("action"))).newInstance();
			String resource=aftAct.doAction(request, response);
			RequestDispatcher dispatcher=request.getRequestDispatcher(resource);
			dispatcher.forward(request,response);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
