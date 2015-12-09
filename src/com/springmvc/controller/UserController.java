package com.springmvc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apicloud.sdk.api.Resource;
import com.springmvc.data.Init;
import com.springmvc.entity.ArticleEntity;
import com.springmvc.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("user")
public class UserController {
	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model,HttpSession httpSession) {
		ArrayList<UserEntity> list=new ArrayList<UserEntity>();
		if(httpSession.getAttribute("admin")==null){
			return "admin/login";
		}else{
			Resource resource=new Resource(Init.appId,Init.appKey,"");
			JSONArray jsonArray=resource.getObjects("user").getJSONArray("data");

			for(int i=0;i<jsonArray.size();i++){
				UserEntity userEntity=new UserEntity();
				JSONObject jsonObject=new JSONObject();
				jsonObject=jsonArray.getJSONObject(i);
				userEntity.setId(jsonObject.getString("id"));
				userEntity.setUsername(jsonObject.getString("username"));
				userEntity.setNickname(jsonObject.getString("nickname"));
				userEntity.setCreate_date(jsonObject.getString("createdAt"));
				list.add(userEntity);
			}
			model.addAttribute("list",list);
			return "user/list";
		}
	}

	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add(ModelMap modelMap){
		return "user/add";
	}
}