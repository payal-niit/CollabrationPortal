package com.userportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.daoimpl.CategoryDAOImpl;
import com.userportal.model.Category;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	CategoryDAOImpl categoryDAO;
	
	
	public void addCategory(Category category) {
		categoryDAO.addCategory(category);
	}
	public List<Category> getList() {
		return categoryDAO.getList();
	}
	
	public Category getCategoryById(int categoryId) {
		return categoryDAO.getCategoryById(categoryId);
	}
	
	public void deleteCategory(int categoryId) {
		categoryDAO.deleteCategory(categoryId);
	}


}
