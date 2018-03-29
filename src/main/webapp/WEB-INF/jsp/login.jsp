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
            <form action="ajaxLogin" method="POST">
                <div class="form-group input-control has-icon-left">
                    <input id="userName" name="userName" type="text" class="form-control" placeholder="账    号">
                    <label for="userName" class="input-control-icon-left"><i class="icon icon-user "></i></label>
                </div>
                <div class="form-group input-control has-icon-left">
                    <input id="passWord" name="passWord" type="text" class="form-control" placeholder="密    码">
                    <label for="passWord" class="input-control-icon-left"><i class="icon icon-key "></i></label>
                </div>

                <button type="submit" class="btn btn-primary">提交</button>
            </form>
        </div>
    </div>
</div>

<script src="jquery/jquery-3.2.1.min.js"></script>
<!-- ZUI Javascript组件 -->
<script src="zui/js/zui.min.js"></script>
</body>
</html>