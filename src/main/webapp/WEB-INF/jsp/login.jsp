<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆</title>
    <!-- zui -->
    <link href="zui/css/zui.min.css" rel="stylesheet">
    <link href="css/public.css" type="text/css" rel="stylesheet">
</head>
<body>

<div class="container">

    <div class="row" style="height: auto;margin-top: 100px">
        <div class="col-md-4 col-md-offset-4" >
            <h4 id="erro"></h4>
            <form>
                <div class="form-group input-control has-icon-left">
                    <input id="userName" name="userName" type="text" class="form-control" placeholder="账    号">
                    <label for="userName" class="input-control-icon-left"><i class="icon icon-user "></i></label>
                </div>
                <div class="form-group input-control has-icon-left">
                    <input id="passWord" name="passWord" type="text" class="form-control" placeholder="密    码">
                    <label for="passWord" class="input-control-icon-left"><i class="icon icon-key "></i></label>
                </div>
                <div class="form-group input-control has-icon-left">
                    验证码：<input type="text" name="vcode" id="vcode"/>
                    <img alt="验证码" src="/demo/getGifCode">
                </div>
                <div class="form-group input-control has-icon-left">
                    <P><input type="checkbox" name="rememberMe"  id="rememberMe" />记住我</P>
                </div>
                <%--<button id="ajaxLogin" class="btn btn-primary">提交</button>--%>
                <input type="button" id="ajaxLogin" class="btn btn-primary" value="登录" />
            </form>
        </div>
    </div>
</div>

<script src="jquery/jquery-3.2.1.min.js"></script>
<!-- ZUI Javascript组件 -->
<script src="zui/js/zui.min.js"></script>
<script>
    $(function(){
        $("#ajaxLogin").click(function() {
            var userName = $("#userName").val();
            var passWord = $("#passWord").val();
            var vcode = $("#vcode").val();
            var rememberMe =$('#rememberMe').is(':checked');
            $.post("/demo/ajaxLogin", {
                "userName" : userName,
                "passWord" : passWord,
                "vcode" : vcode,
                "rememberMe" : rememberMe
            }, function(result) {
                if (result.status == 200) {
                    location.href = "/demo/grid";
                } else {
                    $("#erro").html(result.message);
                }
            });
        });
    });
</script>
</body>
</html>