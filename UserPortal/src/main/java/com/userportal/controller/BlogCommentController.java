package com.userportal.controller;

import java.util.List;

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
import com.userportal.service.UserService;

@RestController
public class BlogCommentController {
	
	@Autowired
	private BlogCommentService blogCommentService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/blogComment{blogId}/{id}", method = RequestMethod.GET )
	public ResponseEntity<BlogComment> getBlogById(@PathVariable("id") Integer id) {
		BlogComment blog = blogCommentService.getBlogById(id);
		return new ResponseEntity<BlogComment>(blog, HttpStatus.OK);
	}
	
	/*@RequestMapping(value= "/blogComment{blogId}", method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getAllBlogs(@PathVariable("blogId") Integer blogId) {
		List<BlogComment> list = blogCommentService.getAllBlogs(blogId);
		return new ResponseEntity<List<BlogComment>>(list, HttpStatus.OK);
	}*/
	
	/*@RequestMapping(value= "/blogComment{blogId}", method = RequestMethod.POST)
	public ResponseEntity<Void> addBlog(@RequestBody BlogComment blog, UriComponentsBuilder builder) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		int user_id = userService.getUserByName(user).getUserId();
		blog.setUserId(user_id);
		System.out.println("USER id is: "+ user_id);
        boolean flag = blogCommentService.addBlogComment(blog);
               if (flag == false) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blogComment/{id}").buildAndExpand(blog.getBlogCommentId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
*/	
	@RequestMapping(value="/blogComment{blogId}/{id}", method = RequestMethod.PUT )
	public ResponseEntity<BlogComment> updateBlog(@RequestBody BlogComment blogComment) {
		blogCommentService.updateBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	@RequestMapping(value="/blogComment{blogId}/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBlog(@PathVariable("id") Integer id) {
		blogCommentService.deleteBlogComment(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
