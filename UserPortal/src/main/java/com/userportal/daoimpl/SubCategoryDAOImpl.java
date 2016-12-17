package com.userportal.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userportal.dao.SubCategoryDAO;
import com.userportal.model.SubCategory;

@Repository
public class SubCategoryDAOImpl implements SubCategoryDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public void addSubCategory(SubCategory subCategory) {
		/*Session session=sessionFactory.openSession();
		session.saveOrUpdate(subCategory);
		session.flush();*/
		
		sessionFactory.getCurrentSession().saveOrUpdate(subCategory);
		
	}

	public List<SubCategory> getList() {
		Session session=sessionFactory.getCurrentSession();
		List<SubCategory> subCategoryList=session.createCriteria(SubCategory.class).list();
		return subCategoryList;
	}

	public SubCategory getCategoryById(int subCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteSubCategory(int subCategoryId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
