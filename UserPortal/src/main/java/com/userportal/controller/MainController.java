package com.userportal.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.userportal.model.Forum;
import com.userportal.service.ForumService;

@Controller
public class MainController {
	@Autowired
	ForumService forumService;
	
	@RequestMapping("/")
	public ModelAndView getHome() {
		
		ModelAndView m=new ModelAndView("index");
		return m;
	}
	
	/*@RequestMapping("/loginpage")
	public String login() {
		
		return "login";
		
	}*/
	
	@RequestMapping("/perform_logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/";
		
	}
	
	
	Path path;
	
	/*@RequestMapping("/upload-{forumId}")
	public String upload(@ModelAttribute("f") Forum f,@PathVariable("forumId")int forumId, HttpServletRequest request,MultipartFile  productImage,HttpSession session) {
		f=forumService.getForumById(forumId);
		
		forumId=forumService.getForumById(forumId).getForumId();
		session.setAttribute("forum_id", forumId);
		productImage = forumService.getForumById(forumId).getForumImage();
        System.out.println("product image"+productImage);
	    String rootDirectory = request.getSession().getServletContext().getRealPath("/");
	    System.out.println("root directory" + rootDirectory);
	    path = Paths.get(rootDirectory + "/resources/images/" + forumService.getForumById(forumId).getForumId()+ forumService.getForumById(forumId).getForumTitle() + ".jpg");

	    if(productImage != null && !productImage.isEmpty()){
	        try {
	            productImage.transferTo(new File(path.toString()));
	        } 
	        catch (Exception ex){
	            ex.printStackTrace();
	            throw new RuntimeException("Product image saving failed", ex);
	        }
	    }
		return "redirect:/";
	}*/
	
	
	

}
