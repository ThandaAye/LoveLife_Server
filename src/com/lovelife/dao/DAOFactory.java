package com.lovelife.dao;

public abstract class DAOFactory {
	public static DAOFactory loadInstance(){
		return new com.lovelife.dao.mySQL.DAOFactoryImplementation();
	}
	public abstract OpenConnectionDAO getOpenConnectionDAO();
	public abstract RetrieveMemberDAO getRetrieveMemberDAO();
	public abstract LoginDAO getLoginDAO();
	public abstract RegisterDAO getRegisterDAO();
}