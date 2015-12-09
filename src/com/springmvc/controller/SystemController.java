package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("system")
public class SystemController {
	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model,HttpSession httpSession) {
		httpSession.getAttribute("admin");
		if(httpSession.getAttribute("admin")==null){
			return "admin/login";
		}else{
			return "system/allset";
		}
	}

	@RequestMapping(value = "sysset",method = RequestMethod.GET)
	public String add(ModelMap modelMap){
		return "system/sysset";
	}
}