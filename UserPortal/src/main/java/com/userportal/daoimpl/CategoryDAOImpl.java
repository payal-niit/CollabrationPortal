package com.userportal.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.dao.CategoryDAO;
import com.userportal.model.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public void addCategory(Category category) {
		sessionFactory.getCurrentSession().saveOrUpdate(category);
	}


	public List<Category> getList() {
		Session session=sessionFactory.getCurrentSession();
		String hql="from Category";
		@SuppressWarnings("unchecked")
		List<Category> clist=session.createQuery(hql).list();
		return clist;
	}
	
	public Category getCategoryById(int categoryId) {
		Session session=sessionFactory.getCurrentSession();
		String hql="from Category where categoryId="+categoryId;
		@SuppressWarnings("unchecked")
		List<Category> clist=session.createQuery(hql).list();
		return clist.get(0);
	}
	
	public void deleteCategory(int categoryId) {
		Category categoryToDelete =new Category();
		categoryToDelete.setCategoryId(categoryId);
		sessionFactory.getCurrentSession().delete(categoryToDelete);
	}

}
