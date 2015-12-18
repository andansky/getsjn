<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/19 0019
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="zh-cn">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="renderer" content="webkit">
  <title>后台管理-内容管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
  <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
  <script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
  <script src="${pageContext.request.contextPath}/js/respond.js"></script>
  <script src="${pageContext.request.contextPath}/js/admin.js"></script>
  <link type="image/x-icon" href="${pageContext.request.contextPath}/images/logo.ico" rel="shortcut icon" />
  <link href="${pageContext.request.contextPath}/images/logo.ico" rel="bookmark icon" />
</head>

<body>
<jsp:include page="../layout/head.jsp"/>
<div class="admin">
  
  <form method="post"  action="${pageContext.request.contextPath}/content/list" method="post" id="userBasePageId">
    <input type="hidden" id="page" name="page" value="${page}"/>
    <div class="panel admin-panel">
      <div class="panel-head">
         <strong>文章列表</strong>
      </div>
      <div class="padding border-bottom">
        <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
        <a href="${pageContext.request.contextPath}/content/add" type="button" class="button button-small border-green">添加文章</a>
        <input type="button" class="button button-small border-yellow" id="deleteSelectForm" value="批量删除" />
        <input type="button" class="button button-small border-yellow float-right" value="查找" style="margin-left: 10px" id="searchButtonId" />
        <input type="text" id="searchtext"  class="button  float-right" name="searchinput" width="30%" size="50" placeholder="文章标题" value=""/>
      </div>
      <table class="table table-hover">
        <tr>
	        <th width="60">选择</th>
	        <th width="60">序号</th>
	        <th width="120">类型</th>
	        <th width="*">标题</th>
	        <th width="100">作者</th>
	        <th width="100">操作</th>
        </tr>
        <c:forEach varStatus="status" items="${list}" var="p">
            <input id="articleId" type="hidden"/>
	        <tr>
	        <td><input type="checkbox" name="id" value="${p.id}" /></td>
	        <td>${status.index+1 }</td>
	        <td>${p.type}</td>
	        <td><a href="http://souvc.com/share/share/${p.id}">${p.title}</a></td>
	        <td>${p.autor}</td>
	        <td>
	            <a class="button border-blue button-little" href="${pageContext.request.contextPath}/content/update/${p.id}">修改</a> 
	            <a class="button border-yellow button-little" href="JavaScript:;"  data="${p.id}" onClick="delcfm('${p.id}')">删除</a>
	        </td>
	        </tr>
        </c:forEach>
      </table>
      <div class="panel-foot text-center">
         <!-- 
        <ul class="pagination">
             <li><a href="<%=basePath%>content/list?page=1" id="backPage">首页</a></li>
        </ul>
         -->
        <ul class="pagination">
        <c:choose>
	        <c:when test="${page eq 1||page eq 0||page eq -1}">
	             <li><a href="javascript:;" id="backPage">上一页</a></li>
	        </c:when>
        	<c:otherwise>
        	     <li><a href="<%=basePath%>content/list?page=${page-1}" id="backPage">上一页</a></li>
        	</c:otherwise>
        </c:choose>
             
        </ul>
        <!--  
        <ul class="pagination pagination-group">
          <li><a href="#">1</a></li>
          <li class="active"><a href="#">2</a></li> 
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
        </ul>
        -->
        <ul class="pagination">
         <c:choose>
	        <c:when test="${page eq -1}">
	            <li><a href="javascript:;" id="nextPage">下一页</a></li>
	        </c:when>
	        <c:otherwise>
        	    <li><a href="<%=basePath%>content/list?page=${page+1}" id="nextPage">下一页</a></li>
        	</c:otherwise>
        </c:choose>
        </ul>
          <!-- 
         <ul class="pagination">
             <li><a href="<%=basePath%>content/list" id="backPage">尾页</a></li>
        </ul>
        -->
      </div>
    </div>
     
  </form>
</div>

<script type="text/javascript">

	//点击查询按钮
	$("#searchButtonId").on("click",function(){
		$("#userBasePageId").submit();
	});
	
	//单条文章删除
	function delcfm(orderId) { 
        if (confirm("确认要删除该文章么？")) { 
        	var requestUrl ="<%=basePath%>content/delete/";
    		$.ajax( {    
       			url:requestUrl,// 跳转到 action    
       			data:{"orderId":orderId},    
       			type:'post',    
       			cache:false,    
       			dataType:'json',    
       			success:function(data) {    
           			if(data.flag ==true ){    
               			window.location.reload();    
           			}else{    
           				
           			}    
        			},    
        			error : function() {    
        			   alert("删除失败！");  
        			}    
    		}); 
    		
        } 
	 } 
	
	
	//批量删除文章
	$("#deleteSelectForm").on("click",function(){
		 var requestUrl ="<%=basePath%>content/deleteSelect/";
		 $('#userBasePageId').attr("action", requestUrl);
		 $('#userBasePageId').submit();
		 /**
		 $("#userBasePageId").ajaxSubmit({
			type: "POST",
			dataType: "json",
		    success: function(data){
		     	if(data.flag ==true){
					alert("删除成功");
					window.location.reload(); 
				}else if(data.flag ==false){
					window.location.reload(); 
				}else{
					alert("删除失败");
		    	}
			    }
		    });
		 */
	});
	
</script>

</body>
</html>
