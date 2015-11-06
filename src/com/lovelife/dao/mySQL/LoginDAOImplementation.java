package com.lovelife.dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javlib.encryption.Encryption;
import com.javlib.system.Sys;
import com.lovelife.dao.LoginDAO;
import com.lovelife.model.UserDTO;
import com.lovelife.resource.Constant;
import com.lovelife.service.OpenConnectionService;

public class LoginDAOImplementation implements LoginDAO {
	@Override
	public UserDTO authenticateUser(UserDTO user) {
		Connection conn = null;
		UserDTO authenticatedUser = new UserDTO();
		OpenConnectionService openConnectionService = new OpenConnectionService();
		conn = openConnectionService.openConnection();
		if (conn == null) {
			Sys.logError(Constant.DB_ERROR);
		}
		Sys.log(Constant.AUTHENTICATING_MEMBER);
		String sql = "SELECT * FROM LoveLife.User WHERE username = ? AND password = ?;";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, Encryption.hashSHA1(user.getPassword()));
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				authenticatedUser.setUserID(result.getInt("userID"));
				authenticatedUser.setUsername(result.getString("username"));
				authenticatedUser.setPassword(result.getString("password"));
				authenticatedUser.setFullName(result.getString("fullname"));
				authenticatedUser.setNickName(result.getString("nickname"));
				authenticatedUser.setDateOfBirth(result.getString("dob"));
				authenticatedUser.setContactNo(result.getString("contactno"));
				authenticatedUser.setEmail(result.getString("email"));
				Sys.logSuccess(Constant.AUTHENTICATED);
			}
			else{
				Sys.log(Constant.NO_MEMBER);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return authenticatedUser;
	}
}
