<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />


<html>
<body>


<h1>Create new account.</h1>
<br/>


<sf:form action="${pageContext.request.contextPath}/createaccount" method="post" commandName="user">
  <table class="formtable">
    <tr><td class="label">Username: </td><td><sf:input class="control" name="username" type="text" path="username"/><br/><sf:errors path="username" cssClass="error"></sf:errors></td></tr>
    <tr><td class="label">Email: </td><td><sf:input class="control"  name="email" type="text" path="email"/><br/><sf:errors path="email" cssClass="error"/></td></tr>
    <tr><td class="label">Password: </td><td><sf:input class="control"  name="password" type="text" path="password"/><br/><sf:errors path="password" cssClass="error"/></td></tr>
    <tr><td class="label">Confirm Password: </td><td><input class="control"  name="confirmpass" type="text"/><br/></td></tr>
    <tr><td class="label"> </td><td><input class="control"  value="Create account" type="submit" /></td></tr>
  </table>
</sf:form>


</body>
</html>