<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>Log In</title>
</head>
<body>
    <div class="login">
        <form method="post">
            <h2>Login Here</h2>

            <label>${errorText}</label>
            <br>
            <label for="username">Username</label>
            <input type="text" placeholder="Username" id="username" name="username" pattern="[a-z0-9_]+" required>

            <label for="password">Password</label>
            <input type="password" placeholder="Password" id="password" name="password"  pattern="[a-z0-9__]+" required>

            <input type="submit" name="loginSubmit" class="log-button" value="Login">
            <input type="submit" name="registerSubmit" class="log-button" value="Register">

        </form>
</div>
</body>

</html>