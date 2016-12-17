package com.userportal.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userportal.dao.BlogCommentDAO;

import com.userportal.model.BlogComment;

@Repository
public class BlogCommentDAOImpl implements BlogCommentDAO{
	
	@Autowired
	private SessionFactory sessionFactory;		

	

	public BlogCommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	public List<BlogComment> getAllBlogs(int blogId) {
		
		Session session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(BlogComment.class);
		criteria.add(Restrictions.eq("blogId", blogId));
		@SuppressWarnings("unchecked")
		List<BlogComment> commentListbyId= (List<BlogComment>)(criteria.list());
		session.flush();
		return commentListbyId;
	}
	
	public BlogComment getBlogById(int blogCommentId) {
		String hql = "from BlogComment where blogCommentId=" +  blogCommentId ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<BlogComment> listPserson = (List<BlogComment>) query.list();
		
		if (listPserson != null && !listPserson.isEmpty()) {
			return listPserson.get(0);
		}		
		return null;
	}

	public boolean addBlogComment(BlogComment blogComment) {
		sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
		return false;
	}
	
	public void updateBlogComment(BlogComment blogComment) {
		BlogComment b = getBlogById(blogComment.getBlogCommentId());
		b.setCommentDetail(blogComment.getCommentDetail());
		b.setUserId(b.getUserId());
		b.setBlogId(b.getBlogId());
		sessionFactory.getCurrentSession().update(b);			
	}

	public void deleteBlogComment(int blogCommentId) {
		BlogComment ProductToDelete = new BlogComment();
		ProductToDelete.setBlogCommentId(blogCommentId);
		sessionFactory.getCurrentSession().delete(ProductToDelete);		
	}

}
