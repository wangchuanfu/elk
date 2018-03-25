<%@ page language="java" pageEncoding="UTF-8"%> 
<%
if(request.getSession().getAttribute("USER") == null) {
	response.sendRedirect("../login.jsp");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" charset="utf-8" src="/resource/js/jquery-2.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/resource/js/layer/layer.js"></script>
<title>登陆</title>
</head>

<body>
登录成功了，你可以修改密码或者<input id="logout" name="logout" type="button" value="退出登录"><br/>
------------------------------------<br/><br/>
<form id="ep_form" name="ep_form" method="post" >
新密码：<input id="newPassword" name="newPassword" type="password">
<input id="ep" name="ep" type="button" value="修改密码">
</form>

</body>
<script>
$(function(){
	
	$("#logout").click(function(){
	    window.location.href = '/user/logout.do';
	});
	
	$("#ep").click(function(){
	    var vo = $("#ep_form").serialize();
	    var url = '/user/editPassword.do';
	 	if ($("#ep_form #newPassword").val() == '') {
    		layer.msg('新密码不能为空');
    		return false;
    	}
	    
	    $.ajax({
	    	type:'post',
	    	url:url,
	    	data:vo,	
	    	cache:false,
	    	async:true,
	    	success:function(json){
	    		layer.msg(json.data);
	    	},
	        error:function(){
	        	layer.msg("执行错误！", 8);
	        }
	    });
		
	});
	
});

</script>
</html>
