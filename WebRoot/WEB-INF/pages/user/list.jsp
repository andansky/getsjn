<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/20 0020
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="renderer" content="webkit">
  <title>后台管理-用户管理</title>
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
  <form method="post">
    <div class="panel admin-panel">
      <div class="panel-head"><strong>用户列表</strong></div>
      <div class="padding border-bottom">
        <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
        <input type="button" class="button button-small border-green" value="添加用户" />
        <input type="button" class="button button-small border-yellow" value="批量删除" />
        <input type="button" class="button button-small border-blue" value="回收站" />
      </div>
      <table class="table table-hover">
        <tr><th width="60">选择</th><th width="*">用户名</th><th width="*">昵称</th><th width="100">注册时间</th><th width="100">操作</th></tr>
        <c:forEach  items="${list}" var="p">
          <tr><td><input type="checkbox" name="id" value="${p.id}" /></td><td>${p.username}</td><td>${p.nickname}</td><td>${p.create_date}</td><td><a class="button border-blue button-little" href="#">修改</a> <a class="button border-yellow button-little" href="#" onclick="{if(confirm('确认删除?')){return true;}return false;}">删除</a></td></tr>
        </c:forEach>
      </table>
      <div class="panel-foot text-center">
        <ul class="pagination"><li><a href="#">上一页</a></li></ul>
        <ul class="pagination pagination-group">
          <li><a href="#">1</a></li>
          <li class="active"><a href="#">2</a></li>
        </ul>
        <ul class="pagination"><li><a href="#">下一页</a></li></ul>
      </div>
    </div>
  </form>
</div>
</body>
</html>
