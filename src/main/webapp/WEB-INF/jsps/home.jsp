<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<body>
	<h1>Dispatcher Servlet Home</h1>
<br/>


	<p><a href="${pageContext.request.contextPath}/offers">Show current offers.</a></p>
	<p><a href="${pageContext.request.contextPath}/createoffer">Add a new offer.</a></p>

	<sec:authorize access="!isAuthenticated()">
		<p><a href="<c:url value='/login'/>">Login</a></p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<p><a href="<c:url value='/j_spring_security_logout'/>">Logout</a></p>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<p><a href="<c:url value='/admin'/>">Admin</a></p>
	</sec:authorize>


</body>
</html>