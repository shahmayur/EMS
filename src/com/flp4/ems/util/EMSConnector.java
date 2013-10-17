package com.flp4.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EMSConnector {
		
	private EMSConnector(){
		
	}
	
	static public Connection createConnection(String u_url,String u,String pwd){
		String url=u_url;
		String user=u;
		String password=pwd;
		Connection connector=null;;
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				connector=DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
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
		return connector;
	}
	
	static public void disconnect(Connection connect){
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
