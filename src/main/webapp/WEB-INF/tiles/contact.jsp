<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form method="post" commandName="message">

  <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
  <input type="hidden" name="_eventId" value="send"/>
  <input type="hidden" name="username" value="test username"/>

  <table class="formtable">
    <tr>
      <td class="label">Your name:</td>
      <td><sf:input class="control" type="text" path="name"/><br/>

        <div class="error"><sf:errors path="name"/></div>
      </td>
    </tr>
    <tr>
      <td class="label">Your email:</td>
      <td><sf:input class="control" type="text" path="email"/><br/>
        <div class="error"><sf:errors path="email"/></div>
      </td>
    </tr>
    <tr>
      <td class="label">Subject:</td>
      <td><sf:input class="control" type="text" path="subject"/><br/>

        <div class="error"><sf:errors path="subject"></sf:errors></div>
      </td>
    </tr>
    <tr>
      <td class="label">Your message:</td>
      <td><sf:textarea class="control" type="text" path="content"/><br/>
        <div class="error"><sf:errors path="content"></sf:errors></div>
      </td>
    </tr>

    <tr>
      <td class="label"></td>
      <td><input class="control" value="Create message" type="submit"/></td>
    </tr>
  </table>
</sf:form>
