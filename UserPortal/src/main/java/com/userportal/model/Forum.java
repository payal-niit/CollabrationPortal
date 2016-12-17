package com.userportal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.userportal.config.CustomJsonDateDeserializer;

@Entity
public class Forum implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int forumId;
	private String forumTitle;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYYY")
	private Date forumCreationDate;
	
	@JsonIgnore
	@Transient
	private MultipartFile forumImage;
	private String forumDescription;
	private int userId;	
	
	public Forum() {
		Date d=new Date();
		forumCreationDate=d;
	}
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="userId", nullable = false, updatable = false, insertable = false)
	private User user;
	
	private String username;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getForumTitle() {
		return forumTitle;
	}
	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	
	@JsonSerialize(using = DateSerializer.class)
	public Date getForumCreationDate() {
		return forumCreationDate;
	}
	
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setForumCreationDate(Date forumCreationDate) {
		this.forumCreationDate = forumCreationDate;
	}

	public MultipartFile getForumImage() {
		return forumImage;
	}
	public void setForumImage(MultipartFile forumImage) {
		this.forumImage = forumImage;
	}
	public String getForumDescription() {
		return forumDescription;
	}
	public void setForumDescription(String forumDescription) {
		this.forumDescription = forumDescription;
	}
	
}
