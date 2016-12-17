package com.userportal.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.userportal.config.CustomJsonDateDeserializer;

@Entity
public class BlogComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogCommentId;
	
	private int blogId;
	private String commentDetail;
	private int userId;
	private String username;
	
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYYY,HH:00")
	private Date dateOfComment;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="userId", nullable = false, updatable = false, insertable = false)
	@JsonIgnore
	private User user;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="blogId", nullable = false, updatable = false, insertable = false)
	@JsonIgnore
	private Blog blog;
	
	public BlogComment() {
		Date d= new Date();
		dateOfComment=d;

	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@JsonSerialize(using = DateSerializer.class)
	public Date getDateOfComment() {
		return dateOfComment;
	}
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setDateOfComment(Date dateOfComment) {
		this.dateOfComment = dateOfComment;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getCommentDetail() {
		return commentDetail;
	}

	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public int getBlogCommentId() {
		return blogCommentId;
	}

	public void setBlogCommentId(int blogCommentId) {
		this.blogCommentId = blogCommentId;
	}
	
	
	

}
