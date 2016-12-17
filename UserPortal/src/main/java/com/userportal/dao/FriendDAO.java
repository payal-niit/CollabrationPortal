package com.userportal.dao;

import java.util.List;

import com.userportal.model.Friend;

public interface FriendDAO {
	
	public List<Friend> getMyFriends(int userId);
	public List<Friend> getMyFriend(int friendId);
	public Friend get(int userId,int friendId);
	public void save(Friend friend);
	public void update(Friend friend);
	public void delete(int userId,int friendId);
	public List<Friend> getNewFriendRequests(int friendId);
	public void setOnline(int friendId);
	public void setOffline(int friendId);

}