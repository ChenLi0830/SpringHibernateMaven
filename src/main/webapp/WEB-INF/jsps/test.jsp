<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/CaveDB">
  select name, email, text from offers
</sql:query>

<html>
<head>
  <title>DB Test</title>
</head>
<body>

<h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
  Name ${row.name}<br/>
  Email ${row.email}<br/>
  Text ${row.text}<br/>
</c:forEach>

</body>
</html>