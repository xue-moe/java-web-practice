<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.yuki.dao.*"%>
<%@page import="com.yuki.staff.*"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>

<%String path = request.getContextPath();%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>员工信息列表</title>

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
                <a href="<%=path %>/staffInfoSave" class="list-group-item">添加员工信息</a>
                
                <a href="<%=path%>/staffInfoList" class="list-group-item  active">员工信息列表</a>

            </div>
        </div>
        <div class="col-md-9">
        <table class="table">
             <thead>
                <tr>
                    <th>员工编号</th>
                    <th>员工姓名</th>
                    <th>员工性别</th>
                    <th>员工生日</th>
                    <th>员工手机</th>
                    <th> 操 作</th>
                </tr>
                </thead>
                <tbody>
                <%List<Staff> list=new Staff_Jdbc().select();
            		for(Staff u:list){
            		%>
            		<tr>
            		<td><%=u.getId() %></td>
            		<td><%=u.getName() %></td>
            		<td><%=u.getGender() %></td>
            		<td><%=u.getBirthday() %></td>
            		<td><%=u.getPhone() %></td>
            		<td>
            		
            		<a href="<%=path %>/staffInfoUpdate?id=<%=u.getId()%>">更新</a>
            		<a style="margin-left:10px; color:red" href="<%=path %>/staffInfoDelete?id=<%=u.getId() %>">删除</a>
            		
            		</td>
            		</tr>
            		<%} %>
                </tbody>
               </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>