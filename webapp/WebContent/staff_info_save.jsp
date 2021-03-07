<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加员工信息</title>

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
                <a href="<%=path %>" class="list-group-item">
                    首页
                </a>
                <a href="<%=path %>/staffInfoSave" class="list-group-item active">添加员工信息</a>
                
                <a href="<%=path %>/staffInfoList" class="list-group-item">员工信息列表</a>

            </div>
        </div>
        <div class="col-md-9">
            <form action="<%=path%>/staffInfoSave" method="post">
			  <div class="form-group">
			    <label>姓名</label>
			    <input type="text" name="name" class="form-control" placeholder="请输入员工姓名">
			  </div>
			  <div class="form-group">
			    <label>性别</label>
			    <input type="gender" name="gender" class="form-control" placeholder="请输入员工性别">
			  </div>
			  <div class="form-group">
			    <label>生日</label>
			    <input type="date" name="birthday" class="form-control" placeholder="请输入员工生日">
			  </div>
			  <div class="form-group">
			    <label>电话</label>
			    <input type="phone" name="phone" class="form-control" placeholder="请输入员工手机号">
			  </div>
			  <button type="submit" class="btn btn-primary">保存</button>
			  <button type="reset" class="btn btn-default">重置</button>
			</form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>