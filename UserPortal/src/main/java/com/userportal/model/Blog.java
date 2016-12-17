package com.userportal.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogId;
	private String blogName;
	private String blogDescription;
	private int userId;
	
	public Blog() {
		Date d= new Date();
		dateOfBlogCreation=d;
	}
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="userId", nullable = false, updatable = false, insertable = false)
	private User user;	
	
	private Date dateOfBlogCreation;
	
	public Date getDateOfBlogCreation() {
		return dateOfBlogCreation;
	}
	public void setDateOfBlogCreation(Date dateOfBlogCreation) {
		this.dateOfBlogCreation = dateOfBlogCreation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}	
	
	
}
