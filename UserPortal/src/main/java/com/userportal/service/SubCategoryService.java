package com.userportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.daoimpl.SubCategoryDAOImpl;
import com.userportal.model.SubCategory;

@Service
@Transactional
public class SubCategoryService {
	@Autowired
	private SubCategoryDAOImpl subCategoryDAO;
	
public void addSubCategory(SubCategory subCategory) {
	subCategoryDAO.addSubCategory(subCategory);
}
	
	public List<SubCategory> getList() {
		return subCategoryDAO.getList();
	}

}
