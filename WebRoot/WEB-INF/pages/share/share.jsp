<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/9 0009
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zh-cn">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="renderer" content="webkit">
  <title>分享页面</title>
  <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css">
  <link type="image/x-icon" href="images/logo.ico" rel="shortcut icon" />
  <link href="images/logo.ico" rel="bookmark icon" />
</head>
<body>
<a href="http://qsbk.bluestorm.top">
  <div class="top">
    用客户端打开
  </div>
</a>
<h3 class="title">${article.title}</h3>
<span class="date">${article.create_date}</span>
<div class="content">
  ${article.content}
</div>
<div class="list">
<!--  <h3>热门文章</h3>
  <ul>
    <a href="#">
      <li>
        <img src="${pageContext.request.contextPath}/images/logo.png">
        <h5>习近平谈扶贫反复强调“精准”,习近平谈扶贫反复强调“精准”</h5>
      </li>
    </a>
    <a href="#">
      <li>
        <img src="${pageContext.request.contextPath}/images/logo.png">
        <h5>习近平谈扶贫反复强调“精准”,习近平谈扶贫反复强调“精准”</h5>
      </li>
    </a>
    <a href="#">
      <li>
        <img src="${pageContext.request.contextPath}/images/logo.png">
        <h5>习近平谈扶贫反复强调“精准”,习近平谈扶贫反复强调“精准”</h5>
      </li>
    </a>
    <a href="#">
      <li>
        <img src="${pageContext.request.contextPath}/images/logo.png">
        <h5>习近平谈扶贫反复强调“精准”,习近平谈扶贫反复强调“精准”</h5>
      </li>
    </a>
  </ul>
</div>
  -->
<div class="download" id="download">
  <a href="http://qsbk.bluestorm.top">立即下载手机客户端</a>
</div>

<script type="text/javascript">
  $(window).scroll(function(){
    var scrollTop = $(this).scrollTop();
    var scrollHeight = $(document).height();
    var windowHeight = $(this).height();
    var download=document.getElementById('download')
    if(scrollTop + windowHeight == scrollHeight){
      download.style.display="block";
    }else{
      download.style.display="none";
    }
  });


</script>
</body>
</html>
