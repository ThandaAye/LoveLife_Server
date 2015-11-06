package com.lovelife.dao;

import java.util.List;

import com.lovelife.model.UserDTO;

public interface RetrieveMemberDAO {
	public List<UserDTO> getAllMembers();
	public UserDTO getMember(UserDTO user);
	public int resetPassword(String email);
	public int changePassword(String email,String oldPassword, String newPassword);
}
