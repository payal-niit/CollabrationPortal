package com.userportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userportal.model.Category;
import com.userportal.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/category")
	public String addCategory(Model model) {
		//this is an attribute which will ensure that a new CATEGORY is added
		model.addAttribute("category", new Category());
		model.addAttribute("categoryList", categoryService.getList());
		return "category";
	}
	
	//no view page for this because this is an action
	@RequestMapping("/addcategory")
	public String categoryAction(@ModelAttribute("category") Category category) {		
		//this is the query which will insert data in category table
		categoryService.addCategory(category);
		return "redirect:/category";
	}
	
	@RequestMapping("edit-{categoryId}")
	public String editCategory(Model model,@PathVariable("categoryId") int categoryId) {
		model.addAttribute("category", categoryService.getCategoryById(categoryId));
		return "category";
	}
	
	@RequestMapping("/deleteCategory-{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") int categoryId) {
		//this is the query which deletes it
		categoryService.deleteCategory(categoryId);
		return "redirect:/category";
	}
	
	

}
