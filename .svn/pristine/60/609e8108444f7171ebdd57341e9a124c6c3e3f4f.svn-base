package com.mbgo.searchmgr.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mbgo.searchmgr.core.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController{

	public final static String PACKAGE = "pages/view";
	@Autowired
	private UserService userService;
	

	  @RequestMapping(value = "/successLogin")
	    public String successLogin() {
	        return PACKAGE + "/main";
	    }
	  
	  @RequestMapping(value = "/unauth")
	    public String unauth() {
	        return "/common/unauth";
	    }
	  
	  
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	  
	  
	  
	
}
