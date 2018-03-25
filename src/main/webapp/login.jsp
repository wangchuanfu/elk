<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" charset="utf-8" src="/resource/js/jquery-2.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/resource/js/layer/layer.js"></script>
<title>登陆</title>
</head>
<body>
<form id="login_form" method="post" >
用户ID：<input id="id" name="id" type="text"/>
密码：<input id="password" name="password" type="password"/>
<input id="login_submit" name="but" type="button" value="登陆"/>
</form>
</body>
<script>
$(function(){
	$("#login_submit").click(function(){
	    var vo = $("#login_form").serialize();
	    var url = '/user/login.do';
	    
	    //前端js校验
	    if ($("#login_form #id").val() == '') {
    		layer.msg('用户ID不能为空');
    		return false;
    	}
	 	
	 	if ($("#login_form #password").val() == '') {
    		layer.msg('登录密码不能为空');
    		return false;
    	}
	    
	    $.ajax({
	    	type:'post',
	    	url:url,
	    	data:vo,	
	    	cache:false,
	    	async:true,
	    	success:function(json){
	    		if(json.result=='0'){
	    			location.href = '/pages/index.jsp';
	    		}else{
	    			layer.msg(json.data);
	    		}
	    	},
	        error:function(){
	        	layer.msg("执行错误！", 8);
	        }
	    });
		
	});
	
});

</script> 
</html>
