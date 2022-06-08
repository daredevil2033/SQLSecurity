<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <a href="change.jsp">Change password</a><br>
    <a href="${pageContext.request.contextPath}/persons?page=1&pagesize=10&purpose=R">Persons table</a><br>
    <a href="${pageContext.request.contextPath}/transports?page=1&pagesize=10&purpose=R">Transports table</a>
</body>
</html>