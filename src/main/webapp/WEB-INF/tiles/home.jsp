<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<table class="offers">
    <tr>
        <td>Name</td>
        <td></td>
        <td>Text</td>
    </tr>
    <c:forEach var="offer" items="${offerList}">
        <%--Id = ${offer.id}--%>
        <%--<c:out value="${item.id}"></c:out><br/>--%>
        <tr>
            <td><c:out value="${offer.user.name}"></c:out></td>
            <td><a href="<c:url value='/message?uid=${offer.username}'/>">contact</a></td>
            <td><c:out value="${offer.text}"></c:out></td>
        </tr>
    </c:forEach>
    <br/>
</table>


<script type="text/javascript">
    function updateLink(data) {
        $("#numberMessges").text(data.number);
//        alert(data.number);
    }

    function updatePage(){
        $.getJSON("<c:url value="/getmessages"/>", updateLink)
    }
    function onLoad() {
        updatePage();
        window.setInterval(updatePage,5000);
    }

    $(document).ready(onLoad);
</script>
