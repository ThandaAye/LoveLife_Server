package com.lovelife.dao;

import com.lovelife.model.UserDTO;

public interface LoginDAO {
		public UserDTO authenticateUser(UserDTO user);
}
