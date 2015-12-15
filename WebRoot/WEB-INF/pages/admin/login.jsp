<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/18 0018
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="renderer" content="webkit">
  <title>后台管理-管理员登录</title>
  <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
  <link rel="stylesheet" href="<%=basePath%>css/admin.css">
  <script src="<%=basePath%>js/jquery.js"></script>
  <script src="<%=basePath%>js/pintuer.js"></script>
  <script src="<%=basePath%>js/respond.js"></script>
  <script src="<%=basePath%>js/admin.js"></script>
  <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon" />
  <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
</head>

<body>
<div class="container">
  <div class="line">
    <div class="xs6 xm4 xs3-move xm4-move">
      <br />
      <br />
      <div class="media media-y">
        <a href="http://www.souvc.com" target="_blank"><img src="<%=basePath%>images/logo.png" class="radius" alt="后台管理系统" /></a>
      </div>
      <br />
      <br />
      <form action="<%=basePath%>admin/dologin" method="post" id="loginForm" name="loginForm">
        <div class="panel">
          <div class="panel-head"><strong>登录后台管理系统</strong></div>
          <div class="panel-body" style="padding:30px;">
            <div class="form-group">
              <div class="field field-icon-right">
                <input type="text" class="input" name="username" id="username" placeholder="登录账号" value="${username }"/>
                <span ><font id="username_error" style="display: none;" color="red" ></font></span>
              </div>
            </div>
            <div class="form-group">
              <div class="field field-icon-right">
                <input type="password" class="input" name="password" id="password" placeholder="登录密码" />
                <span ><font id="password_error" style="display: none;" color="red" ></font></span>
              </div>
            </div>
            <div class="form-group">
              <div class="field field-icon-right">
                <input type="text" class="input" name="passcode" id="passcode" placeholder="填写右侧的验证码" />
                <img src="<%=basePath%>captcha-image" id="randImage" name="randImage" width="80" height="32" class="passcode" alt="验证码"/>
                <span ><font id="passcode_error" style="display: none;" color="red" ></font></span>
                <span ><font id="user_error" color="red" >${errormessage }</font></span>
              </div>
            </div>
          </div>
          <div class="panel-foot text-center">
            <button class="button button-block bg-main text-big" id="loginBtn">立即登录后台</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>

<script type="text/javascript">
jQuery(function($){
        
	 $('#randImage').click(function () {//生成验证码
		  $(this).hide().attr('src', '<%=basePath%>captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); 
	  });
	
	 /**去掉字符串前后所有空格*/
     function trim(str){ 
        return str.replace(/(^\s*)|(\s*$)/g, ""); 
     }
	
	 
     //当“密码”失去焦点时发生 blur 事件。
   	 $("#password").blur(function(){
   		vailPwd();
   	 });
   	 
   	 //当“密码”获得焦点时，发生 focus 事件。
   	 $("#password").on('focus',function(e){
   		$("#password_error").hide();
   		$("#user_error").hide();
   	 });
	
	/**验证密码*/
    function vailPwd(){
       	var password = trim($("#password").val());
       	var flag = false;
       	var message = "";
       	var patrn=/^\d+$/;
       	if(password ==''){
       		message = "密码不能为空！";
       	}else if(password.length<6 || password.length>16){
       		message = "密码6-16位！";
       	}else if(patrn.test(password)){
       		message = "密码不能全是数字！";
       	}else{
       		flag = true;
       	}
       	if(!flag){
       		$("#password_error").html(message).show();
       	}else{
       		$("#password_error").html(message).hide();
       	}
       	return flag;
       }
	
	
     //当“用户名”失去焦点时发生 blur 事件。
  	 $("#username").blur(function(){
  		validateName();
  	 });
  	 
  	 //当“用户名”获得焦点时，发生 focus 事件。
  	 $("#username").on('focus',function(e){
  		$("#username_error").hide();
  		$("#user_error").hide();
  	 });
	
	
    /**用户名称校验*/
    function validateName(){
        var flag = false;
        var message="";
        var realName =trim($("#username").val()) ;
        if(realName ==""){
            message = "用户名不能为空";   
        }else{
         	flag = true;
        }
        if(!flag){
        	$("#username_error").html(message).show();
        }else{
        	$("#username_error").html(message).hide();
        }
        return flag;
     }
	
	
	
    //当“用户名”失去焦点时发生 blur 事件。
 	 $("#passcode").blur(function(){
 		validateCode();
 	 });
 	 
 	 //当“用户名”获得焦点时，发生 focus 事件。
 	 $("#passcode").on('focus',function(e){
 		$("#passcode_error").hide();
 		$("#user_error").hide();
 	 });
	
	
   /**验证码称校验*/
   function validateCode(){
       var flag = false;
       var message="";
       var passcode =trim($("#passcode").val()) ;
       if(passcode ==""){
           message = "验证码不能为空";   
       }else{
        	flag = true;
       }
       if(!flag){
       	  $("#passcode_error").html(message).show();
       }else{
       	  $("#passcode_error").html(message).hide();
       }
       return flag;
    }
   

   function loginForm(){
       var saveAdminName = validateName();
       var saveAdminvailPwd = vailPwd();
       var saveAdminCode = validateCode();
       return saveAdminName&saveAdminvailPwd&saveAdminCode;
   }
   
   /** "登录" 触发的事件*/
   $("#loginBtn").click(function(){
		if(loginForm()){
			 $("#loginForm").submit();
		}
      });  
		
});
</script>

</body>
</html>
