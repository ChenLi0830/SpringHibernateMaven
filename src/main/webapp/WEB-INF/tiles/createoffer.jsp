<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form action="${pageContext.request.contextPath}/docreate" method="post" commandName="offer">
  <table class="formtable">
    <tr><td class="label">Name: </td><td><sf:input class="control" name="name" type="text" path="name"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
    <tr><td class="label">Email: </td><td><sf:input class="control"  name="email" type="pass" path="email"/><br/><sf:errors path="email" cssClass="error"/></td></tr>
    <tr><td class="label">Your offer: </td><td><sf:textarea class="control"  name="text"  rows="10" cols="10" path="text"></sf:textarea><br/><sf:errors path="text" cssClass="error"/></td></tr>
    <tr><td class="label"> </td><td><input class="control"  value="Create advert" type="submit" /></td></tr>
  </table>
</sf:form>
