<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>员工档案 · 员工档案管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/"><span class="brand-mark">H</span> 员工档案管理 <span>HR</span></a>
    <nav><a class="nav-link" href="${pageContext.request.contextPath}/">概览</a><a class="nav-link active" href="${pageContext.request.contextPath}/staffInfoList">员工档案</a></nav>
    <a class="button button-primary button-small" href="${pageContext.request.contextPath}/staffInfoSave">＋ 添加员工</a>
</header>
<main class="page-shell">
    <div class="page-heading"><div><p class="eyebrow">员工管理</p><h1>员工档案</h1><p class="muted">查看、搜索和维护员工基本信息。</p></div><a class="button button-primary" href="${pageContext.request.contextPath}/staffInfoSave">＋ 添加员工</a></div>
    <c:if test="${not empty flashMessage}"><div class="notice"><c:out value="${flashMessage}"/></div></c:if>
    <section class="panel filter-panel">
        <form class="filter-form" action="${pageContext.request.contextPath}/staffInfoList" method="get">
            <label class="search-box"><span aria-hidden="true">⌕</span><input type="search" name="q" value="<c:out value='${keyword}'/>" placeholder="按姓名或手机号搜索"></label>
            <label class="filter-select"><span class="sr-only">性别</span><select name="gender"><option value="">所有性别</option><option value="男" <c:if test="${genderFilter eq '男'}">selected</c:if>>男</option><option value="女" <c:if test="${genderFilter eq '女'}">selected</c:if>>女</option></select></label>
            <button class="button button-dark" type="submit">搜索</button>
            <a class="button button-quiet" href="${pageContext.request.contextPath}/staffInfoList">重置</a>
        </form>
    </section>
    <section class="panel table-panel">
        <div class="table-heading"><div><h2>员工列表</h2><p class="muted">共 ${staffCount} 条记录</p></div></div>
        <div class="table-scroll">
            <table>
                <thead><tr><th>员工编号</th><th>姓名</th><th>性别</th><th>出生日期</th><th>手机号</th><th class="align-right">操作</th></tr></thead>
                <tbody>
                <c:forEach items="${staffList}" var="staff">
                    <tr>
                        <td class="id-cell">#<c:out value="${staff.id}"/></td>
                        <td><div class="person-cell"><span class="avatar"><c:out value="${staff.initial}"/></span><strong><c:out value="${staff.name}"/></strong></div></td>
                        <td><span class="gender-pill"><c:out value="${staff.gender}"/></span></td>
                        <td><c:out value="${staff.birthday}"/></td>
                        <td><c:out value="${staff.phone}"/></td>
                        <td class="align-right actions-cell"><a class="row-action" href="${pageContext.request.contextPath}/staffInfoUpdate?id=${staff.id}">编辑</a><form class="inline-form" action="${pageContext.request.contextPath}/staffInfoDelete" method="post" data-confirm-delete><input type="hidden" name="id" value="${staff.id}"><button class="row-action danger" type="submit">删除</button></form></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty staffList}"><tr><td colspan="6"><div class="empty-state"><span class="empty-icon">⌕</span><strong>没有找到员工</strong><span>试试调整搜索条件，或添加一条新档案。</span></div></td></tr></c:if>
                </tbody>
            </table>
        </div>
    </section>
    <footer class="site-footer">员工档案管理 · Java Web 入门实战</footer>
</main>
<script src="${pageContext.request.contextPath}/assets/app.js"></script>
</body>
</html>
