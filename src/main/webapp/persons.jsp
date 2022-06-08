<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Persons Table</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/persons" method="get">
    <input type="hidden" name="purpose" value="R">
    <label for="page">Page</label>
    <input type="number" id="page" name="page" min="1" value="${page}">
    <label for="pagesize">Page Size</label>
    <input type="number" id="pagesize" name="pagesize" min="1" value="${pagesize}">
    <input type="submit" value="Select">
</form>
<table>
    <tr>
        <th>person_id</th>
        <th>name</th>
        <th>gender</th>
        <th>birthday</th>
    </tr>
    <c:forEach items="${personList}" var="person">
        <tr>
            <td>${person.person_id}</td>
            <td>${person.name}</td>
            <td>${person.gender}</td>
            <td>${person.birthday}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<form action="${pageContext.request.contextPath}/persons" method="post">
    <input type="hidden" name="purpose" value="C">
    <label for="person_id_c">person_id</label>
    <input type="number" id="person_id_c" name="person_id" required><br>
    <label for="name_c">name</label>
    <input type="text" id="name_c" name="name" maxlength="50" required><br>
    <label for="gender">gender</label>
    <select id="gender" name="gender" required>
        <option value="male">male</option>
        <option value="female">female</option>
    </select><br>
    <label for="birthday">birthday</label>
    <input type="date" id="birthday" name="birthday" required><br>
    <input type="submit" value="Insert">
</form>
<form action="${pageContext.request.contextPath}/persons" method="post">
    <input type="hidden" name="purpose" value="U">
    <label for="person_id_u">person</label>
    <select id="person_id_u" name="person_id">
        <c:forEach items="${person_idList}" var="p_id">
            <option value="${p_id}">${p_id}</option>
        </c:forEach>
    </select><br>
    <label for="name_u">new name</label>
    <input type="text" id="name_u" name="name" maxlength="50" required><br>
    <input type="submit" value="Update">
</form>
<form action="${pageContext.request.contextPath}/persons" method="post">
    <input type="hidden" name="purpose" value="D">
    <label for="person_id_d">person_id</label>
    <select id="person_id_d" name="person_id">
        <c:forEach items="${person_idList}" var="p_id">
            <option value="${p_id}">${p_id}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Delete">
</form>
</body>
</html>
