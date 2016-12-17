package com.userportal.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userportal.dao.BlogDAO;
import com.userportal.model.Blog;

@Repository
public class BlogDAOImpl implements BlogDAO{
		
		@Autowired
		private SessionFactory sessionFactory;	
		
		public BlogDAOImpl(SessionFactory sessionFactory) {
			this.sessionFactory=sessionFactory;
		}

		public List<Blog> getAllBlogs() {
			
			@SuppressWarnings("unchecked")
			List<Blog> listBlog = (List<Blog>) 
			          sessionFactory.getCurrentSession()
					.createCriteria(Blog.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			
			return listBlog;
		}

		public Blog getBlogById(int blogId) {
			String hql = "from Blog where blogId=" +  blogId ;
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Blog> listPserson = (List<Blog>) query.list();
			
			if (listPserson != null && !listPserson.isEmpty()) {
				return listPserson.get(0);
			}
			
			return null;
		}

		public boolean addBlog(Blog blog) {
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return false;
		}

		public void updateBlog(Blog blog) {
			Blog b = getBlogById(blog.getBlogId());
			b.setBlogName(blog.getBlogName());
			b.setBlogDescription(blog.getBlogDescription());
			sessionFactory.getCurrentSession().update(b);			
		}

		public void deleteBlog(int blogId) {
			Blog ProductToDelete = new Blog();
			ProductToDelete.setBlogId(blogId);
			sessionFactory.getCurrentSession().delete(ProductToDelete);
			
		}

		public boolean blogExists(String blogName, String blogDescription) {
			String hql = "FROM Blog as b WHERE b.blogName = ? and b.blogDescription = ?";
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Blog> blogs = (List<Blog>) query.list();
			return blogs.size() > 0 ? true : false;
		}

}
