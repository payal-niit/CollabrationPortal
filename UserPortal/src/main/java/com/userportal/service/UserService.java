package com.userportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.daoimpl.UserDAOImpl;
import com.userportal.model.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDAOImpl userDAO;
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	public User getUserById(int uid) {
		return userDAO.getUserById(uid);
	}
    public boolean addUser(User user) {
    	return userDAO.addUser(user);
    }
    public void updateUser(User user) {
    	userDAO.updateUser(user);
    }
    public void deleteUser(int uid) {
    	userDAO.deleteUser(uid);
    }
    public boolean userExists(String username, String role) {
    	return userDAO.userExists(username, role);
    }
    public User authenticate(String username, String password)
	{
		return userDAO.authenticate(username, password);
	}
	public void setOnline(int userId)
	{
		userDAO.setOnline(userId);
	}
	public void setOffline(int userId)
	{
		userDAO.setOffline(userId);
	}
	
	public User getUserByName(String username) {
		return userDAO.getUserByName(username);
	}

}
