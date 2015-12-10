<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/19 0019
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>后台管理-添加文章</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
<script src="${pageContext.request.contextPath}/js/respond.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
<!--
    ueditor编辑器相关js
  -->
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js">
	
</script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<link type="image/x-icon" href="http://www.pintuer.com/favicon.ico"
	rel="shortcut icon" />
<link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
</head>

<body onload="init()">
	<jsp:include page="../layout/head.jsp" />
	<div class="admin">
		<div class="panel border-blue">
			<div class="panel-head  border-sub bg-blue text-white">
				<strong>添加文章</strong>
			</div>
			<div class="panel-body">
				<form action="${pageContext.request.contextPath}/content/create"
					class="form-x" method="post" onsubmit="return formsubmit()">
					<div class="form-group">
						<div class="label">
							<label for="title">文章标题</label>
						</div>
						<div class="field">
							<input name="title" class="input" id="title" type="text"
								size="50" placeholder="文章标题">
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>类型</label>
						</div>
						<div class="field">
							<div class="button-group radio">
								<label class="button active"> <input name="type"
									type="radio" checked="checked" value="txt"
									onchange="uploadImg(this)"><span
									class="icon icon-file-text-o"></span> 文字
								</label> <label class="button"> <input name="type" type="radio"
									value="img" onchange="uploadImg(this)"><span
									class="icon icon-file-image-o"></span> 图文
								</label> <label class="button"> <input name="type" type="radio"
									value="play" onchange="uploadImg(this)"><span
									class="icon icon-file-video-o"></span> 视频
								</label> <label class="button"> <input name="type" type="radio"
									value="ad" onchange="uploadImg(this)"><span
									class="icon icon-share-alt-square"></span> 推广
								</label>
							</div>
						</div>
					</div>
					<div class="form-group" id="uploaddiv">
						<div class="label">
							<label> 预览图片</label>
						</div>
						<div class="field">
							<input type="file" id="fileimg" name="fileimg" size="100"
								onchange="document.getElementById('img').value=getFullPath(this);"
								class="button input-file" accept=".jpg,.gif,.png,.jpeg">
							<input type="hidden" id="img" name="img" value="">
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>所属栏目</label>
						</div>
						<div class="field">
							<select id="select" name="column" class="input">
								<option value="0">请选择栏目</option>
								<c:forEach items="${list}" var="cl">
									<option value="${cl.id}">${cl.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="label">
							<label> 是否推荐</label>
						</div>
						<div class="field">
							<div class="button-group radio">
								<label class="button active"> <input name="bot"
									type="radio" checked="checked" value="1" /><span
									class="icon icon-check"></span> 是
								</label> <label class="button"> <input name="bot" type="radio"
									value="0" /><span class="icon icon-times"></span> 否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label> 文章内容</label>
						</div>
						<div class="field">
							<textarea name="content" id="editor"
								style="height: 500px; width: 100%;"></textarea>
						</div>
					</div>
					<div id="temp"></div>
					<div class="form-button">
						<button class="button" type="submit">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function initTitle(){
			var title=$("#title");
			title.blur(function(){
				 $.ajax( {  
				        type : 'post',  
				        contentType : 'application/x-www-form-urlencoded; charset=UTF-8',  
				        url : 'checkObjectExists/'+encodeURI(encodeURI(title.val())),  
				        dataType : 'json',  
				        success : function(data) {
				          if(data.status==true){
				        	  alert("文章已存在,请不要重复添加！");
				        	  title.css("color","#FF0000");
				        	  title.focus();
				          }
				        } 
				      }); 
			});
		}	
	
	</script>
	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');
		function init() {
			$("#uploaddiv").hide();
			$("#temp").hide();
			initTitle();
		}
		//获取图片本地路径
		function getFullPath(obj) {
			if (obj) {
				//ie
				if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
					obj.select();
					return document.selection.createRange().text;
				}
				//firefox
				else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
					if (obj.files) {
						return obj.files.item(0).getAsDataURL();
					}
					return obj.value;
				}
				return obj.value;
			}
		}
		//表单验证
		function formsubmit() {

			if ($("#select").val() == 0) {
				alert("请选择所属栏目！");
				return false;
			}
			var temp = document.getElementsByName("type");
			var type;
			for (var i = 0; i < temp.length; i++) {
				if (temp[i].checked)
					type = temp[i].value;
			}
			if (type === "txt" || type === "img") {
				var html = ue.getContent();
				$("#temp").html(html);
				$("#img").val(getImgUrl());
			} else {
				var v = document.getElementById("fileimg").value;
				if (v == "") {
					alert("请先上传预览图！");
					return false;
				}
			}
			return true;
		}
		function getImgUrl() {
			var imgUrl = "";
			var divs = document.getElementById("temp");//获取该标签下的Img
			var imgs = divs.getElementsByTagName("img");
			for (var a = 0; a < imgs.length; a++) {
				imgUrl += imgs[a].getAttribute("src") + ',';
			}
			imgUrl = imgUrl.substring(0, imgUrl.length - 1);
			return imgUrl;
		}
		function uploadImg(tag) {
			var value = tag.getAttribute("value");
			if (value === "txt") {
				$("#uploaddiv").hide();
			} else if (value === "img") {
				$("#uploaddiv").hide();
			} else if (value === "play") {
				$("#uploaddiv").show();
			} else if (value === "ad") {
				$("#uploaddiv").show();
			}
		}

		function isFocus(e) {
			alert(UE.getEditor('editor').isFocus());
			UE.dom.domUtils.preventDefault(e)
		}
		function setblur(e) {
			UE.getEditor('editor').blur();
			UE.dom.domUtils.preventDefault(e)
		}
		function insertHtml() {
			var value = prompt('插入html代码', '');
			UE.getEditor('editor').execCommand('insertHtml', value)
		}
		function createEditor() {
			enableBtn();
			UE.getEditor('editor');
		}
		function getAllHtml() {
			alert(UE.getEditor('editor').getAllHtml())
		}
		function getContent() {
			var arr = [];
			arr.push("使用editor.getContent()方法可以获得编辑器的内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getContent());
			alert(arr.join("\n"));
		}
		function getPlainTxt() {
			var arr = [];
			arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getPlainTxt());
			alert(arr.join('\n'))
		}
		function setContent(isAppendTo) {
			var arr = [];
			arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
			UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
			alert(arr.join("\n"));
		}
		function setDisabled() {
			UE.getEditor('editor').setDisabled('fullscreen');
			disableBtn("enable");
		}

		function setEnabled() {
			UE.getEditor('editor').setEnabled();
			enableBtn();
		}

		function getText() {
			//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
			var range = UE.getEditor('editor').selection.getRange();
			range.select();
			var txt = UE.getEditor('editor').selection.getText();
			alert(txt)
		}

		function getContentTxt() {
			var arr = [];
			arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
			arr.push("编辑器的纯文本内容为：");
			arr.push(UE.getEditor('editor').getContentTxt());
			alert(arr.join("\n"));
		}
		function hasContent() {
			var arr = [];
			arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
			arr.push("判断结果为：");
			arr.push(UE.getEditor('editor').hasContents());
			alert(arr.join("\n"));
		}
		function setFocus() {
			UE.getEditor('editor').focus();
		}
		function deleteEditor() {
			disableBtn();
			UE.getEditor('editor').destroy();
		}
		function disableBtn(str) {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
				if (btn.id == str) {
					UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
				} else {
					btn.setAttribute("disabled", "true");
				}
			}
		}
		function enableBtn() {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
				UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
			}
		}

		function getLocalData() {
			alert(UE.getEditor('editor').execCommand("getlocaldata"));
		}

		function clearLocalData() {
			UE.getEditor('editor').execCommand("clearlocaldata");
			alert("已清空草稿箱")
		}
	</script>
</body>
</html>
