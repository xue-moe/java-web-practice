<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="<%=path %>">人力资源管理系统</a>
            </div>
        </div><!-- /.container-fluid -->
    </nav>


    <div class="row">
        <div class="col-md-3">
            <div class="list-group">
                <a href="<%=path %>" class="list-group-item active">
                    首页
                </a>
                <a href="<%=path %>/staffInfoSave" class="list-group-item">添加员工信息</a>
                
                <a href="<%=path %>/staffInfoList" class="list-group-item">员工信息列表</a>

            </div>
        </div>
        <div class="col-md-9">
            <div class="jumbotron">
                <h1>慧心人力资源管理系统</h1>
                <p>人力资源公司为了提高管理效率，开发出了一套B/S结构人力资源管理系统，对员工信息进行管理。</p>
                <p><a class="btn btn-primary btn-lg" href="<%=path %>" role="button">人力资源管理系统</a></p>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>