<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/log.css">
    <title>129聊天空间</title>
    <link rel="icon" href="/images/icon1.ico" sizes="any">
</head>
<script src="/js/jquery-3.4.1.min.js" language="javascript"></script>
<script type="text/javascript">
    function dologin(){
         var name = $("#username").val();
            var password = $("#password").val();
            console.log(name);
            if(name == ""){
                alert("请输入登录名！");
                return;
            }
            if(password == ""){
                alert("请输入密码！");
                return;
            }

    var key=0;
    $.ajax({
                async: false,
             	type : "POST",
             	url  : "/checkLogin",
             	data :$('#form-log').serialize(),
             	success : function(data) {
             	    if(data=="success"){
             		    alert("登录成功");
             		    key=1;

             		}
             		else{
             		    alert("登录失败");
             		    key=0;
             		}
             	}
             });
    if(key==1){
        location.href="web/chat.html";
        window.event.returnValue=false;
    }
    else{

    }
	}
</script>
<body>
<div class="container"><!--该层主要用于设定毛玻璃位置以及对毛玻璃的溢出进行限制，即作为毛玻璃的容器-->
    <div class="glass"></div><!--该层用于模糊化-->
   <div class="log">
        <h3 style="color:white">用户登录</h3>
        <hr color="white" size="2px">
        <br><br>
        <form id="form-log" role="form" method="post">
            <label style="color:white">&emsp;&emsp;用户名：</label>
            <input id="username" name="username" type="text" placeholder="   请输入用户名" style="width: 200px;height: 25px">
            <br><br>
            <label style="color:white">&emsp;&emsp;密码：&nbsp;&thinsp;&thinsp;&thinsp;</label>
            <input id="password" name="password" id="p1" type="text" placeholder="   请输入密码" style="width: 200px;height: 25px">
            <br><br>
            <div class="log1">
                <button id="btn-login" type="submit" class="buttoncss" onclick="dologin()">登 录</button>
            </div>
        </form>
    </div><!--该层是内容层-->
</div>
</body>
</html>