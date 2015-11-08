package com.lovelife.dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javlib.encryption.Encryption;
import com.javlib.system.Sys;
import com.lovelife.dao.RegisterDAO;
import com.lovelife.model.UserDTO;
import com.lovelife.resource.Constant;
import com.lovelife.service.OpenConnectionService;

public class RegisterDAOImplementation implements RegisterDAO{
	public boolean registerAccount(UserDTO newUser){
		Connection conn = null;
		boolean result = false;
		OpenConnectionService openConnectionService = new OpenConnectionService();
		conn = openConnectionService.openConnection();
		if(conn != null){
			Sys.log(Constant.REGISTERING_MEMBER);
			String sql = "INSERT INTO LoveLife (username,password,fullname,nickname,dob,contactno,email,imgUrl) VALUES (?,?,?,?,?,?,?,?);";
			try {
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, newUser.getUsername());
				preparedStatement.setString(2, Encryption.hashSHA1(newUser.getPassword()));
				preparedStatement.setString(3, newUser.getFullName());
				preparedStatement.setString(4, newUser.getNickName());
				preparedStatement.setString(5, newUser.getDateOfBirth());
				preparedStatement.setString(6, newUser.getContactNo());
				preparedStatement.setString(7, newUser.getEmail());
				preparedStatement.setString(8, newUser.getImgUrl());
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()){
					Sys.logSuccess(Constant.REGISTERED_MEMBER);
					result = true;
				}
				else{
					Sys.logError(Constant.REGISTERING_ERROR);
					result =  false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					conn.close();
					Sys.log(Constant.DB_CLOSE);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
		
	}
}
