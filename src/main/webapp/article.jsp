<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>${article.title}</title>
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
    <h1>${article.title}</h1>
    <table class="three-tables">
        <tr>
            <td>
                <div class="table-of-three">
                    <table class="profile">
                        <tr>
                            <td>ID:</td>
                            <td><b>${article.id}</b></td>
                        </tr>
                        <tr>
                            <td>Author:</td>
                            <td><b>${article.authorString}</b></td>
                        </tr>
                        <tr>
                            <td>Publication date:</td>
                            <td><b>${article.publication}</b></td>
                        </tr>
                    </table>
                </div>
                <c:if test="${yours}">
                    <form method="post">
                        <input type="submit" name="delete" class="button-3" style="background-color: #c40906" value="Delete">
                    </form>
                </c:if>
            </td>
            <td>
            <div class="table-of-three">
                <h3>Coauthors</h3>
                <table class="profile">
                    <c:forEach items="${coauthors}" var="coauthor">
                        <tr><td>${coauthor.authorString}</td></tr>
                    </c:forEach>
                </table>
                <c:if test="${yours}">
                    <form method="post">
                        <select name="coauthorID" required>
                            <c:forEach items="${users}" var="user">
                                <option value="${user.id}">${user.username}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" class="button-3" name="coauthor" value="Add coauthor">
                    </form>
                </c:if>
            </div>
            </td>
            <td>
            <div class="table-of-three">
                <h3>Sources</h3>
                <table class="profile">
                    <c:forEach items="${sources}" var="source">
                        <tr><td>${source.link}</td></tr>
                    </c:forEach>
                </table>
                <c:if test="${yours}">
                    <form method="post">
                        <input type="text" name="sourceText" placeholder="Source" required>
                        <input type="submit" class="button-3" name="source" value="Add source">
                    </form>
                </c:if>
            </div>
            </td>
        </tr>
    </table>

    <hr>
    <p>${article.text}</p>
    <hr>

    <div class="clear"></div>

    <h3>Comments</h3>
<%--    <input type="submit" class="button-3" value="Leave a comment" onclick="window.location.href='${pageContext.request.contextPath}/comment/${article.id}'">--%>
    <div class="container">
        <table class="f1-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Author</th>
                <th>Text</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${comments}" var="comment">
                <tr>
                    <td>${comment.id}</td>
                    <td>${comment.authorString}</td>
                    <td>${comment.text}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="clear"></div>

    <h1>Leave your comment</h1>
    <form method="post">
        <table class="profile">
            <tr><textarea name="text" id="text" cols="50" rows="20"
                          placeholder="Text" required
            ></textarea></tr>
        </table>

        <div class="clear"></div>

        <input type="submit" class="button-3" name="comment" value="Leave a comment">
    </form>
</div>

</body>
</html>
