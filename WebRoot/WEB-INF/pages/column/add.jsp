<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/20 0020
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="renderer" content="webkit">
  <title>后台管理-添加栏目</title>
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
<jsp:include page="../layout/head.jsp"/>
<div class="admin">
  <div class="panel border-blue">
    <div class="panel-head  border-sub bg-blue text-white">
      <strong>添加栏目</strong>
    </div>
    <div class="panel-body">
      <form action="${pageContext.request.contextPath}/column/create/" class="form-x" method="post">
        <div class="form-group">
          <div class="label">
            <label for="name">栏目名称</label>
          </div>
          <div class="field">
            <input name="name" class="input" id="name" type="text" size="50" placeholder="栏目名称">
          </div>
        </div>
        <div class="form-button">
          <button class="button" type="submit">提交</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
