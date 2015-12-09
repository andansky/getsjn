package com.springmvc.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apicloud.sdk.api.Resource;
import com.springmvc.data.Init;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/11/18 0018.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(ModelMap model,HttpSession httpSession){
        return "admin/login";
    }

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
