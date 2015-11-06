package com.lovelife.service;

import java.util.List;

import com.lovelife.dao.DAOFactory;
import com.lovelife.dao.RetrieveMemberDAO;
import com.lovelife.model.UserDTO;

public class RetrieveMemberService {
	private RetrieveMemberDAO retrieveMemberDAO;

	public RetrieveMemberService() {
		retrieveMemberDAO = DAOFactory.loadInstance().getRetrieveMemberDAO();
	}

	public List<UserDTO> getAllMembers() {
		return retrieveMemberDAO.getAllMembers();
	}

	public UserDTO getMember(UserDTO user) {
		return retrieveMemberDAO.getMember(user);
	}

//	public boolean resetPassword(String email){
//		return retrieveMemberDAO.
//	}
}
