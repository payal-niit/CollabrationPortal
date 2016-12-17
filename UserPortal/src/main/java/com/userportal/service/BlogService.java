package com.userportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.daoimpl.BlogDAOImpl;
import com.userportal.model.Blog;

@Service
@Transactional
public class BlogService {
	@Autowired
	private BlogDAOImpl blogDAO;
	
	public List<Blog> getAllBlogs() {
		return blogDAO.getAllBlogs();
	}
	public Blog getBlogById(int bid) {
		return blogDAO.getBlogById(bid);
	}
    public boolean addBlog(Blog blog) {
    	return blogDAO.addBlog(blog);
    }
    public void updateBlog(Blog blog) {
    	blogDAO.updateBlog(blog);
    }
    public void deleteBlog(int blogId) {
    	blogDAO.deleteBlog(blogId);
    }
    public boolean blogExists(String blogName, String blogDescription) {
    	return blogDAO.blogExists(blogName, blogDescription);
    }

}
