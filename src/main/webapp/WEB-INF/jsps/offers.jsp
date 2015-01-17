<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />

<html>
<body>
<h1>${message}</h1>

<%--Session: <%= session.getAttribute("name") %><br/>--%>
<%--Request: <%= request.getAttribute("name") %><br/>--%>
<%--Request (using EL): ${name}<br/>--%>
<%--c:out: <c:out value="${name}"></c:out><br/>--%>

<table class="offers">
<tr><td>Name</td><td>Email</td><td>Text</td></tr>
<c:forEach var="offer" items="${offerList}">
  <%--Id = ${offer.id}--%>
  <%--<c:out value="${item.id}"></c:out><br/>--%>
  <tr>
  <td><c:out value="${offer.name}"></c:out></td>
  <td><c:out value="${offer.email}"></c:out></td>
  <td><c:out value="${offer.text}"></c:out></td>
  </tr>
</c:forEach>
<br/>

</table>

</body>
</html>