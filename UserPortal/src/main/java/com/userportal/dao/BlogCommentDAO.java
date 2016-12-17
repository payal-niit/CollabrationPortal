package com.userportal.dao;

import java.util.List;

import com.userportal.model.BlogComment;



public interface BlogCommentDAO {
	
	List<BlogComment> getAllBlogs(int blogId);
	BlogComment getBlogById(int blogCommentId);
    boolean addBlogComment(BlogComment blogComment);
    void updateBlogComment(BlogComment blogComment);
    void deleteBlogComment(int blogCommentId);
    
    //boolean blogExists(String blogName, String blogDescription);

}
