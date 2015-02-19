<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:choose>
  <c:when test="${hasOffers==true}">
    <a href="${pageContext.request.contextPath}/createoffer">Edit your offer</a>
  </c:when>
  <c:otherwise>
    <a href="${pageContext.request.contextPath}/createoffer">Add a new offer</a>
  </c:otherwise>
</c:choose>

&nbsp;

<sec:authorize access="hasRole('ROLE_ADMIN')">
  <a href="<c:url value='/admin'/>">Admin</a>
</sec:authorize>

&nbsp;

<sec:authorize access="isAuthenticated()">
  <a href="<c:url value="/messages"/>">Messages (<span id="numberMessges">0</span>)</a>
</sec:authorize>

&nbsp;