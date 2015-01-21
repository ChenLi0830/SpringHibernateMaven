<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
	<h1>Dispatcher Servlet Home</h1>
<br/>


	<p><a href="${pageContext.request.contextPath}/offers">Show current offers.</a></p>
	<p><a href="${pageContext.request.contextPath}/createoffer">Add a new offer.</a></p>


	<p><a href="<c:url value='/j_spring_security_logout'/>">Logout.</a></p>

</body>
</html>