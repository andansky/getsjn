package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model,HttpSession httpSession) {
		httpSession.getAttribute("admin");
		if(httpSession.getAttribute("admin")==null){
			return "admin/login";
		}else{
			return "index/index";
		}
	}
}