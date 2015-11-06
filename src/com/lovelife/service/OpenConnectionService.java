package com.lovelife.service;

import java.sql.Connection;

import com.lovelife.dao.DAOFactory;
import com.lovelife.dao.OpenConnectionDAO;

public class OpenConnectionService {
	private OpenConnectionDAO openConnectionDAO;

	public OpenConnectionService() {
		openConnectionDAO = DAOFactory.loadInstance().getOpenConnectionDAO();
	}

	public Connection openConnection() {
		return openConnectionDAO.openConnection();
	}
}
