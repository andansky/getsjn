package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
	
	/**
	* 方法名：printWelcome</br>
	* 详述：跳转到主页  </br>
	* 创建时间：2015-12-11  </br>
	* @param model
	* @param httpSession
	* @return 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
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