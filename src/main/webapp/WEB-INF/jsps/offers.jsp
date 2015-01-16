<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>${message}</h1>

<%--Session: <%= session.getAttribute("name") %><br/>--%>
<%--Request: <%= request.getAttribute("name") %><br/>--%>
<%--Request (using EL): ${name}<br/>--%>
<%--c:out: <c:out value="${name}"></c:out><br/>--%>
<c:forEach var="offer" items="${offerList}">
  Id = ${offer.id}
  <%--<c:out value="${item.id}"></c:out><br/>--%>
  <c:out value="${offer.name}"></c:out><br/>
  <c:out value="${offer.email}"></c:out><br/>
  <c:out value="${offer.text}"></c:out><br/>
</c:forEach>
<br/>
</body>
</html>