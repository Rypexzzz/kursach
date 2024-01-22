<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>Profile</title>
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
    <h1>Your profile</h1>
    <table class="profile">
        <tr>
            <td>Username:</td>
            <td><b>${username}</b></td>
        </tr>
    </table>

    <p>Your articles:</p>
    <div class="container">
        <table class="f1-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articles}" var="article">
                <tr class="article" onclick="window.location.href='${pageContext.request.contextPath}/article/${article.id}'">
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                    <td>${article.publication}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="clear"></div>

    <h1>New article</h1>
    <form method="post">
        <table class="profile">
            <tr>
                <td><input type="text" name="title" id="title"
                           placeholder="Title" required
                           pattern=".*"
                ></td>
            </tr>
            <tr>
                <td><textarea name="text" id="text" cols="50" rows="20"
                              placeholder="Text" required
                ></textarea></td>
            </tr>
        </table>

        <div class="clear"></div>

        <input type="submit" class="button-3" name="publish" value="Publish">
    </form>
</div>

</body>
</html>
