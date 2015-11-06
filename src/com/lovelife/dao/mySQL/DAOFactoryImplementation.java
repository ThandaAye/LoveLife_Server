package com.lovelife.dao.mySQL;

import com.lovelife.dao.DAOFactory;
import com.lovelife.dao.LoginDAO;
import com.lovelife.dao.OpenConnectionDAO;
import com.lovelife.dao.RegisterDAO;
import com.lovelife.dao.RetrieveMemberDAO;

public class DAOFactoryImplementation extends DAOFactory{
	private OpenConnectionDAO openConnectionDAO = new OpenConnectionDAOImplementation();
	private RetrieveMemberDAO retrieveMemberDAO = new RetrieveMemberDAOImplementation();
	private LoginDAO loginDAO = new LoginDAOImplementation();
	private RegisterDAO registerDAO = new RegisterDAOImplementation();
	@Override
	public LoginDAO getLoginDAO(){
		return loginDAO;
	}

	@Override
	public OpenConnectionDAO getOpenConnectionDAO() {
		return openConnectionDAO;
	}

	@Override
	public RegisterDAO getRegisterDAO() {
		return registerDAO;
	}
	
	@Override
	public RetrieveMemberDAO getRetrieveMemberDAO(){
		return retrieveMemberDAO;
	}
	
}
