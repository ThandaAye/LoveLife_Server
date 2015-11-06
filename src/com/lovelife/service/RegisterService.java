package com.lovelife.service;

import com.lovelife.dao.DAOFactory;
import com.lovelife.dao.RegisterDAO;
import com.lovelife.model.UserDTO;

public class RegisterService {
	private RegisterDAO registerDAO;
	public RegisterService() {
		registerDAO = DAOFactory.loadInstance().getRegisterDAO();
	}

	public boolean registerAccount(UserDTO user) {
		return registerDAO.registerAccount(user);
	}
}
