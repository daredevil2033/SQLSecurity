<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="POST">
    <label for="username">Enter username:</label>
    <input type="text" id="username" name="username"><br>
    <label for="password">Enter password:</label>
    <input type="password" id="password" name="password"><br>
    <input type="submit" value="Log In">
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</form>
<a href="signin.jsp">Sign In</a>
</body>
</html>
