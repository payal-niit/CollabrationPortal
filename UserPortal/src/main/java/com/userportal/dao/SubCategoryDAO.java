package com.userportal.dao;

import java.util.List;


import com.userportal.model.SubCategory;

public interface SubCategoryDAO {
	
	public void addSubCategory(SubCategory subCategory);
	
	public List<SubCategory> getList();
	
	public SubCategory getCategoryById(int subCategoryId);
	
	public void deleteSubCategory(int subCategoryId);
}
