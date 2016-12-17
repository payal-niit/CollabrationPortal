package com.userportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.daoimpl.BlogCommentDAOImpl;
import com.userportal.model.BlogComment;

@Service
@Transactional
public class BlogCommentService {
	@Autowired
	private BlogCommentDAOImpl blogCommentDAO;
	
	public List<BlogComment> getAllBlogs(int blogId)  {
		return blogCommentDAO.getAllBlogs(blogId);
	}
	public BlogComment getBlogById(int blogCommentId) {
		return blogCommentDAO.getBlogById(blogCommentId);
	}
	public boolean addBlogComment(BlogComment blogComment) {
    	return blogCommentDAO.addBlogComment(blogComment);
    }
	public void updateBlogComment(BlogComment blogComment) {
    	blogCommentDAO.addBlogComment(blogComment);
    }
	public void deleteBlogComment(int blogCommentId) {
    	blogCommentDAO.deleteBlogComment(blogCommentId);
    }

}
