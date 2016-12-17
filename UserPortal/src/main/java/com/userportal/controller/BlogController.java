package com.userportal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.userportal.model.Blog;
import com.userportal.model.BlogComment;
import com.userportal.service.BlogCommentService;
import com.userportal.service.BlogService;
import com.userportal.service.UserService;

@RestController
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/blog/{id}", method = RequestMethod.GET )
	public ResponseEntity<Blog> getBlogById(@PathVariable("id") Integer id, HttpSession session) {
		Blog blog = blogService.getBlogById(id);
		session.setAttribute("blogId", id);
		int blog_id=(Integer) session.getAttribute("blogId");
		System.out.println("BLOGID is:"+blog_id);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	@RequestMapping(value= "/blog", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlogs() {
		List<Blog> list = blogService.getAllBlogs();
		
		return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);
	}
	@RequestMapping(value= "/blog", method = RequestMethod.POST)
	public ResponseEntity<Void> addBlog(@RequestBody Blog blog, UriComponentsBuilder builder) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		int user_id = userService.getUserByName(user).getUserId();
		blog.setUserId(user_id);
		System.out.println("USER id is: "+ user_id);
        boolean flag = blogService.addBlog(blog);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blog/{id}").buildAndExpand(blog.getBlogId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="/blog/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
		blogService.updateBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	@RequestMapping(value="/blog/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBlog(@PathVariable("id") Integer id) {
		blogService.deleteBlog(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
	/*-----------------blog comment------------------*/
	@Autowired
	private BlogCommentService blogCommentService;
	
	
	@RequestMapping(value= "/blogComment", method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getAllBlogs(HttpSession session) {
		int blogId = (Integer) session.getAttribute("blogId");
		List<BlogComment> list = blogCommentService.getAllBlogs(blogId);
		System.out.println("blogs are:"+list);
		return new ResponseEntity<List<BlogComment>>(list, HttpStatus.OK);
	}
	
	/*
	  @RequestMapping(value= "/blogComment{blogId}", method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getAllBlogs(@PathVariable Integer blogId, HttpSession session) {
		int blogId = (Integer) session.getAttribute("blogId");
		List<BlogComment> list = blogCommentService.getAllBlogs(blogId);
		System.out.println("blogs are:"+list);
		return new ResponseEntity<List<BlogComment>>(list, HttpStatus.OK);
	}
	*/
	
	
	@RequestMapping(value= "/blogComment", method = RequestMethod.POST)
	public ResponseEntity<Void> addBlog(@RequestBody BlogComment blog, UriComponentsBuilder builder,HttpSession session) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		int user_id = userService.getUserByName(user).getUserId();
		blog.setUserId(user_id);
		blog.setUsername(user);
		int blogId = (Integer) session.getAttribute("blogId");
		
		blog.setBlogId(blogId);
		System.out.println("USER id is: "+ user_id);
        boolean flag = blogCommentService.addBlogComment(blog);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blogComment/{id}").buildAndExpand(blog.getBlogCommentId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

}
