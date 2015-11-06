package com.lovelife.dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.javlib.encryption.Encryption;
import com.javlib.math.MathLib;
import com.javlib.system.Sys;
import com.lovelife.dao.RetrieveMemberDAO;
import com.lovelife.model.UserDTO;
import com.lovelife.resource.Constant;
import com.lovelife.service.OpenConnectionService;

public class RetrieveMemberDAOImplementation implements RetrieveMemberDAO {
	@Override
	public List<UserDTO> getAllMembers() {
		Connection conn = null;
		ResultSet result = null;
		List<UserDTO> membersList = new ArrayList<UserDTO>();
		OpenConnectionService openConnectionService = new OpenConnectionService();
		conn = openConnectionService.openConnection();
		if (conn == null) {
			Sys.logError(Constant.DB_ERROR);
		}
		String sql = "SELECT * FROM LoveLife.User;";
		Sys.log(Constant.RETRIEVING_ALL_MEMBERS);
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			result = statement.executeQuery();
			while (result.next()) {
				UserDTO user = new UserDTO();
				user.setUserID(result.getInt("userID"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setFullName(result.getString("fullname"));
				user.setNickName(result.getString("nickname"));
				user.setDateOfBirth(result.getString("dob"));
				user.setContactNo(result.getString("contactno"));
				user.setEmail(result.getString("email"));
				membersList.add(user);
			}
		} catch (SQLException e) {
			Sys.logError(Constant.RETRIEVED_ERROR);
			e.printStackTrace();
		} finally {
			try {
				result.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Sys.logSuccess(Constant.RETRIEVED_ALL_MEMBERS);
		return membersList;
	}

	@Override
	public UserDTO getMember(UserDTO user) {
		Connection conn = null;
		ResultSet result = null;
		UserDTO retrievedUser = new UserDTO();
		OpenConnectionService openConnectionService = new OpenConnectionService();
		conn = openConnectionService.openConnection();
		if (conn == null) {
			Sys.logError(Constant.DB_ERROR);
		}
		Sys.log(Constant.RETRIEVING_MEMBER);
		String sql = "SELECT * FROM LoveLife.User WHERE username = ? AND password = ?;";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, Encryption.hashSHA1(user.getPassword()));
			result = statement.executeQuery();
			while (result.next()) {
				retrievedUser.setUserID(result.getInt("userID"));
				retrievedUser.setUsername(result.getString("username"));
				retrievedUser.setPassword(result.getString("password"));
				retrievedUser.setFullName(result.getString("fullname"));
				retrievedUser.setNickName(result.getString("nickname"));
				retrievedUser.setDateOfBirth(result.getString("dob"));
				retrievedUser.setContactNo(result.getString("contactno"));
				retrievedUser.setEmail(result.getString("email"));
				Sys.logSuccess(Constant.RETRIEVED_MEMBER);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				conn.close();
			} catch (SQLException e) {
				Sys.logError(Constant.RETRIEVED_ERROR);
				e.printStackTrace();
			}
		}
		return retrievedUser;
	}

	@Override
	public int resetPassword(String email) {
		Connection conn = null;
		ResultSet resultSet = null;
		int pin = 0;
		int insertStatus = 0;
		OpenConnectionService openConnectionService = new OpenConnectionService();
		conn = openConnectionService.openConnection();
		if (conn == null) {
			Sys.logError(Constant.DB_ERROR);
		}
		Sys.log(Constant.RETRIEVING_MEMBER);
		String sql = "SELECT * FROM LoveLife.User WHERE email = ?;";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pin = checkExistingPin(resultSet.getInt("userID"), conn);
				if (pin == 0) {
					insertStatus = insertResetPinToDB(resultSet.getInt("userID"), conn);
					if (insertStatus != 0) {
						pin = checkExistingPin(resultSet.getInt("userID"), conn);
						Sys.logSuccess(Constant.PIN_SET);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pin;
	}

	private int checkExistingPin(int userid, Connection conn) {
		Sys.log(Constant.CHECKING_PIN);
		int pin = 0;
		String sql = "SELECT * FROM LoveLife.Temp WHERE userID = ? AND available = 'true';";
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, userid);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				pin = resultSet.getInt("tempData");
			}
		} catch (SQLException ex) {
			Sys.logError(Constant.DB_ERROR);
			ex.printStackTrace();
		}
		return pin;
	}

	private int insertResetPinToDB(int userID, Connection conn) {
		int pin = 0;
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		boolean availability = true;
		int returnResult = 0;
		if (conn != null) {
			String sql = "INSERT INTO Temp VALUES(?,?,?,?);";
			try {
				pin = MathLib.generateRdm(5);
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, userID);
				preparedStatement.setString(2, String.valueOf(pin));
				preparedStatement.setString(3, today.toString());
				preparedStatement.setBoolean(4, availability);
				returnResult = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			Sys.logError(Constant.DB_ERROR);
			return returnResult;
		}
		return returnResult;
	}

	@Override
	public int changePassword(String email, String oldPassword, String newPassword) {
		int result = 0;
		Connection conn = null;
		OpenConnectionService service = new OpenConnectionService();
		conn = service.openConnection();
		Sys.log(Constant.DB_CONNECTING);
		if(conn!=null){
			Sys.log(Constant.DB_CONNECTED);
			String sql = "UPDATE LoveLife.User SET password = ? WHERE email = ? AND password=?;";
			try{
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, Encryption.hashSHA1(newPassword));
				preparedStatement.setString(2, Encryption.hashSHA1(oldPassword));
				result = preparedStatement.executeUpdate();
				
				//NOT COMPLETE YET
			}
			catch(SQLException ex){
				Sys.logError(Constant.DB_ERROR);
				ex.printStackTrace();
			}
		}else{
			Sys.logError(Constant.DB_ERROR);
		}
		return result;
	}
}
