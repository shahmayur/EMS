package com.flp4.ems.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EMSListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Properties property=new Properties();
		property.setProperty("create","com.flp4.ems.util.Create");
		property.setProperty("display","com.flp4.ems.util.Display");
		property.setProperty("modify","com.flp4.ems.util.Modify");
		property.setProperty("delete","com.flp4.ems.util.Delete");
		property.setProperty("search","com.flp4.ems.util.Search");
		property.setProperty("searchany","com.flp4.ems.util.SearchAny");
		
		property.setProperty("AfterCreate","com.flp4.ems.util.AfterActionCreate");
		property.setProperty("AfterSearch","com.flp4.ems.util.AfterActionSearch");
		property.setProperty("AfterSearchAny","com.flp4.ems.util.AfterActionSearchAny");
		property.setProperty("AfterModify","com.flp4.ems.util.AfterActionModify");
		property.setProperty("AfterDelete","com.flp4.ems.util.AfterActionDelete");

		property.setProperty("AfterUpdate","com.flp4.ems.util.AfterActionUpdate");		
		OutputStream os=null;
		try {
			os = new FileOutputStream("D:/property.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			property.storeToXML(os,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext context=arg0.getServletContext();
		context.setAttribute("properties",property);
	}

}
