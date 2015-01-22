<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 15-01-21
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />

<html>
<head>
    <title></title>
</head>
<body>

Authorized user only!
<table class="formtable">
  <tr><td>Username</td><td>Email</td><td>Role</td><td>Enabled</td></tr>

  <c:forEach var="user" items="${users}">

    <tr><td><c:out value="${user.username}"></c:out></td>
      <td><c:out value="${user.email}"></c:out></td>
      <td><c:out value="${user.authority}"></c:out></td>
      <td><c:out value="${user.enabled}"></c:out></td>

    </tr>

  </c:forEach>

</table>
</body>
</html>
