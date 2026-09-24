<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>暂时无法完成 · 员工档案管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body>
<header class="topbar"><a class="brand" href="${pageContext.request.contextPath}/"><span class="brand-mark">H</span> 员工档案管理 <span>HR</span></a><a class="button button-quiet button-small" href="${pageContext.request.contextPath}/">返回首页</a></header>
<main class="page-shell error-shell"><section class="panel error-panel"><span class="error-mark">!</span><p class="eyebrow">请求未完成</p><h1>出了点小问题</h1><p class="muted"><c:out value="${errorMessage}"/></p><div class="error-actions"><a class="button button-dark" href="${pageContext.request.contextPath}/">返回首页</a><a class="text-link" href="${pageContext.request.contextPath}/staffInfoList">查看员工列表 →</a></div></section></main>
</body>
</html>
