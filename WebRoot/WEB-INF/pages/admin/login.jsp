<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/18 0018
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="renderer" content="webkit">
  <title>后台管理-管理员登录</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
  <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
  <script src="${pageContext.request.contextPath}/js/respond.js"></script>
  <script src="${pageContext.request.contextPath}/js/admin.js"></script>
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
        <a href="http://www.souvc.com" target="_blank"><img src="${pageContext.request.contextPath}/images/logo.png" class="radius" alt="后台管理系统" /></a>
      </div>
      <br />
      <br />
      <form action="${pageContext.request.contextPath}/admin/dologin" method="post">
        <div class="panel">
          <div class="panel-head"><strong>登录后台管理系统</strong></div>
          <div class="panel-body" style="padding:30px;">
            <div class="form-group">
              <div class="field field-icon-right">
                <input type="text" class="input" name="username" placeholder="登录账号" data-validate="required:请填写账号,length#>=5:账号长度不符合要求" />
              </div>
            </div>
            <div class="form-group">
              <div class="field field-icon-right">
                <input type="password" class="input" name="password" placeholder="登录密码" data-validate="required:请填写密码,length#>=6:密码长度不符合要求" />
              </div>
            </div>
            <div class="form-group">
              <div class="field field-icon-right">
                <input type="text" class="input" name="passcode" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                <img src="${pageContext.request.contextPath}/images/passcode.jpg" width="80" height="32" class="passcode" />
              </div>
            </div>
          </div>
          <div class="panel-foot text-center">
            <button class="button button-block bg-main text-big">立即登录后台</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
