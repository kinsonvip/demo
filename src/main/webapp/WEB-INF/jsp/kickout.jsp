<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>被踢出</title>
</head>
<body>
被踢出 或则在另一地方登录，或已经达到此账号登录上限被挤掉。
<input type="button" id="login" value="重新登录" />
</body>
<script src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $("#login").click(function(){
        window.open("${pageContext.request.contextPath}/login");
    });
</script>
</html>