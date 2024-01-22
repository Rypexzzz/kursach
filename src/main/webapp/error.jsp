<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>404</title>
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
    <h1>400</h1>
    <p>Bad request.</p>
</div>

</body>
</html>
