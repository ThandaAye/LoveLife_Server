package com.lovelife.dao.mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.javlib.system.Sys;
import com.lovelife.dao.OpenConnectionDAO;
import com.lovelife.resource.Constant;

public class OpenConnectionDAOImplementation implements OpenConnectionDAO{
	public Connection openConnection(){
		 Sys.log(Constant.DB_CONNECTING);
		 final String dbURL = "jdbc:mysql://127.0.0.1:3306/LoveLife";
		 final String dbUsername = "lovelife";
		 final String dbPassword = "Thetminko1990";
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
			 Sys.logSuccess(Constant.DB_CONNECTED);
			 return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
