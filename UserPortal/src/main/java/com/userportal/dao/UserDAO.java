package com.userportal.dao;

import java.util.List;

import com.userportal.model.User;

public interface UserDAO {
	
	List<User> getAllUsers();
	User getUserById(int pid);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(int uid);
    boolean userExists(String name, String location);

}
