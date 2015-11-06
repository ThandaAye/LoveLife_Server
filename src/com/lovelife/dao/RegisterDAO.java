package com.lovelife.dao;

import com.lovelife.model.UserDTO;

public interface RegisterDAO {
	public boolean registerAccount(UserDTO user);
}