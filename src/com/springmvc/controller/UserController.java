package com.springmvc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apicloud.sdk.api.Resource;
import com.springmvc.data.Init;
import com.springmvc.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
/**
* 类名: UserController </br>
* 包名： com.springmvc.controller
* 描述: 用户控制类  </br>
* 创建时间：  2015-12-11 </br>
* 发布版本：V1.0  </br>
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	/**
	* 方法名：list</br>
	* 详述：用户列表 </br>
	* 创建时间：2015-12-11  </br>
	* @param model
	* @param httpSession
	* @return 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
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

	/**
	* 方法名：add</br>
	* 详述：增加用户  </br>
	* 创建时间：2015-12-11  </br>
	* @param modelMap
	* @return 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add(ModelMap modelMap){
		
		
		return "user/add";
	}
}