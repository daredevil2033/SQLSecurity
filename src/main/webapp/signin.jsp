<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Signin</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/signin" method="POST">
    <label for="username">Enter username:</label>
    <input type="text" id="username" name="username"><br>
    <label for="password1">Enter password:</label>
    <input type="password" id="password1" name="password1"><br>
    <label for="password2">Repeat password:</label>
    <input type="password" id="password2" name="password2"><br>
    <input type="submit" value="Sign In">
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</form>
<a href="login.jsp">Log In</a>
</body>
</html>
