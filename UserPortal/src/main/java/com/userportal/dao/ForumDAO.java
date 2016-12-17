package com.userportal.dao;

import java.util.List;

import com.userportal.model.Forum;

public interface ForumDAO {
	
	List<Forum> getAllForums();
	Forum getForumById(int uid);
    boolean addForum(Forum forum);
    void updateForum(Forum forum);
    void deleteForum(int forumId);
    boolean forumExists(String forumTitle, String forumDescription);

}
