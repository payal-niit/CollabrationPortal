package com.userportal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.userportal.model.User;
import com.userportal.service.FriendService;
import com.userportal.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FriendService friendService;
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET )
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@RequestMapping(value= "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	@RequestMapping(value= "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder builder) {
        boolean flag = userService.addUser(user);
               if (flag == false) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="/user/{id}", method = RequestMethod.PUT )
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/user/authenticate", method=RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user,HttpSession session)
	{
		user=userService.authenticate(user.getUsername(), user.getPassword());
		if(user==null)
		{
			user=new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials. Please enter valid credentials");
		}
		else
		{
			user.setErrorCode("200");
			user.setErrorMessage("Successfully logged in");
			session.setAttribute("loggedInUser", user);
			session.setAttribute("loggedInUserId", user.getUserId());
			friendService.setOnline(user.getUserId());
			userService.setOnline(user.getUserId());
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/logout", method=RequestMethod.GET)
	public String logout(HttpSession session)
	{
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		friendService.setOffline(loggedInUserID);
		userService.setOffline(loggedInUserID);
		session.invalidate();
		return ("you successfully logged out");
	}


}
