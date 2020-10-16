<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
    <i><c:out value="${message}"/></i><br>

    <form action="/login" method="post" id="loginForm">
        <input hidden name="action" value="login">

        <label>Name:</label>
        <input name="login" placeholder="Please enter name" value="${login}"><br> <!-- userBean.login -->

        <label>Password:</label> <!-- TODO: add type="password" later -->
        <input name="password"><br>
    </form>

    <button type="submit" form="loginForm" value="Login">Login</button>

    <br><br>
    <form action="/register" method="get">
        <input type="submit" value="Register">
    </form>

    <!-- TODO: add "Забыли пароль?" button -->

    </body>
</html>
