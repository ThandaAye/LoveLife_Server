package com.lovelife.service;

import com.lovelife.dao.DAOFactory;
import com.lovelife.dao.LoginDAO;
import com.lovelife.model.UserDTO;

public class LoginService {
	private LoginDAO loginDAO;

	public LoginService() {
		loginDAO = DAOFactory.loadInstance().getLoginDAO();
	}
	public UserDTO loginUser(UserDTO user) {
		return loginDAO.authenticateUser(user);
	}
}
