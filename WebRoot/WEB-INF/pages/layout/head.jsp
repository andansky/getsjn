<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="lefter">
    <div class="logo">
        <a href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="后台管理系统" /></a>
    </div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
        <div class="admin-navbar">
            <span class="float-right">
            	<a class="button button-little bg-main" href="http://souvc.com/" target="_blank">前台首页</a>
                <a class="button button-little bg-yellow" href="${pageContext.request.contextPath}/admin/logout">注销登录</a>
            </span>
            <ul class="nav nav-inline admin-nav">
                <li class="home"><a href="${pageContext.request.contextPath}/" class="icon-home"> 开始</a>
                    <ul>
	                    <li><a href="${pageContext.request.contextPath}/user/">会员管理</a></li>
	                    <li><a href="${pageContext.request.contextPath}/column/">栏目管理</a></li>
	                    <li><a href="${pageContext.request.contextPath}/content/list?page=1">内容管理</a></li>
	                    <li><a href="${pageContext.request.contextPath}/comment/">评论管理</a></li>
	                    <li><a href="${pageContext.request.contextPath}/system/">系统管理</a></li>
                    </ul>
                </li>
                <li class="item"><a href="${pageContext.request.contextPath}/user/" class="icon-user"> 会员管理</a>
                    <ul>
	                    <li><a href="${pageContext.request.contextPath}/user/add/">添加会员</a></li>
	                    <li><a href="${pageContext.request.contextPath}/user/">会员管理</a></li>
                    </ul>
                </li>
                <li class="item"><a href="${pageContext.request.contextPath}/column/" class="icon-th-list"> 栏目管理</a>
                    <ul>
	                    <li><a href="${pageContext.request.contextPath}/column/add/">添加栏目</a></li>
	                    <li><a href="${pageContext.request.contextPath}/column/">栏目管理</a></li>
                    </ul>
                </li>
                <li class="item"><a href="${pageContext.request.contextPath}/content/list?page=1" class="icon-file-text"> 内容管理</a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/content/add/">添加内容</a></li>
                        <li><a href="${pageContext.request.contextPath}/content/list?page=1">内容管理</a></li>
                    </ul>
                </li>
                <li class="item"><a href="${pageContext.request.contextPath}/comment/" class="icon-comments"> 评论管理</a>
                    <ul><li><a href="#">评论管理</a></li></ul>
                </li>
                <li class="item"><a href="${pageContext.request.contextPath}/system/" class="icon-cog"> 系统管理</a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/system/">全局设置</a></li>
                        <li><a href="${pageContext.request.contextPath}/system/sysset/">系统设置</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，<%=request.getSession().getAttribute("username")%>，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="#" class="icon-home"> 开始</a></li>
                <li>后台首页</li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $(".admin-nav .item a").each(function(){
            var str=location.search;
            $this = $(this);
            if($this[0].href==("http://"+location.host+"/")){
                $this.parent().addClass("active-par");
            }else{
                if(String(window.location).indexOf($this[0].href)>=0){
//                &&$this[0].href!=("http://"+location.host+"/")
                    $this.parent().addClass("active-par");
                    $(".admin-nav .item a").className("");
                }else
                {
                    $this.parent().addClass("");
                }
            }
        });

        $(".admin-nav .home a").each(function(){
            var str=location.search;
            $this = $(this);
            if($this[0].href==("http://"+location.host+"/")){
                $this.parent().addClass("active-par");
            }else{
                if(String(window.location).indexOf($this[0].href)>=0){
//                &&$this[0].href!=("http://"+location.host+"/")
                    $this.parent().addClass("active-par");
                }else
                {
                    $this.parent().addClass("");
                }
            }
        });

//        $(".admin-nav li ul li a").each(function(){
//            $this = $(this);
//            if($this[0].href==String(window.location)){
//
//                $this.parent().addClass("active-chil");
//            }  else
//            {
//                $this.parent().addClass("");
//            }
//        });

    });

    function GetUrlRelativePath()
    {
        var domain = location.host;
        var url = document.location.toString();
        var arrUrl = url.split("//");

        var start = arrUrl[1].indexOf("/");
        var relUrl = arrUrl[1].substring(start);//stop省略，截取从start开始到结尾的所有字符

        if(relUrl.indexOf("?") != -1){
            relUrl = relUrl.split("?")[0];
        }
        return "http://"+domain+relUrl;
    }

</script>