<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">Shangchou.com - crowdfunding platform for creative products</a></div>
        </div>
      </div>
    </nav>

    <div class="container">
	
      <form id="loginForm" action="${APP_PATH }/doLogin.do" method="POST" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" name="loginacct" id="floginacct" value="superadmin" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" name="userpswd" id="fuserpswd" value="123" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select class="form-control" name="type" id="ftype">
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
		  </div>
		  ${exception.message }  
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="reg.html">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${APP_PATH}/jquery/layer/layer.js"></script>
    <script>
    function dologin() {
    	
    	//$("#loginForm").submit();
    
    	var floginacct = $("#floginacct");
    	var fuserpswd = $("#fuserpswd");
    	var ftype = $("#ftype");
    	
    	//判断用户名是否为空
    	if($.trim(floginacct.val()) == ""){
    		//alert("用户名不能为空");
    		layer.msg("用户名不能为空",{time:1000,icon:5,shift:6},function(){
    			floginacct.val("");
        		floginacct.focus(); //获取鼠标焦点	
    		});
    		return false;
    	}
    	
    	//判断密码是否为空
    	if($.trim(fuserpswd.val()) == ""){
    		//alert("密码不能为空和空格");
    		layer.msg("密码不能为空和空格",{time:1000,icon:5,shift:6},function(){
        		fuserpswd.val("");
        		fuserpswd.focus(); //获取鼠标焦点
    		});
    		return false;
    	}
    	
    	var loadingIndex = -1;
    	$.ajax({
    		url : "${APP_PATH}/doLogin.do",
    		type : "POST",   		
    		data : {
    			loginacct : floginacct.val(),
    			userpswd : 	fuserpswd.val(),
    			type : ftype.val()
    		},
    		
    		beforeSend:function(){
    			loadingIndex = layer.msg("处理中",{icon:6});
    			//一般做表单验证
    			return true;
    		},
    		
    		success:function(result){ //{"success":true}或 {"success":false,"message":"登录失败"}
    			layer.close(loadingIndex);
    			if(result.success){
    				//alert("ok");
    				//跳转主页面
    				window.location.href="${APP_PATH}/main.htm";
    			}else{
    				layer.msg(result.message,{time:1000,icon:5,shift:6});
    				//alert("not ok");
    			}
    		},
    		
    		error:function(){
    			//alert("error");
    			layer.msg("登录失败,请检查",{time:1000,icon:5,shift:6});
    		}
    		
    		
    	});
    	
    	
       /*  var type = $(":selected").val();
        if ( type == "user" ) {
            window.location.href = "main.html";
        } else {
            window.location.href = "index.html";
        } */
        
    }
    </script>
  </body>
</html>