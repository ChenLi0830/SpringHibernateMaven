<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>

<head>

    <%--<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css"/>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.2.min.js"></script>--%>


    <%--<script type="text/javascript">--%>
        <%--function onLoad() {--%>

            <%--$("#password").keyup(checkPasswordsMatch());--%>
            <%--$("#confirmpass").keyup(checkPasswordsMatch());--%>

            <%--$("#details").submit(canSubmit());--%>
        <%--}--%>

        <%--function canSubmit() {--%>
            <%--var password = $("#password").val();--%>
            <%--var confirmpass = $("#confirmpass").val();--%>

            <%--if (password != confirmpass) {--%>
                <%--alert("Passwords do not match!")--%>
                <%--return false;--%>
            <%--}--%>
            <%--else {--%>
                <%--return true;--%>
            <%--}--%>
        <%--}--%>

        <%--function checkPasswordsMatch() {--%>
            <%--var password = $("#password").val();--%>
            <%--var confirmpass = $("#confirmpass").val();--%>

            <%--if (password.length > 3 || confirmpass.length > 3) {--%>

                <%--if (password == confirmpass) {--%>
                    <%--$("#matchpass").text("Passwords match.");--%>
                    <%--$("#matchpass").addClass("valid");--%>
                    <%--$("#matchpass").removeClass("error");--%>
                <%--} else {--%>
                    <%--$("#matchpass").text("Passwords do not match.");--%>
                    <%--$("#matchpass").addClass("error");--%>
                    <%--$("#matchpass").removeClass("valid");--%>
                <%--}--%>
            <%--}--%>
        <%--}--%>

        <%--$(document).ready(onLoad);--%>
    <%--</script>--%>


        <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.2.min.js"></script>

        <script type="text/javascript">
            function onLoad() {
                alert("onload");
                $("#password").keyup(checkPasswordMatch);
                $("#confirmpass").keyup(checkPasswordMatch);
                $("#details").submit(cansubmit);

            }

            function cansubmit(){
                var password = $("#password").val();
                var confirmpass = $("#confirmpass").val();
                if (password == confirmpass){
                    return true;
                } else {
                    alert("passwords don't match")
                    return false;
                }
            }

            function checkPasswordMatch() {
                var password = $("#password").val();
                var confirmpass = $("#confirmpass").val();
                if (password.length>3 && confirmpass.length>3){

                    if (password == confirmpass){
                        $("#matchpass").text("Password match");
                        $("#matchpass").addClass("valid");
                        $("#matchpass").removeClass("error");
                    } else
                    {
                        $("#matchpass").text("Password do not match");
                        $("#matchpass").addClass("error");
                        $("#matchpass").removeClass("valid");
                    }

                }
            }

            $(document).ready(onLoad);
        </script>


    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Create New Account</title>
</head>




<body>


<h1>Create new account.</h1>
<br/>


<sf:form action="${pageContext.request.contextPath}/createaccount" method="post" commandName="user" id="details">
    <table class="formtable">
        <tr>
            <td class="label">Username:</td>
            <td><sf:input class="control" name="username" type="text" path="username"/><br/>

                <div class="error"><sf:errors path="username"></sf:errors></div>
            </td>
        </tr>
        <tr>
            <td class="label">Email:</td>
            <td><sf:input class="control" name="email" type="text" path="email"/><br/>

                <div class="error"><sf:errors path="email"/></div>
            </td>
        </tr>
        <tr>
            <td class="label">Password:</td>
            <td><sf:input class="control" id="password" name="password" type="text" path="password"/><br/>

                <div class="error"><sf:errors path="password"/></div>
            </td>
        </tr>
        <tr>
            <td class="label">Confirm Password:</td>
            <td><input class="control" id="confirmpass" name="confirmpass" type="text"/><div id="matchpass" ></div></td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input class="control" value="Create account" type="submit"/></td>
        </tr>
    </table>
</sf:form>


</body>
</html>