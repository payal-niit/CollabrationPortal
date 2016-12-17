package com.userportal.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userportal.dao.ForumDAO;

import com.userportal.model.Forum;

@Repository
public class ForumDAOImpl implements ForumDAO{
	
	@Autowired
	private SessionFactory sessionFactory;	

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	public List<Forum> getAllForums() {
		@SuppressWarnings("unchecked")
		List<Forum> listForum = (List<Forum>) 
		          sessionFactory.getCurrentSession()
				.createCriteria(Forum.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();		
		return listForum;
	}

	public Forum getForumById(int forumId) {
		String hql = "from Forum where forumId=" +  forumId ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Forum> listPserson = (List<Forum>) query.list();
		
		if (listPserson != null && !listPserson.isEmpty()) {
			return listPserson.get(0);
		}
		
		return null;
	}

	public boolean addForum(Forum forum) {
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
		return false;
	}

	public void updateForum(Forum forum) {
		Forum f = getForumById(forum.getForumId());
		f.setForumTitle(forum.getForumTitle());
		f.setForumDescription(forum.getForumDescription());
		f.setUserId(forum.getUserId());
		f.setUsername(forum.getUsername());
		
		sessionFactory.getCurrentSession().update(f);
		
	}

	public void deleteForum(int forumId) {
		Forum ProductToDelete = new Forum();
		ProductToDelete.setForumId(forumId);
		sessionFactory.getCurrentSession().delete(ProductToDelete);		
	}

	public boolean forumExists(String forumTitle, String forumDescription) {
		String hql = "FROM Forum as b WHERE b.forumTitle = ? and b.forumDescription = ?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Forum> forums = (List<Forum>) query.list();
		return forums.size() > 0 ? true : false;
	}

}
