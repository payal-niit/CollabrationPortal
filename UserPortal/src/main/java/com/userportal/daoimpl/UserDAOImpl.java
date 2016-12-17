package com.userportal.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userportal.dao.UserDAO;
import com.userportal.model.User;
@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;		

	public UserDAOImpl(SessionFactory sessionFactory2) {
		this.sessionFactory=sessionFactory;
	}

	public List<User> getAllUsers() {
		
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) 
		          sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return listUser;
	}

	public User getUserById(int userId) {
		String hql = "from User where userId=" +  userId ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> listPserson = (List<User>) query.list();
		
		if (listPserson != null && !listPserson.isEmpty()) {
			return listPserson.get(0);
		}
		
		return null;
	}
	
	public User getUserByName(String username) {
		String hql = "from User where username=" +  "'" + username + "'" ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> listPserson = (List<User>) query.list();
		
		if (listPserson != null && !listPserson.isEmpty()) {
			return listPserson.get(0);
		}
		
		return null;
	}

	public boolean addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return false;
	}

	public void updateUser(User user) {
		User u = getUserById(user.getUserId());
		u.setEnabled(true);
		u.setRole(u.getRole());
		u.setPassword(u.getPassword());
		u.setUsername(user.getUsername());
		u.setFname(u.getFname());
		u.setLname(u.getLname());
		u.setGender(u.getGender());
		u.setAge(u.getAge());
		
		sessionFactory.getCurrentSession().update(u);			
	}

	public void deleteUser(int uid) {
		User ProductToDelete = new User();
		ProductToDelete.setUserId(uid);
		sessionFactory.getCurrentSession().delete(ProductToDelete);
		
	}

	public boolean userExists(String username, String role) {
		String hql = "FROM User as u WHERE u.username = ? and u.role = ?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) query.list();
		return users.size() > 0 ? true : false;
	}
	
public User authenticate(String username, String password) {
		
		String hql = "from User where username=" + "'" + username + "' and " + " password='" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
	}

	public void setOnline(int userId) {
		String hql="UPDATE User SET isOnline = 'Y' where userId='" + userId + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	public void setOffline(int userId) {
		String hql="UPDATE User SET isOnline = 'N' where userId='" + userId + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();	
	}



}
