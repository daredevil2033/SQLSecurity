<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change password</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/change" method="POST">
    <label for="password">Enter old password:</label>
    <input type="password" id="password" name="password"><br>
    <label for="password1">Enter new password:</label>
    <input type="password" id="password1" name="password1"><br>
    <label for="password2">Repeat new password:</label>
    <input type="password" id="password2" name="password2"><br>
    <input type="submit" value="Change password">
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</form>
</body>
</html>
