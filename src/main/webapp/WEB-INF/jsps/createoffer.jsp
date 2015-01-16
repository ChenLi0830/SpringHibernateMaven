<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>


<h1>under construction, create a new offer.</h1>
<br/>


<form action="${pageContext.request.contextPath}/docreate" method="get">
  <table>
    <tr><td>Name: </td><td><input name="name" type="text"></td></tr>
    <tr><td>Email: </td><td><input name="email" type="email"></td></tr>
    <tr><td>Your offer: </td><td><textarea rows="10" cols="10"></textarea></td></tr>
    <tr><td> </td><td><input name="Create advert" type="submit"></td></tr>
  </table>
</form>


</body>
</html>