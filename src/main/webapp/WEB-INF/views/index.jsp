<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>员工档案管理 · 员工档案</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/"><span class="brand-mark">H</span> 员工档案管理 <span>HR</span></a>
    <nav><a class="nav-link active" href="${pageContext.request.contextPath}/">概览</a><a class="nav-link" href="${pageContext.request.contextPath}/staffInfoList">员工档案</a></nav>
    <a class="button button-primary button-small" href="${pageContext.request.contextPath}/staffInfoSave">＋ 添加员工</a>
</header>
<main class="page-shell">
    <section class="welcome">
        <p class="eyebrow">HR MANAGEMENT · 教学示例</p>
        <h1>员工档案，一目了然。</h1>
        <p class="welcome-copy">从一个小而完整的 Java Web 项目开始，练习表单、Servlet、JDBC 和数据库。</p>
        <a class="button button-dark" href="${pageContext.request.contextPath}/staffInfoList">查看员工档案 <span aria-hidden="true">→</span></a>
        <div class="welcome-orb" aria-hidden="true"></div>
    </section>

    <section class="stats-grid" aria-label="员工统计">
        <article class="stat-card"><span class="stat-icon purple">人</span><span class="stat-label">员工总数</span><strong>${totalCount}</strong><span class="stat-note">当前档案记录</span></article>
        <article class="stat-card"><span class="stat-icon blue">男</span><span class="stat-label">男性员工</span><strong>${maleCount}</strong><span class="stat-note">按性别统计</span></article>
        <article class="stat-card"><span class="stat-icon coral">女</span><span class="stat-label">女性员工</span><strong>${femaleCount}</strong><span class="stat-note">按性别统计</span></article>
    </section>

    <section class="panel quick-panel">
        <div><p class="eyebrow">从这里开始</p><h2>把基础功能做扎实</h2><p class="muted">先体验完整的员工增删改查，再去练习题里扩展图书借阅和成绩管理。</p></div>
        <div class="quick-actions"><a class="text-link" href="${pageContext.request.contextPath}/staffInfoSave">新增一条员工档案 <span>→</span></a><span class="muted">练习路线：员工档案 → 图书借阅 → 成绩管理</span></div>
    </section>
    <footer class="site-footer">员工档案管理 · Java Web 入门实战</footer>
</main>
</body>
</html>
