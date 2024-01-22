<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>Articles</title>
</head>
<body>
<header>
    <div class="headerMenu">
        <nav>
            <ul>
                <li></li>
                <li></li>
                <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/article/all">All articles</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

<div id="content">
    <h1>All articles</h1>

    <div class="container">
        <table class="f1-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articles}" var="article">
                <tr class="article" onclick="window.location.href='${pageContext.request.contextPath}/article/${article.id}'">
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                    <td>${article.authorString}</td>
                    <td>${article.publication}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
