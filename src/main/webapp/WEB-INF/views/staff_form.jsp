<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:choose><c:when test="${mode eq 'create'}">添加员工</c:when><c:otherwise>编辑员工</c:otherwise></c:choose> · 员工档案管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/"><span class="brand-mark">H</span> 员工档案管理 <span>HR</span></a>
    <nav><a class="nav-link" href="${pageContext.request.contextPath}/">概览</a><a class="nav-link active" href="${pageContext.request.contextPath}/staffInfoList">员工档案</a></nav>
    <a class="button button-quiet button-small" href="${pageContext.request.contextPath}/staffInfoList">返回列表</a>
</header>
<main class="page-shell form-shell">
    <div class="page-heading"><div><p class="eyebrow">员工管理 / <c:choose><c:when test="${mode eq 'create'}">新增</c:when><c:otherwise>编辑</c:otherwise></c:choose></p><h1><c:choose><c:when test="${mode eq 'create'}">添加员工档案</c:when><c:otherwise>编辑员工档案</c:otherwise></c:choose></h1><p class="muted">请填写员工的基本信息，带 * 的项目为必填项。</p></div></div>
    <c:if test="${not empty errorMessage}"><div class="notice notice-error"><c:out value="${errorMessage}"/></div></c:if>
    <section class="panel form-panel">
        <c:choose><c:when test="${mode eq 'create'}"><form action="${pageContext.request.contextPath}/staffInfoSave" method="post"></c:when><c:otherwise><form action="${pageContext.request.contextPath}/staffInfoUpdate" method="post"><input type="hidden" name="id" value="${staff.id}"></c:otherwise></c:choose>
            <div class="form-grid">
                <label class="field"><span>姓名 <b>*</b></span><input type="text" name="name" maxlength="20" value="<c:out value='${staff.name}'/>" placeholder="请输入员工姓名" required></label>
                <label class="field"><span>性别 <b>*</b></span><select name="gender" required><option value="男" <c:if test="${staff.gender eq '男'}">selected</c:if>>男</option><option value="女" <c:if test="${staff.gender eq '女'}">selected</c:if>>女</option></select></label>
                <label class="field"><span>出生日期 <b>*</b></span><input type="date" name="birthday" value="<c:out value='${staff.birthday}'/>" required></label>
                <label class="field"><span>手机号 <b>*</b></span><input type="tel" name="phone" inputmode="numeric" pattern="1[3-9][0-9]{9}" maxlength="11" value="<c:out value='${staff.phone}'/>" placeholder="11 位手机号" required></label>
            </div>
            <div class="form-footer"><a class="button button-quiet" href="${pageContext.request.contextPath}/staffInfoList">取消</a><button class="button button-primary" type="submit"><c:choose><c:when test="${mode eq 'create'}">保存员工</c:when><c:otherwise>保存修改</c:otherwise></c:choose></button></div>
        </form>
    </section>
    <footer class="site-footer">员工档案管理 · Java Web 入门实战</footer>
</main>
</body>
</html>
