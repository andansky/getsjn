package com.springmvc.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apicloud.sdk.api.Resource;
import com.springmvc.data.Init;

/**
* 类名: AdminController </br>
* 包名： com.springmvc.controller
* 描述: 管理员类   </br>
* 创建时间：  2015-12-11 </br>
* 发布版本：V1.0  </br>
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	/**
	* 方法名：login</br>
	* 详述：跳转到登录页面  </br>
	* 创建时间：2015-12-11  </br>
	* @param model
	* @param httpSession
	* @return 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(ModelMap model,HttpSession httpSession){
        return "admin/login";
    }

    /**
    * 方法名：doLogin</br>
    * 详述：用户登录校验 </br>
    * 创建时间：2015-12-11  </br>
    * @param username
    * @param password
    * @param httpSession
    * @return 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    @RequestMapping(value = "dologin",method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,HttpSession httpSession){
        JSONObject property = new JSONObject();
        JSONObject property2 = new JSONObject();
        property2.put("username", username);
        property2.put("password", password);
        property.put("where",property2);
        Resource resource = new Resource(Init.appId, Init.appKey,"");
        com.alibaba.fastjson.JSONObject json = resource.doFilterSearch("admin",property.toString());
        JSONArray array=json.getJSONArray("data");
        com.alibaba.fastjson.JSONObject admin;
        if(array.size()>0){
            admin=array.getJSONObject(0);
            httpSession.setAttribute("admin", admin.toString());
            httpSession.setAttribute("username", username);
            return "redirect:/";
        }else{
            return "redirect:login";
        }
    }

    /**
    * 方法名：logout</br>
    * 详述：注销登录  </br>
    * 创建时间：2015-12-11  </br>
    * @param httpSession
    * @return 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("admin");
        httpSession.removeAttribute("username");
        return "admin/login";
    }

    
    @RequestMapping(value = "user_center")
    public String userCenter(ModelMap model,HttpSession httpSession){

        model.addAttribute("result",httpSession.getAttribute("username"));
        return "admin/user_center";
    }

    @RequestMapping(value = "create",method = RequestMethod.GET)
    public String createUser(ModelMap model){
        Resource resource = new Resource(Init.appId, Init.appKey,"");
        com.alibaba.fastjson.JSONObject property = new  com.alibaba.fastjson.JSONObject();
        property.put("username", "admin");
        property.put("password", "123456");
        property.put("email", "312328648@qq.com");
        com.alibaba.fastjson.JSONObject json = resource.createObject("admin", property);
        model.addAttribute("result", json+"");
        return "admin/login";
    }
}
