<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="messages">

</div>

<script type="text/javascript">
  function showMessages(data) {
    $("#messages").html("");
    for (var i=0;i<data.messages.length;i++){
      var message = data.messages[i];

      var messageDiv = document.createElement("div");
      messageDiv.setAttribute("class","message");

      var subjectSpan = document.createElement("span");
      subjectSpan.setAttribute("class","subject");
      subjectSpan.appendChild(document.createTextNode(message.subject));

      var contentSpan = document.createElement("span");
      contentSpan.setAttribute("class","messageBody");
      contentSpan.appendChild(document.createTextNode(message.content));

      var nameSpan = document.createElement("span");
      nameSpan.setAttribute("class","messageBody");
      nameSpan.appendChild(document.createTextNode(message.name + "("+message.email+")"));

      messageDiv.appendChild(subjectSpan);
      messageDiv.appendChild(contentSpan);
      messageDiv.appendChild(nameSpan);

      $("div#messages").append(messageDiv);
    }
  }

  function updatePage(){
    $.getJSON("<c:url value="/getmessages"/>", showMessages)
  }
  function onLoad() {
    updatePage();
    window.setInterval(updatePage,5000);
  }

  $(document).ready(onLoad);
</script>
