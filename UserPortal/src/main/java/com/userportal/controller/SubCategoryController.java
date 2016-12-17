package com.userportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userportal.model.SubCategory;
import com.userportal.service.CategoryService;
import com.userportal.service.SubCategoryService;

@Controller
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/subcategory")
	public String getSubCategory(Model model) {
		model.addAttribute("subCategory", new SubCategory());
		model.addAttribute("categoryList", categoryService.getList());
		return "subCateogory";
	}

}
