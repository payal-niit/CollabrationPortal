package com.userportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userportal.daoimpl.ForumDAOImpl;
import com.userportal.model.Forum;
@Service
@Transactional
public class ForumService {
	@Autowired
	private ForumDAOImpl forumDAO;
	
	public List<Forum> getAllForums() {
		return forumDAO.getAllForums();
	}
	public Forum getForumById(int forumId) {
		return forumDAO.getForumById(forumId);
	}
	public boolean addForum(Forum forum) {
    	return forumDAO.addForum(forum);
    }
	public void updateForum(Forum forum) {
    	forumDAO.updateForum(forum);
    }
	public void deleteForum(int forumId) {
    	forumDAO.deleteForum(forumId);
    }
	public boolean forumExists(String forumTitle, String forumDescription) {
    	return forumDAO.forumExists(forumTitle, forumDescription);
    }

}
