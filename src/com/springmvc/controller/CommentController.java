package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("comment")
public class CommentController {
	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model,HttpSession httpSession) {
		httpSession.getAttribute("admin");		
		if(httpSession.getAttribute("admin")==null){
			return "admin/login";
		}else{	
			JSONObject temp=JSON.parseObject(httpSession.getAttribute("admin").toString());
			System.out.println(temp.getString("username"));
			return "comment/list";
		}
	}
}