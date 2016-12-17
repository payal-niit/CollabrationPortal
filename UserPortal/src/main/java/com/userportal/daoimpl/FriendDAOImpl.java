package com.userportal.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userportal.dao.FriendDAO;
import com.userportal.model.Friend;

@Repository
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Friend> getMyFriends(int userId) {
		String hql="from Friend where userId= " + "'" + userId + "' and status ='"+ "A'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>)query.list();
		return list;
	}

	public Friend get(int userId, int friendId) {
		String hql = "from Friend where userID=" + "'" + userId + "' and friendId = '"+ friendId +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();

		if (list!= null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public void save(Friend friend) {
			sessionFactory.getCurrentSession().save(friend);	
	}

	public void update(Friend friend) {
			sessionFactory.getCurrentSession().update(friend);
	}

	public void delete(int userId, int friendId) {
		Friend friend=new Friend();
		friend.setFriendId(friendId);
		friend.setUserId(userId);
		sessionFactory.getCurrentSession().delete(friend);
	}

	public List<Friend> getNewFriendRequests(int friendId) {
		String hql="from Friend where friendId= " + "'" + friendId + "' and status = '"+ "N'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>)query.list();
		return list;
	}

	public void setOnline(int friendId) {
		String hql="UPDATE Friend SET isOnline = 'Y' where friendId='" + friendId + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	public void setOffline(int friendId) {
		String hql="UPDATE Friend SET isOnline = 'N' where friendId='" + friendId + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();	
	}

	public List<Friend> getMyFriend(int friendId) {
		String hql="from Friend where friendId= " + "'" + friendId + "' and status ='"+ "A'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>)query.list();
		return list;
	}
}
