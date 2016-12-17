package com.userportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userportal.model.Forum;
import com.userportal.service.ForumService;
import com.userportal.service.UserService;

@RestController

@MultipartConfig
public class ForumController {
	
	@Autowired
	private ForumService forumService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/forum/{id}", method = RequestMethod.GET )
	public ResponseEntity<Forum> getForumById(@PathVariable("id") Integer id, HttpSession session) {
		Forum forum = forumService.getForumById(id);
		session.setAttribute("forumId", id);
		int forum_id=(Integer) session.getAttribute("forumId");
		System.out.println("Forum id is:"+forum_id);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	@RequestMapping(value= "/forum", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> getAllForums() {
		List<Forum> list = forumService.getAllForums();
		
		return new ResponseEntity<List<Forum>>(list, HttpStatus.OK);
	}
	
	Path path;
	@RequestMapping(value= "/forum", method = RequestMethod.POST)
	
	public ResponseEntity<Void> addForum(@RequestBody Forum forum, UriComponentsBuilder builder,Map<String, Object> model,HttpServletRequest  request, MultipartFile productImage) {
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;	
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		int user_id = userService.getUserByName(user).getUserId();*/	
		ObjectMapper mapper = new ObjectMapper();
		//forum = mapper.readValue(productImage,Forum.class);
		forum.setUserId(33);
		forum.setUsername("mayank");
		//System.out.println("USER id is: "+ user_id);
        boolean flag = forumService.addForum(forum);
        
        /*String path="E:\\payal\\ProjectThree\\IndianAttire\\src\\main\\webapp\\resources\\images\\";
		path=path+String.valueOf(forum.getForumId())+".jpg";
		File f=new File(path);
		System.out.println("path is"+path);
		productImage=forum.getForumImage();
		
		if(!productImage.isEmpty())
		{
			try
			{
			  byte[] bytes=productImage.getBytes();
			  FileOutputStream fos=new FileOutputStream(f);
              			BufferedOutputStream bs=new BufferedOutputStream(fos);
              			bs.write(bytes);
              			bs.close();
             			 System.out.println("File Uploaded Successfully");
			}
			catch(Exception e)
			{
				System.out.println("Exception Arised"+e);
			}
		}
		else
		{
			System.out.println("File is Empty not Uploaded");
			
		}*/
        
        //multipart
        
        //productImage = forum.getForumImage();
        System.out.println("product image"+productImage);
	    String rootDirectory = request.getSession().getServletContext().getRealPath("/");
	    System.out.println("root directory" + rootDirectory);
	    path = Paths.get(rootDirectory + "/resources/images/" + forum.getForumId() + ".jpg");

	    if(productImage != null && !productImage.isEmpty()){
	        try {
	            productImage.transferTo(new File(path.toString()));
	        } 
	        catch (Exception ex){
	            ex.printStackTrace();
	            throw new RuntimeException("Product image saving failed", ex);
	        }
	    }
        
        //multipart over
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               //headers.setContentType(MediaType.IMAGE_JPEG);
               
               //headers.setContentType(MediaType.MULTIPART_FORM_DATA);
               headers.setLocation(builder.path("/forum/{id}").buildAndExpand(forum.getForumId()).toUri());
               return new ResponseEntity<Void>( HttpStatus.CREATED);
	}
	@RequestMapping(value="/forum/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum) {
		forumService.updateForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	@RequestMapping(value="/forum/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteForum(@PathVariable("id") Integer id) {
		forumService.deleteForum(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
}
