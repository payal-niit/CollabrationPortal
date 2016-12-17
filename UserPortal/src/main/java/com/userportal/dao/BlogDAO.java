package com.userportal.dao;

import java.util.List;

import com.userportal.model.Blog;

public interface BlogDAO {
	
	List<Blog> getAllBlogs();
	Blog getBlogById(int uid);
    boolean addBlog(Blog blog);
    void updateBlog(Blog blog);
    void deleteBlog(int bid);
    boolean blogExists(String blogName, String blogDescription);

}
