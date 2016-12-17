package com.userportal.dao;

import java.util.List;

import com.userportal.model.Category;

public interface CategoryDAO {
	
	public void addCategory(Category category);
	
	public List<Category> getList();
	
	public Category getCategoryById(int categoryId);
	
	public void deleteCategory(int categoryId);
}
