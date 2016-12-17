package com.userportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.daoimpl.FriendDAOImpl;
import com.userportal.model.Friend;

@Service
@Transactional
public class FriendService {
	@Autowired
	private FriendDAOImpl friendDAO;
	
	public List<Friend> getMyFriends(int userId) {
		return friendDAO.getMyFriend(userId);
	}
	public List<Friend> getMyFriend(int friendId) {
		return friendDAO.getMyFriend(friendId);
	}
	public Friend get(int userId,int friendId) {
		return friendDAO.get(userId, friendId);
	}
	public void save(Friend friend) {
		friendDAO.save(friend);
	}
	public void update(Friend friend) {
		friendDAO.update(friend);
	}
	public void delete(int userId,int friendId) {
		friendDAO.delete(userId, friendId);
	}
	public List<Friend> getNewFriendRequests(int friendId) {
		return friendDAO.getNewFriendRequests(friendId);
	}
	public void setOnline(int friendId) {
		friendDAO.setOnline(friendId);
	}
	public void setOffline(int friendId) {
		friendDAO.setOffline(friendId);
	}

}
