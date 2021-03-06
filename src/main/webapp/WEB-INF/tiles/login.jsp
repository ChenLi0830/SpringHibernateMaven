<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css"/>

<script>
$(document).ready(function(){
    document.f.j_username.focus();
})
</script>


<c:if test="${param.error!=null}">
    <p class="loginerror">Login Failed. Check your user name and password. </p>
</c:if>

<form name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
    <table class="offers">
        <tr>
            <td>User:</td>
            <td><input type='text' name='j_username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Login"/></td>
        </tr>
    </table>
</form>

<p><a href="<c:url value="/newaccount"/>">Create new account</a></p>
