<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 94993
  Date: 2017/10/16
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
</head>
<style type="text/css">
    body{
        margin: 0px;
        height: 100%;
        width: 100%;
        background-image: url("../../resource/img/bg-2.jpg");
        background-size: cover;
    }
    #login{
        width: 450px;
        height: 300px;
        position: relative;
        margin: 168px auto;
        background: rgba(0, 0, 0, 0.37);
        border-radius:4px
    }

    #login div > input[type='text'],input[type='password']{
        margin-top: 20px;
        margin-bottom: 15px;
        width: 350px;
        line-height: 40px;
        border-radius: 4px;
        border: none;
        font-size: 12px;
        text-indent: 2em
    }

    #login div:first-child{
        margin: 0 50px;
    }

    .remeber{
        margin: 0 50px;
        clear: both;
        content: '';
        overflow: hidden;
        zoom: 1;
        color: #fff;
    }
    .remeber input{
        float: left;
    }
    .remeber a{
        float: right;
    }

    a.btn{
        width: 249px;
        height: 40px;
        display: inline-block;
        border-radius: 4px;
        margin: 19px 22%;
        background-color: #00acac;
        text-align: center;
        line-height: 40px;
        font-size: 20px;
        text-decoration: none;

    }
    button{
        background-color: #008a8a;
        padding: 10px 16px;
        font-size: 16px;
        color: white
    }
    a{
        color: #fff;
        text-decoration:none;
    }
    ul{
        margin-top: -130px;
    }
    ul img{
        height: 70px;
        width: 125px;
    }
    ul li{
        float: right;
        margin-left: 6px;
        list-style-type: none;
    }
    .bg{
        border: 2px solid white;
    }
    #error{
        color: red;
        font-size: 14px;
        text-align: center;
    }
</style>
<body>

<form method="post" action="/login" id="form">
<div id="login">
    <div>
        <input type="text" name="userName" placeholder="用户名">
        <input type="password" name="passWord" placeholder="密码">
    </div>
    <div class="remeber"><input type="checkbox" name="remeberMe" value="0">记住我<a href="#">忘记密码</a></div>

    <a href="javascript:;" class="btn" onclick="login()">登录</a>

    <div id="error"><c:if test="${not empty msg}">${msg}</c:if></div>
</div>
</form>

<ul>
    <li><img src="../../resource/img/bg-1.jpg" content="bg-1.jpg"></li>
    <li class="bg"><img src="../../resource/img/bg-2.jpg" content="bg-2.jpg"></li>
</ul>

</body>
</html>
<script src="../../resource/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
    $("li").click(function () {

        $(this).siblings().each(function () {
            if($(this).attr("class") == 'bg')
                $(this).removeClass("bg");
        });

        $(this).addClass("bg");
        var img = $(this).children().eq(0).attr("content");
        console.info(img);

        var url = "url(resource/img/"+img+")";
        $("body").css("background-image",url);
    });
//  登录
    function login() {
        if($("input[name='userName']").val().length==0){
            alert("用户名不能为空！");
        }else if($("input[name='passWord']").val().length==0){
            alert("密码不能为空！");
        }else {
            $("#form").submit();
        }
    }
</script>