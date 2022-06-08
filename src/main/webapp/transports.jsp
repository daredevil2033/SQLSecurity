<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Transports Table</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/transports" method="get">
    <input type="hidden" name="purpose" value="R">
    <label for="page">Page</label>
    <input type="number" id="page" name="page" min="1" value="${page}">
    <label for="pagesize">Page Size</label>
    <input type="number" id="pagesize" name="pagesize" min="1" value="${pagesize}">
    <input type="submit" value="Select">
</form>
<table>
    <tr>
        <th>transport_id</th>
        <th>owner_id</th>
        <th>license_plate</th>
        <th>brand</th>
        <th>model</th>
        <th>model_year</th>
        <th>color</th>
    </tr>
    <c:forEach items="${transportList}" var="transport">
        <tr>
            <td>${transport.transport_id}</td>
            <td>${transport.owner_id}</td>
            <td>${transport.license_plate}</td>
            <td>${transport.brand}</td>
            <td>${transport.model}</td>
            <td>${transport.model_year}</td>
            <td>${transport.color}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<form action="${pageContext.request.contextPath}/transports" method="post">
    <input type="hidden" name="purpose" value="C">
    <label for="transport_id_c">transport_id</label>
    <input type="number" id="transport_id_c" name="transport_id" required><br>
    <label for="owner_id_c">owner_id</label>
    <select id="owner_id_c" name="owner_id" required>
        <c:forEach items="${owner_idList}" var="o_id">
            <option value="${o_id}">${o_id}</option>
        </c:forEach>
    </select><br>
    <label for="license_plate">license_plate</label>
    <select id="license_plate" name="license_plate" required>
        <c:forEach items="${license_plateList}" var="license_plate">
            <option value="${license_plate}">${license_plate}</option>
        </c:forEach>
    </select><br>
    <label for="brand">brand</label>
    <select id="brand" name="brand" required>
        <c:forEach items="${brandList}" var="brand">
            <option value="${brand}">${brand}</option>
        </c:forEach>
    </select><br>
    <label for="model">model</label>
    <input type="text" id="model" name="model" maxlength="20" required><br>
    <label for="model_year">model_year</label>
    <input type="number" id="model_year" name="model_year" min="1900" max="3000" required><br>
    <label for="color">color</label>
    <select id="color" name="color" required>
        <c:forEach items="${colorList}" var="color">
            <option value="${color}">${color}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Insert">
</form>
<form action="${pageContext.request.contextPath}/transports" method="post">
    <input type="hidden" name="purpose" value="U">
    <label for="transport_id_u">transport_id</label>
    <select id="transport_id_u" name="transport_id">
        <c:forEach items="${transport_idList}" var="t_id">
            <option value="${t_id}">${t_id}</option>
        </c:forEach>
    </select><br>
    <label for="owner_id_u">owner_id</label>
    <select id="owner_id_u" name="owner_id">
        <c:forEach items="${owner_idList}" var="o_id">
            <option value="${o_id}">${o_id}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Update">
</form>
<form action="${pageContext.request.contextPath}/transports" method="post">
    <input type="hidden" name="purpose" value="D">
    <label for="transport_id_d">transport_id</label>
    <select id="transport_id_d" name="transport_id">
        <c:forEach items="${transport_idList}" var="t_id">
            <option value="${t_id}">${t_id}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Delete">
</form>
</body>
</html>
